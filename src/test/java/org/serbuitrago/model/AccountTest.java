package org.serbuitrago.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class AccountTest {
	
	Account account = new Account("Sergio Stives Barrios Buitrago", BigDecimal.TEN);

	@Test
	void getName() {
		String actual = account.getName();
		String expected = "Sergio Stives Barrios Buitrago";
		
		assertEquals(expected, actual);
	}
		
	@Test
	void getMoney() {
		BigDecimal actual = account.getMoney();
		BigDecimal expected = BigDecimal.TEN;
		
		assertEquals(expected, actual);
	}	
	
	@Test
	void referenceMoney() {
		Account accountOne = new Account("Sergio Stives Barrios Buitrago", BigDecimal.TEN);
		Account accountTwo = new Account("Jonatan Javier Barrios Buitrago", BigDecimal.ONE);
		
		assertNotEquals(accountOne, accountTwo);
	}
	
	@Test
	void debit() {
		Account account = new Account("Sergio Stives Barrios Buitrago", new BigDecimal("1000.02"));
		account.debit(new BigDecimal("100"));
		
		Integer actual = account.getMoney().intValue();
		Integer expected = 900;
		
		assertNotNull(account.getMoney());
		assertEquals(expected, actual);
	}
	
	@Test
	void credit() {
		Account account = new Account("Sergio Stives Barrios Buitrago", new BigDecimal("1000.02"));
		account.credit(new BigDecimal("100"));
		
		Integer actual = account.getMoney().intValue();
		Integer expected = 1100;
		
		assertNotNull(account.getMoney());
		assertEquals(expected, actual);
	}
	
	@Test
	void referenceBank() {
		Account accountOne = new Account("Sergio Stives Barrios Buitrago", BigDecimal.TEN);
		Account accountTwo = new Account("Jonatan Javier Barrios Buitrago", BigDecimal.ONE);
		
		Bank bank = new Bank("SerBuitrago");
		bank.addAccount(accountOne);
		bank.addAccount(accountTwo);
		
		String actual = accountOne.getBank().getName();
		String expected = "SerBuitrago";
		
		assertNotNull(accountOne.getBank());
		assertEquals(expected, actual);
	}
}
