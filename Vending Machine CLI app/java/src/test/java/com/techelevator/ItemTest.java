package com.techelevator;

import org.junit.Test;

import com.techelevator.model.Item;

import java.math.BigDecimal;

import org.junit.Assert;

public class ItemTest {
	
	@Test
	public void canCreateNewItem() {
		Item sut = new Item("test", new BigDecimal("69.00"), "xxx");
		Assert.assertEquals("test", sut.getName());
		Assert.assertEquals(new BigDecimal("69.00"), sut.getPrice());
		Assert.assertEquals("xxx", sut.getPurchaseSound());
		
	}
	

}
