package com.example.tipymovies.rest;

import com.example.tipymovies.Config;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class API {
        private static <T> T builder(Class<T> endpoint) {
            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
            OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
            return new Retrofit.Builder()
                    .baseUrl(Config.API_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build()
                    .create(endpoint);
        }

        public static MovieApiService movies() {
            return builder(MovieApiService.class);
        }


}
