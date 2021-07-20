package com.techelevator;

import java.io.FileNotFoundException;

public class ChangeDispenser{

    //Variables
    private double change;
    private int quarter;
    private int dime;
    private int nickle;

    public ChangeDispenser() {
        this.change = change;
    }

    //Constructors


    //GETTERS AND SETTERS

    public int getQuarter() {
        return quarter;
    }

    public int getDime() {
        return dime;
    }

    public int getNickle() {
        return nickle;
    }

    // ADDITIONAL METHODS

    public double dispenseChange(double change) {
        int quarter = 0;
        int dime = 0;
        int nickel = 0;
        while (change >= 0) {
            if (change >= 0.25) {
                quarter++;
                change = change - 0.25;
            } else if (change >= 0.10) {
                dime++;
                change = change - 0.10;
            } else if (change >= 0.05) {
                nickel++;
                change = change - 0.05;
            } else {
                System.out.println("Your change is: " + quarter + " quarter(s), " + dime + " dime(s), " + nickel + " nickel(s).");
                break;
            }
        }
        return change;
    }
}
