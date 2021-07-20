package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Product {


    //Variables
    private final String productName;
    private final double productCost;
    private final String productNumber;
    private final String productType;


    //Constructors

    public Product(String productNumber, String productName, double productCost, String productType) {
        this.productName   = productName;
        this.productCost   = productCost;
        this.productNumber = productNumber;
        this.productType   = productType;
    }

    //GETTERS AND SETTERS

    public String getProductName() {
        return productName;
    }

    public double getProductCost() {
        return productCost;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public String getProductType() {
        return productType;
    }
// ADDITIONAL METHODS

    }
