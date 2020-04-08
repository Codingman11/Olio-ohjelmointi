package com.example.viikko9;

import java.util.ArrayList;

public class smartPost {

    private static smartPost sPost;
    ArrayList<Posti> postiLista;
    ArrayList<String> lista = new ArrayList<>();


    public smartPost( ) {
        postiLista = new ArrayList<>();
    }

    public static smartPost getInstance() {
        if (sPost == null) {
            sPost = new smartPost();
        }

        return sPost;
    }

    public void addSmartP(String id, String name, String city, String address, String avail) {
        postiLista.add(new Posti(id, name, city, address, avail));
        lista.add(name);
    }

    public ArrayList<Posti> getPostiLista() {
        return postiLista;
    }

    public ArrayList<String> getNameLista() {
        return lista;
    }
}
