package com.example.tipymovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Trivia1Response {
    @SerializedName("preguntas")
    private List<Trivia> results;

    public List<Trivia> getResults() {
        return results;
    }
}
