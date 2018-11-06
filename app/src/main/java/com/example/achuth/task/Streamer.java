package com.example.achuth.task;

public class Streamer {
    private String name,genre;
    private int age;
    private boolean vstreamer,astreamer;

    Streamer(String name,String genre,int age,boolean vs,boolean as)
    {
        this.name=name;
        this.genre=genre;
        this.age=age;
        vstreamer=vs;
        astreamer=as;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAstreamer(boolean astreamer) {
        this.astreamer = astreamer;
    }

    public void setVstreamer(boolean vstreamer) {
        this.vstreamer = vstreamer;
    }

    public int getAge() {
        return age;
    }

    public String getGenre() {
        return genre;
    }

    public String getName() {
        return name;
    }

    public boolean isAstreamer() {
        return astreamer;
    }

    public boolean isVstreamer() {
        return vstreamer;
    }
}
