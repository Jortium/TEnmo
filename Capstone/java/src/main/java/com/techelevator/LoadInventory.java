package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class LoadInventory {
    private Map<String, ArrayList<Product>> inventory = new TreeMap<>();

    public Map<String, ArrayList<Product>> loadInventory() throws FileNotFoundException {
        File vmText = new File("vendingmachine.csv"); // Define input file
        Scanner vmScanner = new Scanner(vmText);      // Define Scanner to scan input file
        String vmString = "";                         // Define empty String to hold value of each line in input file

        while (vmScanner.hasNextLine()) {                     // While Loop to run through input file
            ArrayList<Product> products = new ArrayList<>();  // Defining Array List to hold Products
            vmString = vmScanner.nextLine();                  // Assigning input file lines to vmString
            String[] vmStringArray = vmString.split("\\|");   // Splitting and Assigning vmString values to our StringArray
            Product newProduct = new Product(vmStringArray[0], vmStringArray[1], Double.parseDouble(vmStringArray[2]), vmStringArray[3]);
            // Creating new Product and assigning it to our products ArrayList
            for (int i = 0; i < 5; i++) {
                products.add(newProduct);
            }
            this.inventory.put(vmStringArray[0], products); // Assigning Keys and products to our inventory Map
        }
        return inventory;
    }
}
