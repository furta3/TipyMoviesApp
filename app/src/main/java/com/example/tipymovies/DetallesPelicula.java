package com.example.tipymovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class DetallesPelicula extends AppCompatActivity {

    Button minijuego,agregarP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_pelicula);

        minijuego = (Button) findViewById(R.id.btnMinijuego1Detalles);
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
                Intent detalles = new Intent(DetallesPelicula.this,AgregarPregunta.class);
                startActivity(detalles);
            }
        });
    }
}