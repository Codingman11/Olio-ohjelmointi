package com.example.viikko8;
/**
 *
 * @author Jesse Peltola
 */
public class Bottle {

    private String name;
    private String manufacturer;
    private double total_energy;
    private double size;
    private double price;


    public Bottle(){
        this.name = "Pepsi Max";
        this.manufacturer = "Pepsi";
        this.size = 0.5;
        this.price = 1.8;
    }

    public Bottle(String name, String manuf, double size, double price){
        this.name = name;
        this.manufacturer = manuf;
        this.size = size;
        this.price = price;
    }

    public String getName(){ return this.name; }

    public String getManufacturer(){ return this.manufacturer; }

    public double getEnergy(){ return this.total_energy; }

    public double getPrice() { return this.price; }
    public double getSize() { return this.size; }
    @Override
    public String toString() { return this.name + ", " + this.size + "l, " + this.price + "€"; }

}

