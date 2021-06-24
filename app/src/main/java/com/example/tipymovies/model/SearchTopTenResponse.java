package com.example.tipymovies.model;

import com.example.tipymovies.TopTen;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchTopTenResponse {

    @SerializedName("Search")
    private List<JoinTopTen> results;

    public List<JoinTopTen> getResults() {
        return results;
    }
}
