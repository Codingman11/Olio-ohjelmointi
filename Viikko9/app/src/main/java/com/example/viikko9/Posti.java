package com.example.viikko9;

public class Posti extends smartPost{

    private String id;
    private String name;
    private String city;
    private String address;
    private String avail;

    public Posti(String id, String name, String city, String address, String avail) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.address = address;
        this.avail = avail;
    }

    public String getID() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public String getAddress() {
        return this.address;
    }

    public String getAvail() {
        return this.avail;
    }

    @Override
    public String toString() {return this.name;}


}
