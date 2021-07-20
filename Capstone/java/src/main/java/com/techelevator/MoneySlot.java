package com.techelevator;

import java.io.IOException;
import java.util.Scanner;

public class MoneySlot extends VendingMachine{


    public MoneySlot() throws IOException {
    }

    public double feedMoney(String moneyFed) throws IOException{
                    if(moneyFed.equals("1") || moneyFed.equals("5") || moneyFed.equals("10")) {
            switch(moneyFed){
                case "1":
                    getAuditLog("FEED DOLLABILLZ: $1 " + getCurrentBalance());
                    break;
                case "5":
                    getAuditLog("FEED DOLLABILLZ: $5 " + getCurrentBalance());
                    break;
                case "10":
                    getAuditLog("FEED DOLLABILLZ: $10 " + getCurrentBalance());
                    break;
            }
            currentBalance = addMoney(Integer.parseInt(moneyFed)); // Converting the String to Int and adding it to the currentBalance
            System.out.println("Current Money Provided: $" + getCurrentBalance());
            Scanner userInput2 = new Scanner(System.in);
            System.out.print("Continue money insertion (Y / N): ");
            String continueInput = userInput2.nextLine();
            if (continueInput.toUpperCase().equals("N")) {
                break;
            }
        }
            else {
            System.out.println("ERROR! YOU GOOFED! Invalid amount please insert $1, $5, $10\n");
        }
    }
}
