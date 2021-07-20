package com.techelevator;
/**************************************************************************************************************************
*  This is your Vending Machine Command Line Interface (CLI) class
*
*  It is the main process for the Vending Machine
*
*  THIS is where most, if not all, of your Vending Machine interactions should be coded
*  
*  It is instantiated and invoked from the VendingMachineApp (main() application)
*  
*  Your code vending machine related code should be placed in here
***************************************************************************************************************************/
import com.techelevator.view.Menu;         // Gain access to Menu class provided for the Capstone

import java.io.*;
import java.text.DecimalFormat;

public class VendingMachineCLI {

	private static DecimalFormat df2 = new DecimalFormat("#.##");


	// Main menu options defined as constants
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS,
			MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_EXIT};

	private static final String SUB_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String SUB_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String SUB_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] SUB_MENU_OPTIONS = {SUB_MENU_OPTION_FEED_MONEY,
			SUB_MENU_OPTION_SELECT_PRODUCT,
			SUB_MENU_OPTION_FINISH_TRANSACTION};

	private Menu vendingMenu;// Menu object to be used by an instance of this class
	private VendingMachine vendingMachine;

	public VendingMachineCLI(Menu menu) throws IOException {  // Constructor - user will pass a menu for this class to use
		this.vendingMenu = menu;           // Make the Menu the user object passed, our Menu
		this.vendingMachine = new VendingMachine(); // Instantiating a new VendingMachine Object - This also loads the Inventory

	}

	/**************************************************************************************************************************
	 *  VendingMachineCLI main processing loop
	 *
	 *  Display the main menu and process option chosen
	 *
	 *  It is invoked from the VendingMachineApp program
	 *
	 *  THIS is where most, if not all, of your Vending Machine objects and interactions
	 *  should be coded
	 *
	 *  Methods should be defined following run() method and invoked from it
	 *
	 ***************************************************************************************************************************/

	public void run() throws IOException {

		boolean shouldProcess = true;         // Loop control variable

		while (shouldProcess) {                // Loop until user indicates they want to exit

			String choice = (String) vendingMenu.getChoiceFromOptions(MAIN_MENU_OPTIONS);  // Display menu and get choice

			switch (choice) {                  // Process based on user menu choice

				case MAIN_MENU_OPTION_DISPLAY_ITEMS:
					displayItems();           // invoke method to display items in Vending Machine
					break;                    // Exit switch statement

				case MAIN_MENU_OPTION_PURCHASE:
					purchaseItems();          // invoke method to purchase items from Vending Machine
					break;                    // Exit switch statement

				case MAIN_MENU_OPTION_EXIT:
					endMethodProcessing();    // Invoke method to perform end of method processing
					shouldProcess = false;    // Set variable to end loop
					break;                    // Exit switch statement
			}
		}
		return;// End method and return to caller
	} // End of Run Method

	public void runSub() throws IOException {
		System.out.print("\nCurrent Money Provided: $" + vendingMachine.getCurrentBalance());

		boolean shouldProcess = true;         // Loop control variable

		while (shouldProcess) {                // Loop until user indicates they want to exit

			String choice = (String) vendingMenu.getChoiceFromOptions(SUB_MENU_OPTIONS);  // Display menu and get choice

			switch (choice) {                  // Process based on user menu choice

				case SUB_MENU_OPTION_FEED_MONEY:
					feedMoney();           // invoke method to display items in Vending Machine
					break;                    // Exit switch statement

				case SUB_MENU_OPTION_SELECT_PRODUCT:
					selectProduct();          // invoke method to purchase items from Vending Machine
					break;                    // Exit switch statement

				case SUB_MENU_OPTION_FINISH_TRANSACTION:
					vendingMachine.returnChange();    // Invoke method to perform end of method processing
					shouldProcess = false;    // Set variable to end loop
					break;                    // Exit switch statement
			}
		}
		return;// End method and return to caller
	} // End of Run Method


	/********************************************************************************************************
	 * Methods used to perform processing
	 ********************************************************************************************************/
	public void displayItems() throws IOException {
		vendingMachine.displayItems(); // Calling the method to display our items
	}

	public void purchaseItems() throws IOException {
		runSub();
	}

	public void feedMoney()	{
		try {
			vendingMachine.feedMoney();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.print("\nCurrent Money Provided: $" + vendingMachine.getCurrentBalance());
	}

	public void selectProduct() throws IOException {
		vendingMachine.displayItems();
		vendingMachine.selectProduct();
		System.out.print("\nCurrent Money Provided: $" + vendingMachine.getCurrentBalance());
	}


	public void endMethodProcessing() { // static attribute used as method is not associated with specific object instance
		// Any processing that needs to be done before method ends
	}




}
