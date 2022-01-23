package org.serbuitrago.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

	@Test
	void referenceBankStreamFilter() {
		Account accountOne = new Account("Sergio Stives Barrios Buitrago", BigDecimal.TEN);

		Bank bank = new Bank("SerBuitrago");
		bank.addAccount(accountOne);

		String actual = bank.getAccounts().stream().filter(a -> a.getName().equals(accountOne.getName())).findFirst()
				.get().getName();
		String expected = "Sergio Stives Barrios Buitrago";

		assertEquals(expected, actual);
	}

	@Test
	void referenceBankStreamMatch() {
		Account accountOne = new Account("Sergio Stives Barrios Buitrago", BigDecimal.TEN);

		Bank bank = new Bank("SerBuitrago");
		bank.addAccount(accountOne);

		String expected = "Sergio Stives Barrios Buitrago";

		assertTrue(bank.getAccounts().stream().anyMatch(a -> a.getName().equals(expected)));
	}

	@Test
	void referenceBankAssertAll() {
		Account accountOne = new Account("Sergio Stives Barrios Buitrago", BigDecimal.TEN);
		Account accountTwo = new Account("Jonatan Javier Barrios Buitrago", BigDecimal.ONE);

		Bank bank = new Bank("SerBuitrago");
		bank.addAccount(accountOne);
		bank.addAccount(accountTwo);

		String expectedBank = "SerBuitrago";
		String expectedName = "Sergio Stives Barrios Buitrago";

		assertAll(() -> {
			assertNotNull(accountOne.getBank());
			assertNotNull(accountTwo.getBank());
		}, () -> {
			assertEquals(expectedBank, accountOne.getBank().getName());
			assertEquals(expectedBank, accountTwo.getBank().getName());
		}, () -> {
			assertEquals(expectedName, bank.getAccounts().stream().filter(a -> a.getName().equals(accountOne.getName()))
					.findFirst().get().getName());
		}, () -> {
			assertTrue(bank.getAccounts().stream().anyMatch(a -> a.getName().equals(expectedName)));
		});
	}

	@Test
	void referenceBankAssertAllMessage() {
		Account accountOne = new Account("Sergio Stives Barrios Buitrago", BigDecimal.TEN);
		Account accountTwo = new Account("Jonatan Javier Barrios Buitrago", BigDecimal.ONE);

		Bank bank = new Bank("SerBuitrago");
		bank.addAccount(accountOne);
		bank.addAccount(accountTwo);

		String expectedBank = "SerBuitrago";
		String expectedName = "Sergio Stives Barrios Buitrago";

		assertAll(() -> {
			assertNotNull(accountOne.getBank(), "El banco no puede estar nulo en la cuenta 1.");
			assertNotNull(accountTwo.getBank(), "El banco no puede estar nulo en la cuenta 2.");
		}, () -> {
			assertEquals(expectedBank, accountOne.getBank().getName(),
					"El nombre del banco no es el esperado en la cuenta 1.");
			assertEquals(expectedBank, accountTwo.getBank().getName(),
					"El nombre del banco no es el esperado en la cuenta 2.");
		}, () -> {
			assertEquals(expectedName, bank.getAccounts().stream().filter(a -> a.getName().equals(accountOne.getName()))
					.findFirst().get().getName(), "El nombre de la cuenta no fue el esperado.");
		}, () -> {
			assertTrue(bank.getAccounts().stream().anyMatch(a -> a.getName().equals(expectedName)),
					"El nombre de la cuenta no fue el esperado.");
		});
	}
}
