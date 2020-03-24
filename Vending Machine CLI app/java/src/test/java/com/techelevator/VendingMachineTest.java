package com.techelevator;

import java.util.Map;

import org.junit.Test;
import org.junit.Assert;

import com.techelevator.model.Slot;
import com.techelevator.model.VendingMachine;

public class VendingMachineTest {

	@Test
	public void Can_Create_Vending_Machine() {
		VendingMachine sut = new VendingMachine();
		Map<String, Slot> mySlots = sut.getSlots();
		Assert.assertEquals(16, mySlots.size());
		for (String s : mySlots.keySet()) {
			Assert.assertEquals(5, mySlots.get(s).getQuantity());
		} 
	}
}