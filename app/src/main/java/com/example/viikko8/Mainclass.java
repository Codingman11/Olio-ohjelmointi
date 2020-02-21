package com.example.viikko8;

import java.util.Scanner;
public class Mainclass {

        public static void main(String[] args) {

            BottleDispenser boDis = BottleDispenser.getInstance();
            int choice = -1;
            while (choice != 0) {
                System.out.println("\n*** BOTTLE DISPENSER ***");
                System.out.println("1) Add money to the machine");
                System.out.println("2) Buy a bottle");
                System.out.println("3) Take money out");
                System.out.println("4) List bottles in the dispenser");
                System.out.println("0) End");

                Scanner sc = new Scanner(System.in);
                System.out.print("Your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        boDis.addMoney();
                        break;
                    case 2:
                        boDis.listMachine();
                        System.out.print("Your choice: ");
                        int ch = sc.nextInt();
                        boDis.buyBottle(ch);
                        break;
                    case 3:
                        boDis.returnMoney();
                        break;
                    case 4:
                        boDis.listMachine();
                        break;
                    case 0:
                        choice = 0;
                        break;
                }

            }

        }




}
