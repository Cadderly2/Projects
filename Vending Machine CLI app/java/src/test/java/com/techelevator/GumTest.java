package com.techelevator;

import org.junit.Test;

import com.techelevator.model.Gum;
import com.techelevator.model.Item;

import java.math.BigDecimal;

import org.junit.Assert;

public class GumTest {
	
	@Test
	public void Can_Create_New_Gum() {
		Item sut = new Gum("test", new BigDecimal("69.00"));
		Assert.assertEquals("test", sut.getName());
		Assert.assertEquals(new BigDecimal("69.00"), sut.getPrice());
		Assert.assertEquals("Chew Chew, Yum!", sut.getPurchaseSound());
	}
	
}
