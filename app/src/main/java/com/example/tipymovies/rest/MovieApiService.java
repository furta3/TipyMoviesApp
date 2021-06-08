package com.example.tipymovies.rest;

import com.example.tipymovies.model.SearchMovieResponse;
import com.example.tipymovies.model.SearchUserResponse;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MovieApiService {

    //http://www.omdbapi.com/?s=texto
    //http://www.omdbapi.com/search?text=texto
    @GET(".")
    Call<SearchMovieResponse> search(@Query("apikey") String apiKey, @Query("s") String query);//, @Query("y") String year

    @GET("lista")
    Call<SearchMovieResponse> search2(@Query("texto_busqueda") String query, @Query("a") String a);

    @FormUrlEncoded
    @POST("api/user/login")
    Call<SearchUserResponse> searchuser(@Field("email") String name,
                                        @Field("password") String username);
}
