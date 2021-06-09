package com.example.tipymovies;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tipymovies.rest.MovieApiService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AgregarPregunta extends AppCompatActivity {
    Button AgregarP,MiniJuego1;
    EditText pre, resC,resI1,resI2,resI3;

    TextView titulo;

    public static final String TTPY_MOVIES_URL = Config.TTPY_MOVIES_URL;
    private static Retrofit retrofit = null;
    private final static String API_KEY = Config.API_KEY;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        pre = (EditText) findViewById(R.id.taPregunta);
        resC = (EditText) findViewById(R.id.tbResC);
        resI1 = (EditText) findViewById(R.id.tbResI1);
        resI2 = (EditText) findViewById(R.id.tbResI2);
        resI3 = (EditText) findViewById(R.id.tbResI3);
        titulo = (TextView) findViewById(R.id.tbTituloPeli);
        Bundle mybundle = this.getIntent().getExtras();
        String tit = "Agregando prefuta a "+ mybundle.getString("titulo");
        //titulo.setText(tit);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_pregunta);
        AgregarP = (Button) findViewById(R.id.btnAgragarPreguntaAceptar);
        AgregarP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (retrofit == null) {
                    retrofit = new Retrofit.Builder()
                            .baseUrl(TTPY_MOVIES_URL)//BASE_URL
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }

                MovieApiService movieApiService = retrofit.create(MovieApiService.class);
                movieApiService.agregarPregunta(mybundle.getString("imdbID"),pre.getText().toString(),resC.getText().toString(),resI1.getText().toString(),resI2.getText().toString(),resI3.getText().toString(),"a");
            }
        });
    }
}