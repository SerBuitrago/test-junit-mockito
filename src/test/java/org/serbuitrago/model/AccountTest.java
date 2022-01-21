package org.serbuitrago.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class AccountTest {
	
	Account account = new Account("Sergio Stives", "Barrios Buitrago", BigDecimal.TEN);

	@Test
	void test_name() {
		String actual = account.getName();
		String expected = "Sergio Stives";
		assertEquals(expected, actual);
	}
	
	@Test
	void test_subname() {
		String actual = account.getSubname();
		String expected = "Barrios Buitrago";
		assertEquals(expected, actual);
	}
	
	@Test
	void test_money() {
		BigDecimal actual = account.getMoney();
		BigDecimal expected = BigDecimal.TEN;
		assertEquals(expected, actual);
	}	
	
	
	@Test
	void test_reference_money() {
		Account accountOne = new Account("Sergio Stives", "Barrios Buitrago", BigDecimal.TEN);
		Account accountTwo = new Account("Jonatan Javier", "Barrios Buitrago", BigDecimal.ONE);
		
		assertNotEquals(accountOne, accountTwo);
	}
	
	@Test
	void test_debit() {
		Account account = new Account("Sergio Stives", "Barrios Buitrago", new BigDecimal("1000.02"));
		account.debit(new BigDecimal("100"));
		assertNotNull(account.getMoney());
		assertEquals(900, account.getMoney().intValue());
	}
	
	@Test
	void test_credit() {
		Account account = new Account("Sergio Stives", "Barrios Buitrago", new BigDecimal("1000.02"));
		account.credit(new BigDecimal("100"));
		assertNotNull(account.getMoney());
		assertEquals(1100, account.getMoney().intValue());
	}
}
