package com.example.tipymovies.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchUserResponse {
    @SerializedName("Search")
    private List<User> results;

    public List<User> getResults() {
        return results;
    }
}
