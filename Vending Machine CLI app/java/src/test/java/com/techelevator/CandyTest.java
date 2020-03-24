package com.techelevator;

import org.junit.Test;

import com.techelevator.model.Candy;
import com.techelevator.model.Item;

import java.math.BigDecimal;

import org.junit.Assert;

public class CandyTest {
	
	@Test
	public void Can_Create_New_Candy() {
		Item sut = new Candy("test", new BigDecimal("69.00"));
		Assert.assertEquals("test", sut.getName());
		Assert.assertEquals(new BigDecimal("69.00"), sut.getPrice());
		Assert.assertEquals("Munch Munch, Yum!", sut.getPurchaseSound());
	}
	
}
