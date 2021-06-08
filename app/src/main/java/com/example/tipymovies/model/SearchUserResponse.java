package com.example.tipymovies.model;

import com.google.gson.annotations.SerializedName;

public class SearchUserResponse {
    @SerializedName("data")
    private User result;

    public User getResult() {
        return result;
    }
}
