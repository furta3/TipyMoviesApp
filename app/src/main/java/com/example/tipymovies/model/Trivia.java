package com.example.tipymovies.model;

import com.google.gson.annotations.SerializedName;

public class Trivia {
    @SerializedName("id")
    private String id;
    @SerializedName("imdbID")
    private String imdbID;
    @SerializedName("pregunta")
    private String pregunta;
    @SerializedName("respuestaC")
    private String respuestaC;
    @SerializedName("respuestaI1")
    private String respuestaI1;
    @SerializedName("respuestaI2")
    private String respuestaI2;
    @SerializedName("respuestaI3")
    private String respuestaI3;
    @SerializedName("poster")
    private String poster;
    @SerializedName("titulo")
    private String titulo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public String getRespuestaC() {
        return respuestaC;
    }

    public void setRespuestaC(String respuestaC) {
        this.respuestaC = respuestaC;
    }

    public String getRespuestaI1() {
        return respuestaI1;
    }

    public void setRespuestaI1(String respuestaI1) {
        this.respuestaI1 = respuestaI1;
    }

    public String getRespuestaI2() {
        return respuestaI2;
    }

    public void setRespuestaI2(String respuestaI2) {
        this.respuestaI2 = respuestaI2;
    }

    public String getRespuestaI3() {
        return respuestaI3;
    }

    public void setRespuestaI3(String respuestaI3) {
        this.respuestaI3 = respuestaI3;
    }

    public String getPoster() { return poster; }

    public void setPoster(String poster) { this.poster = poster; }

    public String getTitulo() { return titulo; }

    public void setTitulo(String titulo) { this.titulo = titulo; }
}
