package org.serbuitrago.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class AccountTest {

	Account account = new Account("Sergio Stives Barrios Buitrago", BigDecimal.TEN);

	@Test
	@DisplayName("Probando el nombre de la cuenta.")
	void getName() {
		String actual = account.getName();
		String expected = "Sergio Stives Barrios Buitrago";

		assertEquals(expected, actual);
	}

	@Test
	@Disabled
	@DisplayName("Probando la plata de la cuenta.")
	void getMoney() {
		fail();
		BigDecimal actual = account.getMoney();
		BigDecimal expected = BigDecimal.TEN;

		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Probando el debito de la cuenta.")
	void debit() {
		Account account = new Account("Sergio Stives Barrios Buitrago", new BigDecimal("1000.02"));
		account.debit(new BigDecimal("100"));

		Integer actual = account.getMoney().intValue();
		Integer expected = 900;

		assertNotNull(account.getMoney());
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Probando el credito de la cuenta.")
	void credit() {
		Account account = new Account("Sergio Stives Barrios Buitrago", new BigDecimal("1000.02"));
		account.credit(new BigDecimal("100"));

		Integer actual = account.getMoney().intValue();
		Integer expected = 1100;

		assertNotNull(account.getMoney());
		assertEquals(expected, actual);
	}

	@Test
	@DisplayName("Probando la relacion entre la cuenta y banco.")
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
	@DisplayName("Probando mensajes personalizados y la relacion entre la cuenta y banco.")
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

	@Test
	@DisplayName("Probando mensajes personalizados con lambda y la relacion entre la cuenta y banco.")
	void referenceBankAssertAllLambdaMessage() {
		Account accountOne = new Account("Sergio Stives Barrios Buitrago", BigDecimal.TEN);
		Account accountTwo = new Account("Jonatan Javier Barrios Buitrago", BigDecimal.ONE);

		Bank bank = new Bank("SerBuitrago");
		bank.addAccount(accountOne);
		bank.addAccount(accountTwo);

		String expectedBank = "SerBuitrago";
		String expectedName = "Sergio Stives Barrios Buitrago";

		assertAll(() -> {
			assertNotNull(accountOne.getBank(), () -> "El banco no puede estar nulo en la cuenta 1.");
			assertNotNull(accountTwo.getBank(), () -> "El banco no puede estar nulo en la cuenta 2.");
		}, () -> {
			assertEquals(expectedBank, accountOne.getBank().getName(),
					() -> "El nombre del banco no es el esperado en la cuenta 1.");
			assertEquals(expectedBank, accountTwo.getBank().getName(),
					() -> "El nombre del banco no es el esperado en la cuenta 2.");
		}, () -> {
			assertEquals(expectedName, bank.getAccounts().stream().filter(a -> a.getName().equals(accountOne.getName()))
					.findFirst().get().getName(), () -> "El nombre de la cuenta no fue el esperado.");
		}, () -> {
			assertTrue(bank.getAccounts().stream().anyMatch(a -> a.getName().equals(expectedName)),
					() -> "El nombre de la cuenta no fue el esperado.");
		});
	}
}
