package com.techelevator.model;

import java.math.BigDecimal;

public class Item {

	private String name;
	private BigDecimal price = new BigDecimal("0.00");
	public String purchaseSound;
	public int amountSold;
	
	// Constructor
	
	public Item(String name, BigDecimal price, String purchaseSound) {
		super();
		this.name = name;
		this.price = price;
		this.purchaseSound = purchaseSound;
		
	}
	
	// Getters and Setters
	
	public String getName() {
		return name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public String getPurchaseSound() {
		return purchaseSound;
	}
	public int soldOne() {
		return amountSold ++;
	}
	public void setAmountSold(int amountSold) {
		this.amountSold = amountSold;
	}
	
 
	
}
