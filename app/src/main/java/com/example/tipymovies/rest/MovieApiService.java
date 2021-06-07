package com.example.tipymovies.rest;

import com.example.tipymovies.model.SearchMovieResponse;
import com.example.tipymovies.model.SearchUserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MovieApiService {

    //http://www.omdbapi.com/?s=texto
    //http://www.omdbapi.com/search?text=texto
    @GET(".")
    Call<SearchMovieResponse> search(@Query("apikey") String apiKey, @Query("s") String query);//, @Query("y") String year

    @GET("lista")
    Call<SearchMovieResponse> search2(@Query("texto_busqueda") String query, @Query("a") String a);

    @POST("user/login")
    Call<SearchUserResponse> searchuser(@Query("email") String email, @Query("password") String password);
}
