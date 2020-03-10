package com.example.viikko8;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;


/**
 *
 * @author jesse
 */
public class BottleDispenser {

    private static BottleDispenser bDis = null;

    // The array for the Bottle-objects

    public ArrayList<Bottle> bottle_array;

    private double money;


    public BottleDispenser() {

        this.money = 0;

        bottle_array = new ArrayList();

        bottle_array.add(new Bottle());
        bottle_array.add(new Bottle("Pepsi Max", "Pepsi", 1.5, 2.2));
        bottle_array.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 0.5, 2));
        bottle_array.add(new Bottle("Coca-Cola Zero", "Coca-Cola", 1.5, 2.5));
        bottle_array.add(new Bottle("Fanta Zero", "Fanta", 0.5, 1.95));

    }

    public static BottleDispenser getInstance() {
        if (bDis == null) {
            bDis = new BottleDispenser();
        }
        return bDis;
    }

    public void addMoney() {
        this.money += 1;
        System.out.println("Klink! Added more money!");
    }

    public void buyBottle(int ch) {
        Bottle bottle = bottle_array.get(ch - 1);
        if (this.money < bottle.getPrice()) {
            System.out.println("Add money first!");
        } else {
            this.money -= bottle.getPrice();
            bottle_array.remove(ch - 1);
            System.out.println("KACHUNK! "  + bottle.getName() + " came out of the dispenser!");
        }
    }

    public void listMachine() {
        for (int i = 0; i < bottle_array.size(); i++) {
            Bottle bottle = bottle_array.get(i);
            System.out.println(i + 1 + ". " + "Name: " + bottle.getName());
            System.out.println("\tSize: " + bottle.getSize() + "\tPrice: " + bottle.getPrice());
        }

    }

    public void returnMoney() {
        NumberFormat nf = new DecimalFormat("#0.00");
        double mon = Math.round(this.money * 100.0) / 100.0;
        System.out.println("Klink klink. Money came out! You got " +  nf.format(mon).replace(".", ",") + "€ back");
    }

    public double getMoney(){ return this.money; }

    public ArrayList<Bottle> getArray() { return this.bottle_array; }
    }
