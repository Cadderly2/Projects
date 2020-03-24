package com.techelevator.model;

import java.math.BigDecimal;

public class Slot {

	private String slotId;
	private int quantity;
	private Item item;

	// Constructor

	public Slot(String name, int quantity, Item item) {
		super();
		this.slotId = name;
		this.quantity = quantity;
		this.item = item;
	}

	// Getters and Setters

	public String getSlotId() {
		return slotId;
	}

	public int getQuantity() {
		return quantity;
	}

	public Item getItem() {
		return item;
	}

	// Deduct stock
	public void deductStock() {
		quantity--;
	}

	@Override
	public String toString() {
		String result = "";

		if (quantity > 0) {
			result = String.format("%1s%4s|%-20s|$%-5.02f|","|", slotId, item.getName(), item.getPrice());
		} else {
			result = String.format("%1s%4s|%-20s|%s","|", slotId, "SOLD OUT","      |");

		}
		return result;
	}
}
