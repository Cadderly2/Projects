package com.techelevator.model;

import java.math.BigDecimal;

public class MoneyChanger {

	public static String makeChange(BigDecimal customerBalance) {
		int numDollar = 0;
		int numQuarter = 0;
		int numDime = 0;
		int numNickel = 0;
		String result = "Your change is ";
		int balanceInt = 0;
		int penniesInDollar = 100;
		
		customerBalance = customerBalance.multiply(new BigDecimal(penniesInDollar));
		balanceInt = customerBalance.intValueExact();
		
		numDollar = balanceInt / 100;
		balanceInt = balanceInt % 100;
		if (numDollar > 0) {
			result += numDollar + " Dollar(s) ";
		}
		numQuarter = balanceInt / 25;
		balanceInt = balanceInt % 25; 
		if (numQuarter > 0) {
			result += numQuarter + " Quarter(s) ";
		}
		
		numDime = balanceInt / 10;
		balanceInt = balanceInt % 10;
		if (numDime > 0) {
			result += numDime + " Dime(s) ";
		}
		numNickel = balanceInt / 5;
		balanceInt = balanceInt % 5;
		if (numNickel > 0) {
			result += numNickel + " Nickel(s) ";
		}
		if (result == "Your change is ") {
			result = "No change could be made";
		}
		
		return result;
	}
}