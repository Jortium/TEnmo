package com.techelevator;

import java.io.*;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.*;

public class VendingMachine {

    //Variables
    private double currentBalance;
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private AuditLogger audit = new AuditLogger();
    private ChangeDispenser change = new ChangeDispenser();
    private LoadInventory inventory = new LoadInventory();
    // Map to store our key(ProductNumber) and Value (Product Info Array)


    //Constructors



    public VendingMachine() throws IOException {
        this.inventory = createInventory();   // Everytime our Program runs or we instantiate a VendingMachine, we load the inventory
        this.currentBalance = 0.00;
    }

    public VendingMachine(double currentBalance){
        this.currentBalance = currentBalance;
    }

    public VendingMachine(){
        this.audit = getAuditLogger(String auditString);
    }

    public double getCurrentBalance() {
        return currentBalance;
    }

    //GETTERS AND SETTERS

    public double addMoney(int addMoney){
        currentBalance = currentBalance + addMoney;
        return getCurrentBalance();
    }

    public AuditLogger createAuditLog(String auditString) throws IOException {
        audit.auditLog(auditString);
        return audit;
    }

    public AuditLogger getAuditLogger(String auditString) throws IOException {
        return createAuditLog(auditString);
    }

    public LoadInventory createInventory() throws IOException {
        inventory.loadInventory();
        return inventory;
    }

    public LoadInventory getInventory() throws IOException{
        return createInventory();
    }

    public ChangeDispenser returnChange() throws IOException {
        change.dispenseChange(currentBalance);
        currentBalance = 0;
        return change;
    }

    public ChangeDispenser getChangeDispenser() throws IOException {
        return returnChange();
    }

    // ADDITIONAL METHODS
    // Load Items - Scans the data from the input file and adds it to the inventory Map


    // ADDITIONAL METHODS
    // Load Items - Scans the data from the input file and adds it to the inventory Map


    public void displayItems() throws IOException{
        Set<String> theKeys = inventory.loadInventory().keySet();  // Hold all the keys from the Map - keys are Strings in our Map
        for (String key : theKeys) { // Iterating through our Map values
            ArrayList<Product> products = inventory.loadInventory().get(key);
            if (products.size() > 0) { // I don't know if this works correctly for determining out of stock!!!!
                System.out.println(products.get(0).getProductNumber() + " | " + products.get(0).getProductName() + "|" + products.get(0).getProductCost() + "|" + products.get(0).getProductType() + "|" + products.size());
            } else {
                System.out.println(products.get(0).getProductName()+ "| 0 SOLD OUT");
            }
        }
    }

    public void feedMoney() throws IOException {
        while (true) {
            System.out.println("Your current balance is: $" + getCurrentBalance());
            Scanner userInput = new Scanner(System.in);
            System.out.print("Please insert money ($1, $5, $10) >>> $");
            String moneyFed = userInput.nextLine();
        }
    }

    public void selectProduct() {
        while (true) {
            Scanner usersSelection = new Scanner(System.in); // We get user input
            System.out.print("Hurry up and pick >>> "); // Print out to user
            String userInput = usersSelection.nextLine().toUpperCase(); // Assigning users input to string
            try {
                if (inventory.containsKey(userInput)) {
                    userInputToKeyValue(userInput);

                    System.out.println("Do you want to buy another item? (Y//N) ");
                    Scanner anotherItem = new Scanner(System.in);
                    String moreItems = anotherItem.nextLine().toUpperCase();
                    if (moreItems.equals("N")) {
                        break;
                    } else if (!moreItems.equals("Y")) {
                        System.out.println("Hey Hey give us a good answer!"); // WE WANT TO FIX THIS SO IT GOES BACK TO LINE 112
                    }
                } else {
                    System.out.println("Please choose a different option");
                }
            } catch (IndexOutOfBoundsException | IOException e) {
                System.out.println("Please choose a different option");
            }


        } // End of While Loop
    }

    public void userInputToKeyValue(String userInput) throws IOException {
        ArrayList checkMoney = inventory.get(userInput); // Taking the user's input, getting the value of that Key, and assigning it to a variable ArrayList
        Object price = checkMoney.get(0);                // Creating an Object assigning the value to one Product in the ArrayList
        double cost = ((Product) price).getProductCost(); // Creating a double to hold the cost value. Assigning it's value to the Object/Products cost
        Object product = checkMoney.get(0);
        String type = ((Product) product).getProductType();
        Object shelf = checkMoney.get(0);
        String number = ((Product) shelf).getProductNumber();
        Object snack = checkMoney.get(0);
        String name = ((Product) snack).getProductName();
        if (getCurrentBalance() >= cost) { // Checking if userInput exists in keys
            double auditBalance = getCurrentBalance() - cost;
            getAuditLogger(number + " " + name + " " + type + " " + getCurrentBalance() + " - " + cost + ": " + df2.format(auditBalance));
            currentBalance = getCurrentBalance() - cost;
            ArrayList dispensedProduct = inventory.get(userInput);
            dispensedProduct.remove(0);
            if (type.equals("Chip")) {
                System.out.println("Crunch Crunch, Yum");
            } else if (type.equals("Candy")) {
                System.out.println("Munch Munch, Yum!");
            } else if (type.equals("Drink")) {
                System.out.println("Glug Glug, Yum!");
            } else if (type.equals("Gum")) {
                System.out.println("Chew Chew, Yum!");
            } else {
                System.out.println("FrankyMcFrankFrank");
            }
        } else if (getCurrentBalance() < cost) {
            System.out.println("You don't have enough money for that item.");
        }
    }
}
