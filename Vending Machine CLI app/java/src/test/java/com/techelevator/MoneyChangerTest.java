package com.techelevator;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Test;

import com.techelevator.model.MoneyChanger;

public class MoneyChangerTest {

	@Test 
	public void Returns_21Dollars_50Cents() {
		Assert.assertEquals("Your change is 21 Dollar(s) 2 Quarter(s) ", MoneyChanger.makeChange(new BigDecimal("21.50")));
	}
	@Test
	public void Returns_45Cents() {
		Assert.assertEquals("Your change is 1 Quarter(s) 2 Dime(s) ", MoneyChanger.makeChange(new BigDecimal("0.45")));
	}
	@Test
	public void Returns_No_Cents() {
		Assert.assertEquals("No change could be made", MoneyChanger.makeChange(new BigDecimal("0.04")));
	}
	@Test
	public void Returns_15_Cents() {
		Assert.assertEquals("Your change is 1 Dime(s) 1 Nickel(s) ", MoneyChanger.makeChange(new BigDecimal("0.15")));
	}
}
