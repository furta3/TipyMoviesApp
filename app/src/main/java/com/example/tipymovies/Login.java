package com.example.tipymovies;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Retrofit;

public class Login extends AppCompatActivity {
    Button ingresar;
    TextView username;
    public static final String TTPY_MOVIES_URL = Config.TTPY_MOVIES_URL;
    private static Retrofit retrofit = null;
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

        ingresar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intento = new Intent(Login.this,ListarPeliculas.class);
                startActivity(intento);
                finish();

                /*
                if (retrofit2 == null) {
                    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
                    retrofit2 = new Retrofit.Builder()
                            .baseUrl(TTPY_MOVIES_URL)//BASE_URL
                            .addConverterFactory(GsonConverterFactory.create())
                            .client(client)
                            .build();
                }

                MovieApiService movieApiService = retrofit2.create(MovieApiService.class);
                //Call<SearchMovieResponse> call = movieApiService.search(API_KEY,peli);

                JSONObject paramObject = new JSONObject();
                try {
                    paramObject.put("email","bf@gmail.com" );//username.getText().toString()
                    paramObject.put("password", "berni123");//password.getText().toString()
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                //paramObject.toRequestBody("application/json".toMediaTypeOrNull());
                Call<SearchUserResponse> call = movieApiService.searchuser(paramObject);
                //Call<SearchUserResponse> call = movieApiService.searchuser(username.getText().toString(),password.getText().toString());
                call.enqueue(new Callback<SearchUserResponse>() {
                    @Override
                    public void onResponse(Call<SearchUserResponse> call, Response<SearchUserResponse> response) {
                        User user = response.body().getResult();
                        if (user!=null){
                            Intent intento = new Intent(Login.this,ListarPeliculas.class);
                            startActivity(intento);
                            finish();
                        }else {
                        }
                    }
                    @Override
                    public void onFailure(Call<SearchUserResponse> call, Throwable throwable) {
                        Log.e(TAG, throwable.toString());
                    }
                });*/

            }
        });
    }
}