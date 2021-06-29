package com.example.tipymovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class DetallesPelicula extends AppCompatActivity {

    Button minijuego,agregarP;
    TextView titulo,anio;
    ImageView poster;
    Bundle mybundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_pelicula);

        titulo = (TextView) findViewById(R.id.tituloDetalles);
        anio = (TextView) findViewById(R.id.dateDetalles);
        poster = (ImageView) findViewById(R.id.posterDetalles);
        minijuego = (Button) findViewById(R.id.btnMinijuego1Detalles);

         mybundle = this.getIntent().getExtras();

        if(mybundle != null){
            titulo.setText(mybundle.getString("titulo"));
            anio.setText(mybundle.getString("anio"));
            String image_url = mybundle.getString("poster");
            //https://m.media-amazon.com/images/M/MV5BNGNhMDIzZTUtNTBlZi00MTRlLWFjM2ItYzViMjE3YzI5MjljXkEyXkFqcGdeQXVyNzkwMjQ5NzM@._V1_SX300.jpg
            Picasso.with(getApplicationContext())
                    .load(image_url)
                    .placeholder(android.R.drawable.sym_def_app_icon)
                    .error(android.R.drawable.sym_def_app_icon)
                    .into(poster);
            //poster.setImageURI( mybundle.getString("poster"));
        }

        minijuego.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent detalles = new Intent(DetallesPelicula.this,MiniJuego1.class);
                startActivity(detalles);
            }
        });

        agregarP = (Button) findViewById(R.id.btnAgrPreguntaDetalles);
        agregarP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent agregarpregunta = new Intent(DetallesPelicula.this,AgregarPregunta.class);
                Bundle mybundle2 = new Bundle();
                mybundle2.putString("imdbID",mybundle.getString("imdbID"));
                mybundle2.putString("titulo",mybundle.getString("titulo"));
                mybundle2.putString("poster",mybundle.getString("poster"));
                agregarpregunta.putExtras(mybundle);
                startActivity(agregarpregunta);
            }
        });
    }
}