package com.example.jjmacbook.recyclercardview;

/**
 * Created by jjmacbook on 17/3/17.
 */

public class Movie {

    private String name;
    private int poster;

    public Movie(){

    }

    public Movie(String name, int poster) {
        this.name = name;
        this.poster = poster;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoster() {
        return poster;
    }

    public void setPoster(int poster) {
        this.poster = poster;
    }
}
