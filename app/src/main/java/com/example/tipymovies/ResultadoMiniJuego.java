package com.example.tipymovies;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

public class ResultadoMiniJuego extends AppCompatActivity {
    Bundle mybundle;
    TextView rpt,rmc,rpo,rrc;
    Button btnAceptar;
    ImageView poster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_mini_juego);
        mybundle = this.getIntent().getExtras();
        poster = (ImageView) findViewById(R.id.posterResultado1);
        Picasso.with(getApplicationContext())
                .load(mybundle.getString("poster"))
                .placeholder(android.R.drawable.sym_def_app_icon)
                .error(android.R.drawable.sym_def_app_icon)
                .into(poster);
        btnAceptar = (Button) findViewById(R.id.btnAceptarRes);
        rpt = (TextView) findViewById(R.id.tvPT);
        rmc = (TextView) findViewById(R.id.tvMC);
        rpo = (TextView) findViewById(R.id.tvPO);
        rrc = (TextView) findViewById(R.id.tvRC);
        String puntos = ""+mybundle.getInt("puntos");
        String mejorCombo = ""+mybundle.getInt("mejorCombo");
        String correctas = ""+mybundle.getInt("correctas");
        rpt.setText(mybundle.getString("puntosTotales"));
        rmc.setText(mejorCombo);
        rpo.setText(puntos);
        rrc.setText(correctas);
        btnAceptar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                finish();
            }
        });
    }
}