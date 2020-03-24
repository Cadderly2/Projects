package com.techelevator.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachine {
	static final String INVENTORY_FILE_NAME = "VendingMachine.txt";
	static final String CHIP_NAME = "Chip";
	static final String CANDY_NAME = "Candy";
	static final String DRINK_NAME = "Drink";
	static final String GUM_NAME = "Gum";

	static final String AUDIT_LOG_FILE_NAME = "log.txt"; 

	Map<String, Slot> slots = new LinkedHashMap<>();
	BigDecimal totalRevenue = new BigDecimal("0.00");
	BigDecimal customerBalance = new BigDecimal("0.00");
	AuditWriter myAuditWriter = new AuditWriter(AUDIT_LOG_FILE_NAME);

	public VendingMachine() {
		// get the data from the file
		File inventoryFile = new File(INVENTORY_FILE_NAME);

		if (inventoryFile.exists() && inventoryFile.isFile()) {

			try {
				Scanner scanner = new Scanner(inventoryFile);
				while (scanner.hasNextLine()) {
					String line = scanner.nextLine();
					String[] myPartsOfString = line.split("\\|");
					String slotId = myPartsOfString[0].trim().toUpperCase();
					String itemName = myPartsOfString[1].trim();
					String price = myPartsOfString[2].trim();
					String itemType = myPartsOfString[3].trim();

					// Sort and add them

					if (itemType.equals(CHIP_NAME)) {
						slots.put(slotId, new Slot(slotId, 5, new Chip(itemName, new BigDecimal(price))));
					} else if (itemType.equals(CANDY_NAME)) {
						slots.put(slotId, new Slot(slotId, 5, new Candy(itemName, new BigDecimal(price))));
					} else if (itemType.equals(DRINK_NAME)) {
						slots.put(slotId, new Slot(slotId, 5, new Drink(itemName, new BigDecimal(price))));
					} else if (itemType.equals(GUM_NAME)) {
						slots.put(slotId, new Slot(slotId, 5, new Gum(itemName, new BigDecimal(price))));
					} else {
						System.out.println("Error: invalid item type.");
					}
				}
			} catch (FileNotFoundException e) {
				System.out.println("Inventory File" + INVENTORY_FILE_NAME + "File open error.");
			}
		} else {
			System.out.println("Inventory File" + INVENTORY_FILE_NAME + " Missing!");
		}
	}

	public String depositMoney(BigDecimal toDeposit) {
		String result = "";
		customerBalance = customerBalance.add(toDeposit);
		result = ("Your new balance is: " + "$" + customerBalance);
		myAuditWriter.printLine("FEED MONEY: $" + toDeposit + " $" + customerBalance);
		return result;
	}

	// Getters and Setters

	public Map<String, Slot> getSlots() {
		return slots;
	}

	public BigDecimal getTotalRevenue() {
		return totalRevenue;
	}

	// Check quantity and compare balance and price and call to deduct quantity
	public String purchase(String slotId) {
		String result = "";
		if (slots.containsKey(slotId)) {
			BigDecimal price = slots.get(slotId).getItem().getPrice();
			if (slots.get(slotId).getQuantity() == 0) {
				result = "This item is sold out, try again later.";
			} else if (customerBalance.compareTo(price) >= 0) {
				slots.get(slotId).deductStock();
				customerBalance = customerBalance.subtract(price);
				slots.get(slotId).getItem().soldOne();
				result = (slots.get(slotId).getItem().getPurchaseSound());
				myAuditWriter.printLine(slots.get(slotId).getItem().getName() + " " + slots.get(slotId).getSlotId()
						+ " $" + price + " $" + customerBalance);
				totalRevenue = totalRevenue.add(price);

			} else {
				result = "Sorry, insufficient funds.";
			}
		} else {
			result = "The option selected, does not exist.";
		}
		return result;
	}

	// Call change Maker set balance to 0 return change
	public String makeChange() {
		String result = MoneyChanger.makeChange(customerBalance);
		BigDecimal customerBalanceOld = customerBalance;
		customerBalance = customerBalance.subtract(customerBalance);
		myAuditWriter.printLine("GIVE CHANGE: $" + customerBalanceOld + " $" + customerBalance);
		return result;
	}

	public boolean isExists(String slotId) {
		return slots.containsKey(slotId);
	}

	public BigDecimal getCustomerBalance() {
		return customerBalance;
	}

	public void close() {
		myAuditWriter.closePrinter();
	}

	public void GetSalesReport() {
		for (String s : slots.keySet()) {
			System.out.println(slots.get(s).getItem().getName() + "| " + 
		slots.get(s).getItem().amountSold);
		}
		System.out.println();
		System.out.println("***TOTAL SALES***");
		System.out.println("$" + totalRevenue);
	}
}