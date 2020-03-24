package com.techelevator;

import org.junit.Test;

import com.techelevator.model.Chip;
import com.techelevator.model.Item;

import java.math.BigDecimal;

import org.junit.Assert;

public class ChipTest {
	
	@Test
	public void Can_Create_New_Chip() {
		Item sut = new Chip("test", new BigDecimal("69.00"));
		Assert.assertEquals("test", sut.getName());
		Assert.assertEquals(new BigDecimal("69.00"), sut.getPrice());
		Assert.assertEquals("Crunch Crunch, Yum!", sut.getPurchaseSound());
	}
	
}
