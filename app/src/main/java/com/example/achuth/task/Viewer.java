package com.example.achuth.task;

public class Viewer {
    private String name,Location;
    private int age,dpid;
    private boolean blocked;

    Viewer(String name,int age,int dpid,String Location)
    {
        this.name=name;
        this.age=age;
        this.dpid=dpid;
        this.Location=Location;
        blocked=false;
    }

    public void setName(String name) {
        this.name = name;
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


    public int getAge() {
        return age;
    }

    public String getName() {
        return name;
    }


    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        this.blocked = blocked;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }
}
