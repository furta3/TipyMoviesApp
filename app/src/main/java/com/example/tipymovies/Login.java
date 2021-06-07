package com.example.tipymovies;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Retrofit;


public class Login extends AppCompatActivity {
    Button ingresar;
    TextView username;


    TextView password;

    private static Retrofit retrofit2 = null;
    private static final String TAG = Login.class.getSimpleName();
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ingresar = (Button) findViewById(R.id.ingresar);
        username = (TextView) findViewById(R.id.usuario);
        password = (TextView) findViewById(R.id.contraseña);
/*     ingresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if (retrofit2 == null) {
                    retrofit2 = new Retrofit.Builder()
                            .baseUrl(TTPY_MOVIES_URL)//BASE_URL
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }

                MovieApiService movieApiService = retrofit2.create(MovieApiService.class);
                //Call<SearchMovieResponse> call = movieApiService.search(API_KEY,peli);
                Call<SearchUserResponse> call = movieApiService.searchuser(username.getText().toString(),password.getText().toString());
                call.enqueue(new Callback<SearchUserResponse>() {
                    @Override
                    public void onResponse(Call<SearchUserResponse> call, Response<SearchUserResponse> response) {
                        List<User> user = response.body().getResults();
                        if (user.isEmpty()){

                        }else {
                            Intent intento = new Intent(Login.this,ListarPeliculas.class);

                            startActivity(intento);
                            finish();
                        }


                    }
                    @Override
                    public void onFailure(Call<SearchUserResponse> call, Throwable throwable) {
                        Log.e(TAG, throwable.toString());
                    }
                });



            }
        });*/
        
    }
}