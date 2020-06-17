package com.example.viikko9;

public class Cinema {

    private String id;
    private String place;


    public Cinema(String id, String pla) {
        this.id = id;
        this.place = pla;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

}
