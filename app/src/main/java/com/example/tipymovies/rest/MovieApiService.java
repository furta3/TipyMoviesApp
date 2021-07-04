package com.example.tipymovies.rest;

import com.example.tipymovies.model.SearchMovieResponse;
import com.example.tipymovies.model.SearchTopTenResponse;
import com.example.tipymovies.model.SearchUserResponse;
import com.example.tipymovies.model.Trivia1Response;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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

    @GET("api/RankingMovil")
    Call<SearchTopTenResponse> toptenJoin();

    @FormUrlEncoded
    @POST("api/user/login")
    Call<SearchUserResponse> searchuser(@Field("email") String name,
                                        @Field("password") String username);
    //Call<SearchUserResponse> searchuser(@Body JSONObject body);

    @GET("api/agregarPregunta")
    Call<String> agregarPregunta(@Query("imdbID") String imdbID, @Query("pregunta") String pregunta, @Query("respuestaC") String respuestaC, @Query("respuestaI1") String respuestaI1, @Query("respuestaI2") String respuestaI2, @Query("respuestaI3") String respuestaI3);

    @GET("api/MiniJuego1")
    Call<Trivia1Response> getTrivia1(@Query("imdbID") String imdbID);

    @GET("api/PuntuarMiniJuego1")
    Call<String> puntuarMiniJuego1(@Query("imdbID") String imdbID, @Query("user_id") String user_id, @Query("puntos") int puntos);
}
