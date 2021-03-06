package com.example.tipymovies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tipymovies.adapter.MoviesAdapter;
import com.example.tipymovies.model.Movie;
import com.example.tipymovies.model.SearchMovieResponse;
import com.example.tipymovies.rest.MovieApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ListarPeliculas extends AppCompatActivity {

    private static final String TAG = ListarPeliculas.class.getSimpleName();
    public static final String BASE_URL = Config.API_BASE_URL;
    public static final String TTPY_MOVIES_URL = Config.TTPY_MOVIES_URL;
    private static Retrofit retrofit = null;
    private RecyclerView recyclerView = null;
    // insert your themoviedb.org API KEY here
    private final static String API_KEY = Config.API_KEY;

    Button buscar,siguiente,top10, btnTrivia;
    TextView textoBusqueda;
    TextView movieTitle;
    ImageView poster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_peliculas);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        connectAndGetApiData("Garfield");
        Toolbar toolbar = findViewById(R.id.toolbal);
        setSupportActionBar(toolbar);
        buscar = (Button) findViewById(R.id.buscar);
        textoBusqueda = (TextView) findViewById(R.id.textoBusqueda);
        buscar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                connectAndGetApiData(textoBusqueda.getText().toString());
            }
        });
        siguiente = (Button) findViewById(R.id.siguiente);
        siguiente.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent detalles = new Intent(ListarPeliculas.this,DetallesPelicula.class);
                startActivity(detalles);
            }
        });

        /*movieTitle = (TextView) findViewById(R.id.movieTitle);
        movieTitle.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent detalles = new Intent(ListarPeliculas.this,DetallesPelicula.class);
                startActivity(detalles);
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.tipymovies, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                Log.d("editor", editor.toString());
                editor.clear().commit();
                Intent login = new Intent(ListarPeliculas.this, Login.class);
                startActivity(login);
                finish();
                return  true;
            case R.id.item2:
                Intent trivia = new Intent(ListarPeliculas.this,MiniJuego1.class);
                Bundle mybundle = new Bundle();
                mybundle.putInt("mj",2);
                trivia.putExtras(mybundle);
                startActivity(trivia);
                return  true;
            case R.id.item3:
                Intent topglobal = new Intent(ListarPeliculas.this,TopTen.class);
                startActivity(topglobal);
                return  true;
            case R.id.item4:
                Intent toptrivia = new Intent(ListarPeliculas.this,TopTenTrivia.class);
                startActivity(toptrivia);
                return  true;
        }
        return true;
    }

    public void connectAndGetApiData(String peli){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(TTPY_MOVIES_URL)//BASE_URL
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        MovieApiService movieApiService = retrofit.create(MovieApiService.class);
        //Call<SearchMovieResponse> call = movieApiService.search(API_KEY,peli);
        Call<SearchMovieResponse> call = movieApiService.search2(peli,"1");
        call.enqueue(new Callback<SearchMovieResponse>() {
            @Override
            public void onResponse(Call<SearchMovieResponse> call, Response<SearchMovieResponse> response) {
                List<Movie> movies = response.body().getResults();
                MoviesAdapter ma = new MoviesAdapter(movies, R.layout.list_item_movie, getApplicationContext());
                ma.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent detalles = new Intent(ListarPeliculas.this,DetallesPelicula.class);
                        Bundle mybundle = new Bundle();
                        mybundle.putString("imdbID",movies.get(recyclerView.getChildAdapterPosition(v)).getImdbID());
                        mybundle.putString("titulo",movies.get(recyclerView.getChildAdapterPosition(v)).getTitle());
                        mybundle.putString("poster",movies.get(recyclerView.getChildAdapterPosition(v)).getPoster());
                        mybundle.putString("anio",movies.get(recyclerView.getChildAdapterPosition(v)).getYear());
                        detalles.putExtras(mybundle);
                        Log.d("Error", mybundle+"hola");
                        startActivity(detalles);
                        //Toast.makeText(getApplicationContext(),"Selecci??n: "+movies.get(recyclerView.getChildAdapterPosition(v)).getTitle(),Toast.LENGTH_SHORT).show();
                    }
                });
                recyclerView.setAdapter(ma);
                Log.d(TAG, "Number of movies received: " + movies.size());
            }
            @Override
            public void onFailure(Call<SearchMovieResponse> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });


    }
}