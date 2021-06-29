package com.example.tipymovies;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.tipymovies.model.SearchUserResponse;
import com.example.tipymovies.model.User;
import com.example.tipymovies.rest.MovieApiService;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
                Call<SearchUserResponse> call = movieApiService.searchuser(username.getText().toString(),password.getText().toString());
                call.enqueue(new Callback<SearchUserResponse>() {
                    @Override
                    public void onResponse(Call<SearchUserResponse> call, Response<SearchUserResponse> response) {
                        User user = response.body().getResult();
                        Log.d("Usuario",user.getUsername());
                        Toast.makeText(Login.this, "Logueaste con exito Master "+user.getUsername(), Toast.LENGTH_SHORT).show();
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
                });

            }
        });

    }
}