package com.techelevator;

import org.junit.Test;

import com.techelevator.model.Drink;
import com.techelevator.model.Item;

import java.math.BigDecimal;

import org.junit.Assert;

public class DrinkTest {
	
	@Test
	public void Can_Create_New_Drink() {
		Item sut = new Drink("test", new BigDecimal("69.00"));
		Assert.assertEquals("test", sut.getName());
		Assert.assertEquals(new BigDecimal("69.00"), sut.getPrice());
		Assert.assertEquals("Glug Glug, Yum!", sut.getPurchaseSound());
	}
	
}
