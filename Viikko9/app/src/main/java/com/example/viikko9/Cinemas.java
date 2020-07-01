package com.example.viikko9;

import java.util.ArrayList;

public class Cinemas {

    private static Cinemas cin = null;
    public ArrayList<Cinema> cinemas;


    public Cinemas() {
        cinemas = new ArrayList<>();
    }
    public static Cinemas getInstance() {
        if(cin == null) {
            cin = new Cinemas();
        }
        return cin;
    }


    public ArrayList<Cinema> getCinemas() {
        return cinemas;
    }

    public void addList(String id, String place, String cinema) {
        cinemas.add(new Cinema(id, place, cinema));
    }

    public void printList() {
        for(int i = 0; i < cinemas.size(); i++) {
            System.out.println(cinemas.get(i).getId() + " " + cinemas.get(i).getPlace());
        }
    }
}
