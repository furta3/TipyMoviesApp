package com.example.tipymovies.model;

import com.google.gson.annotations.SerializedName;
import com.example.tipymovies.TopTen;

import java.util.List;

public class JoinTopTen {

        //@SerializedName("user_id")
        //private String userid;
        @SerializedName("username")
        private String username;
        @SerializedName("puntos")
        private String puntos;


        public JoinTopTen(/*String userid,*/String username, String puntos) {
            //this.userid = userid;
            this.username = username;
            this.puntos = puntos;
        }

    /*public String getUserid() {
        return userid;
    }*/

    /*public void setUserid(String userid) {
        this.userid = userid;
    }*/

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPuntos() {
        return puntos;
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
    }
}
