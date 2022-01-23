package org.serbuitrago.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BankTest {
	
	Bank bank;
	
	@BeforeEach
	void beforeEach() {
		System.out.println("Bank - @BeforeEach");
		this.bank = new Bank("SerBuitrago");
	}
	
	@AfterEach
	void afterEach() {
		System.out.println("Bank - @AfterEach");
	}
	
	@BeforeAll
	static void beforeAll() {
		System.out.println("Bank - @BeforeAll");
	}
	
	@AfterAll
	static void afterAll() {
		System.out.println("Bank - @AfterAll");
	}
	
	@Test
	@DisplayName("Probando el nombre del banco.")
	void getName() {
		String expected =  "SerBuitrago";
		String actual = this.bank.getName();
		
		assertNotNull(actual);
		assertEquals(expected, actual);
	}
	
	@Test
	@DisplayName("Probando la tranferencia del banco.")
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
	@DisplayName("Probando relacion entre el banco y cuenta.")
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
