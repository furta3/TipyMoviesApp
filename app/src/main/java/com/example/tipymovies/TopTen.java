package com.example.tipymovies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tipymovies.Config;
import com.example.tipymovies.DetallesPelicula;
import com.example.tipymovies.ListarPeliculas;
import com.example.tipymovies.R;
import com.example.tipymovies.adapter.MoviesAdapter;
import com.example.tipymovies.adapter.TopTenAdapter;
import com.example.tipymovies.model.JoinTopTen;
import com.example.tipymovies.model.SearchTopTenResponse;
import com.example.tipymovies.model.Movie;
import com.example.tipymovies.model.SearchMovieResponse;
import com.example.tipymovies.rest.MovieApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TopTen extends AppCompatActivity {
    private static final String TAG = ListarPeliculas.class.getSimpleName();
    public static final String BASE_URL = Config.API_BASE_URL;
    public static final String TTPY_MOVIES_URL = Config.TTPY_MOVIES_URL;
    private static Retrofit retrofit = null;
    private RecyclerView recyclerView = null;
    // insert your themoviedb.org API KEY here
    private final static String API_KEY = Config.API_KEY;

    Button buscar;
    TextView textoBusqueda;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_ten);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        connectAndGetApiData();

        buscar = (Button) findViewById(R.id.buscar);
        textoBusqueda = (TextView) findViewById(R.id.textoBusqueda);
        buscar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                connectAndGetApiData();
            }
        });
    }

    public void connectAndGetApiData(){
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(TTPY_MOVIES_URL)//BASE_URL
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        MovieApiService topten = retrofit.create(MovieApiService.class);
        Call<SearchTopTenResponse> call = topten.toptenJoin();
          call.enqueue(new Callback<SearchTopTenResponse>() {
            @Override
          public void onResponse(Call<SearchTopTenResponse> call, Response<SearchTopTenResponse> response) {
                List<JoinTopTen> top10 = response.body().getResults();
                TopTenAdapter tt = new TopTenAdapter(top10, R.layout.list_item_movie, getApplicationContext());
                recyclerView.setAdapter(tt);
            }

            @Override
            public void onFailure(Call<SearchTopTenResponse> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });

    }}
