package com.alyndroid.pokemon.model;

public class Pokemon {
    private int id;
    private String name;
    private String url;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Pokemon(String name, String url) {
        this.name = name;
        this.url = url;
    }
}
