package com.example.achuth.task;

public class Streamer {
    private String name,genre;
    private int age,dpid;
    private boolean vstreamer,astreamer;

    Streamer(String name,String genre,int age,boolean vs,boolean as,int dpid)
    {
        this.name=name;
        this.genre=genre;
        this.age=age;
        vstreamer=vs;
        astreamer=as;
        this.dpid=dpid;
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

    public int getDpid() {
        return dpid;
    }

    public void setDpid(int dpid) {
        this.dpid = dpid;
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
