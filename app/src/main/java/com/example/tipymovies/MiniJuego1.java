package com.example.tipymovies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tipymovies.model.Trivia;
import com.example.tipymovies.model.Trivia1Response;
import com.example.tipymovies.rest.MovieApiService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MiniJuego1 extends AppCompatActivity {
    private static final String TAG = MiniJuego1.class.getSimpleName();
    public static final String BASE_URL = Config.API_BASE_URL;
    public static final String TTPY_MOVIES_URL = Config.TTPY_MOVIES_URL;
    private static Retrofit retrofit = null;
    private final static String API_KEY = Config.API_KEY;
    Button res1,res2,res3,res4;
    TextView pre, titulo;
    List<Trivia> Preguntas;
    int contador  = -1;
    int combo=1,puntos=0,resCorrectas=0,mejorCombo=0;
    Bundle mybundle;
    String userid;
    ImageView poster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //ESTO VA PRIMERO QUE TODO PLS//
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mini_juego1);
        //ESTO VA PRIMERO QUE TODO PLS//

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(TTPY_MOVIES_URL)//BASE_URL
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        SharedPreferences prefs = getSharedPreferences("MisPreferencias", Context.MODE_PRIVATE);
        userid = prefs.getString("user_id","");
        mybundle = this.getIntent().getExtras();
        poster = (ImageView) findViewById(R.id.posterMiniJuego);
        res1 = (Button) findViewById(R.id.Res1);
        res2 = (Button) findViewById(R.id.Res2);
        res3 = (Button) findViewById(R.id.Res3);
        res4 = (Button) findViewById(R.id.Res4);
        pre = (TextView) findViewById(R.id.Pregunta);
        titulo = (TextView) findViewById(R.id.TituloMiniJuego1);

        if(mybundle.getInt("mj")==1){
            loadMiniJuego1();
        }
        else{
            loadMiniJuego2();
        }

        res1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                puntuar(res1.getText().toString());
                if(mybundle.getInt("mj")==1){
                    next(1);
                }
                else{
                    next(2);
                }

            }
        });
        res2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                puntuar(res2.getText().toString());
                if(mybundle.getInt("mj")==1){
                    next(1);
                }
                else{
                    next(2);
                }
            }
        });
        res3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                puntuar(res3.getText().toString());
                if(mybundle.getInt("mj")==1){
                    next(1);
                }
                else{
                    next(2);
                }
            }
        });
        res4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                puntuar(res4.getText().toString());
                if(mybundle.getInt("mj")==1){
                    next(1);
                }
                else{
                    next(2);
                }
            }
        });

    }

    public void loadMiniJuego1(){
        titulo.setText(mybundle.getString("titulo"));
        Picasso.with(getApplicationContext())
                .load(mybundle.getString("poster"))
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(poster);
        MovieApiService movieApiService = retrofit.create(MovieApiService.class);
        Call<Trivia1Response> call = movieApiService.getTrivia1(mybundle.getString("imdbID"));
        call.enqueue(new Callback<Trivia1Response>() {
            @Override
            public void onResponse(Call<Trivia1Response> call, Response<Trivia1Response> response) {
                Preguntas = response.body().getResults();
                Log.d(TAG, "Number of movies received: " + Preguntas.size() +" mas primera pregunta: "+Preguntas.get(0).getPregunta());
                next(1);
            }
            @Override
            public void onFailure(Call<Trivia1Response> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });
    }

    public void loadMiniJuego2(){
        Picasso.with(getApplicationContext())
                .load(mybundle.getString("poster"))
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(poster);

        MovieApiService movieApiService = retrofit.create(MovieApiService.class);
        Call<Trivia1Response> call = movieApiService.getTrivia2();
        call.enqueue(new Callback<Trivia1Response>() {
            @Override
            public void onResponse(Call<Trivia1Response> call, Response<Trivia1Response> response) {
                Preguntas = response.body().getResults();
                Log.d(TAG, "Number of movies received: " + Preguntas.size() +" mas primera pregunta: "+Preguntas.get(0).getPregunta());
                next(2);
            }
            @Override
            public void onFailure(Call<Trivia1Response> call, Throwable throwable) {
                Log.e(TAG, throwable.toString());
            }
        });
    }

    public void next(int mj){
        contador++;
        Log.d(TAG, "contador: "+contador);
        if(contador<=9){
            if(mj==2){
                Picasso.with(getApplicationContext())
                        .load(Preguntas.get(contador).getPoster())
                        .placeholder(android.R.drawable.sym_def_app_icon)
                        .error(android.R.drawable.sym_def_app_icon)
                        .into(poster);
                titulo.setText(mybundle.getString("titulo"));
            }
            Trivia tr = Preguntas.get(contador);
            List<String> respuestas = new ArrayList<String>();
            //respuestas = null;
            respuestas.add(tr.getRespuestaC());
            respuestas.add(tr.getRespuestaI1());
            respuestas.add(tr.getRespuestaI2());
            respuestas.add(tr.getRespuestaI3());
            Collections.shuffle(respuestas);
            pre.setText(tr.getPregunta());
            res1.setText(respuestas.get(0));
            res2.setText(respuestas.get(1));
            res3.setText(respuestas.get(2));
            res4.setText(respuestas.get(3));
        }
        else{
            if(mj==1) {
                MovieApiService movieApiService = retrofit.create(MovieApiService.class);
                Call<String> call = movieApiService.puntuarMiniJuego1(mybundle.getString("imdbID"), userid, puntos);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String results = response.body();

                        Toast.makeText(MiniJuego1.this, "Puntos:  " + puntos + "  imbdID: " + mybundle.getString("imdbID") + " User_id: " + userid, Toast.LENGTH_SHORT).show();
                        Log.d(TAG, "------------Puntos:  " + puntos + "  imbdID: " + mybundle.getString("imdbID") + " User_id: " + userid + " Resultado: " + results + " ------------------");
                        Intent resutados = new Intent(MiniJuego1.this, ResultadoMiniJuego.class);
                        mybundle.putInt("puntos", puntos);
                        mybundle.putInt("correctas", resCorrectas);
                        mybundle.putInt("mejorCombo", mejorCombo);
                        mybundle.putString("puntosTotales", results);
                        mybundle.putString("titulo", mybundle.getString("titulo"));
                        mybundle.putString("poster", mybundle.getString("poster"));
                        resutados.putExtras(mybundle);
                        startActivity(resutados);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable throwable) {
                        Log.e(TAG, throwable.toString());
                    }
                });
            }
            else {
                MovieApiService movieApiService = retrofit.create(MovieApiService.class);
                Call<String> call = movieApiService.puntuarMiniJuego2(userid, puntos);
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String results = response.body();

                        Toast.makeText(MiniJuego1.this, "Puntos:  " + puntos, Toast.LENGTH_SHORT).show();
                        Intent resutados = new Intent(MiniJuego1.this, ResultadoMiniJuego.class);
                        mybundle.putInt("puntos", puntos);
                        mybundle.putInt("correctas", resCorrectas);
                        mybundle.putInt("mejorCombo", mejorCombo);
                        mybundle.putString("puntosTotales", results);
                        mybundle.putString("titulo", mybundle.getString("No va título"));
                        mybundle.putString("poster", mybundle.getString("Imagen para mostrar"));
                        resutados.putExtras(mybundle);
                        startActivity(resutados);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable throwable) {
                        Log.e(TAG, throwable.toString());
                    }
                });
            }
        }
    }
    public void puntuar(String respuesta){
        if(respuesta.equals(Preguntas.get(contador).getRespuestaC())){
            resCorrectas+=1;
            puntos+=10*combo;
            if(combo>mejorCombo)
                mejorCombo =  combo;
            combo++;
        }
        else{
            combo=1;
        }
    }
}