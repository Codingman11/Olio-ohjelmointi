package com.example.viikko9;

public class Cinema {

    private String id;
    private String place;
    private String cinema;

    public Cinema(String id, String pla, String cin) {
        this.id = id;
        this.place = pla;
        this.cinema = cin;

    }

    public String getCinema() {
        return cinema;
    }

    public void setCinema(String cinema) {
        this.cinema = cinema;
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

    @Override
    public String toString() {
        return place + ": " + cinema;
    }
}
