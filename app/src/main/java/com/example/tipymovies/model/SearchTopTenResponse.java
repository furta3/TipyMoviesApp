package com.example.tipymovies.model;

import com.example.tipymovies.TopTen;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchTopTenResponse {

    @SerializedName("topten")
    private List<JoinTopTen> results;

    public List<JoinTopTen> getResults() {
        return results;
    }

    @SerializedName("toptentrivia")
    private List<JoinTopTen> results2;

    public List<JoinTopTen> getResults2() {
        return results2;
    }
}
