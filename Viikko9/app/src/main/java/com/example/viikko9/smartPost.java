package com.example.viikko9;

import java.util.ArrayList;

public class smartPost {

    private static smartPost sPost;
    ArrayList<Posti> postiLista = new ArrayList<Posti>();


    public smartPost( ) {

    }

    public static smartPost getInstance() {
        if (sPost == null) {
            sPost = new smartPost();
        }

        return sPost;
    }

    public void addSmartP(String id, String name, String city, String address, String avail) {
        postiLista.add(new Posti(id, name, city, address, avail));
    }

    public ArrayList<Posti> getPostiLista() {
        return postiLista;
    }
}
