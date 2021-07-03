package com.example.tipymovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PreguntasReturn {
    @SerializedName("resultado")
    private String result;

    public String getResultado() {
        return result;
    }
}
