package com.example.tipymovies;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tipymovies.rest.MovieApiService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AgregarPregunta extends AppCompatActivity {
    public static final String TTPY_MOVIES_URL = Config.TTPY_MOVIES_URL;
    private static Retrofit retrofit = null;
    private final static String API_KEY = Config.API_KEY;

    Button AgregarP;
    EditText pre, resC,resI1,resI2,resI3;
    TextView tituloAgregar;
    Bundle mybundle ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_pregunta);

        mybundle = this.getIntent().getExtras();
        tituloAgregar = (TextView) findViewById(R.id.tvTituloAgregar);
        String tit = "Agregando pregunta a "+ mybundle.getString("titulo");
        tituloAgregar.setText(tit);


        AgregarP = (Button) findViewById(R.id.btnAgragarPreguntaAceptar);
        AgregarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Error", mybundle.getString("imdbID")+" hola");
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()

                            .baseUrl(TTPY_MOVIES_URL)//BASE_URL
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                }
                pre = (EditText) findViewById(R.id.taPregunta);
                resC = (EditText) findViewById(R.id.tbResC);
                resI1 = (EditText) findViewById(R.id.tbResI1);
                resI2 = (EditText) findViewById(R.id.tbResI2);
                resI3 = (EditText) findViewById(R.id.tbResI3);

                MovieApiService movieApiService = retrofit.create(MovieApiService.class);
                Call<String> call = movieApiService.agregarPregunta(mybundle.getString("imdbID"),pre.getText().toString(),resC.getText().toString(),resI1.getText().toString(),resI2.getText().toString(),resI3.getText().toString());
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String result = response.body();
                        Log.d("resultado",result);

                        if (result!=null){
                            Toast.makeText(AgregarPregunta.this, "Se cargo la pregunta con exito", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                    @Override
                    public void onFailure(Call<String> call, Throwable throwable) {
                        Log.e("Error", throwable.toString());
                    }
                });
            }
        });
    }
}