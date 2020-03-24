package com.techelevator;

import java.math.BigDecimal;

import java.util.Map;
import java.util.Scanner;

import com.techelevator.model.Slot;
import com.techelevator.model.VendingMachine;
import com.techelevator.view.Menu;

public class VendingMachineCLI {

	static Scanner userInput = new Scanner(System.in);

	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase items";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String MAIN_MENU_OPTION_SALES_REPORT = "Invisible";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE,
			MAIN_MENU_OPTION_EXIT, MAIN_MENU_OPTION_SALES_REPORT };

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY,
			PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION };

	private Menu menu;
	private static VendingMachine theVendingMachine;

	public VendingMachineCLI(Menu menu) {
		this.menu = menu;
	}

	public static void main(String[] args) {
		Menu menu = new Menu(System.in, System.out);
		theVendingMachine = new VendingMachine();
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}

	// Main menu

	public void run() {

		boolean done = false;
		while (done == false) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				displayItems();
			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {
				purchaseMenu();
			} else if (choice.equals(MAIN_MENU_OPTION_EXIT)) {
				done = true;
			} else if (choice.contentEquals(MAIN_MENU_OPTION_SALES_REPORT)) {
				salesReport();
			}
		}
		theVendingMachine.close();
	}

	// Purchase Menu

	private void purchaseMenu() {
		boolean done = false;
		while (done == false) {
			String choice = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

			if (choice.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
				addToBalance();
			} else if (choice.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
				handlePurchase();
			} else if (choice.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)) {
				System.out.println(theVendingMachine.makeChange());
				done = true;
			}
		}
	}

	// Display Items available

	private void displayItems() {
		Map<String, Slot> mySlots = theVendingMachine.getSlots();
		System.out.println("|--------------------------------|");
		System.out.println("|        Vendo-Matic-601         |");
		System.out.println("|--------------------------------|");
		System.out.println("|Slot|    Items           |Money |");
		System.out.println("|--------------------------------|");
		for (String s : mySlots.keySet()) {
			System.out.println(mySlots.get(s));
		}
		System.out.println("|--------------------------------|");
		System.out.print("|           [   ] ");
		
		
		System.out.format("%7s%7.02f%s\n","Balance" ,theVendingMachine.getCustomerBalance()," |");
		System.out.println("|--------------------------------|");
	}
	// Add to balance
	private void addToBalance() {
		System.out.print("How many dollar bills would you like to insert?  >>> ");
		boolean done = false;
		while (done == false) {
			String depositAmountStr = userInput.nextLine();
			try {
				if (Integer.parseInt(depositAmountStr) >= 0) {
					BigDecimal depositAmount = new BigDecimal(depositAmountStr);
					System.out.println(theVendingMachine.depositMoney(depositAmount));
					done = true;
				} else {
					System.out.print("You entered a negative number. Please try again >>> ");
				}
			} catch (NumberFormatException e) {
				System.out.print("Invalid currency. Please try again >>> ");
			}
		}
	}

	// Get slot number and pass and print purchase method
	private void handlePurchase() {
		displayItems();
		System.out.println("Enter slot number >>> ");
		String selection = userInput.nextLine().toUpperCase();
		System.out.println(theVendingMachine.purchase(selection));
	}

	private void salesReport() {
		System.out.println();
		theVendingMachine.GetSalesReport();
	}
}
