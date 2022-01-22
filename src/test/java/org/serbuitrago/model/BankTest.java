package org.serbuitrago.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class BankTest {
	
	Bank bank = new Bank("SerBuitrago");
	
	@Test
	void getName() {
		String expected =  "SerBuitrago";
		String actual = this.bank.getName();
		
		assertNotNull(actual);
		assertEquals(expected, actual);
	}
	
	@Test
	void toTransfer() {
		Account origin = new Account("Sergio Stives Barrios Buitrago", new BigDecimal("200.000"));
		Account destination = new Account("Jonatan Javier Barrios Buitrago", new BigDecimal("100.000"));
		BigDecimal value = new BigDecimal("50.000");
		
		bank.toTransfer(origin, destination, value);
		
		Integer expectedOrigin =  150;
		Integer expectedDestination = 150;
		
		assertEquals(expectedOrigin, origin.getMoney().intValue());
		assertEquals(expectedDestination, destination.getMoney().intValue());	
	}
	
	@Test
	void addAccount() {
		Account account = new Account("Sergio Stives Barrios Buitrago", new BigDecimal("200.000"));
		bank.addAccount(account);
		bank.addAccount(account);
		
		Integer expected =  2;
		Integer actual = this.bank.getAccounts().size();
		
		assertNotNull(this.bank.getAccounts());
		assertEquals(expected, actual);
	}
}
