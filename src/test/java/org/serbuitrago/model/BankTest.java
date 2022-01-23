package org.serbuitrago.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
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

	@Nested
	@DisplayName("Clase que permite probar el nombre del banco.")
	class BankName {
		@Test
		@DisplayName("Probando el nombre del banco.")
		void getName() {
			String expected = "SerBuitrago";
			String actual = bank.getName();

			assertNotNull(actual);
			assertEquals(expected, actual);
		}

		@Test
		@DisplayName("Probando el nombre del banco con una condicion si el proyecto esta en desarrollo.")
		void getNameIsDev() {
			boolean isDev = "dev".equals(System.getProperty("ENV"));
			assumeTrue(isDev);

			String expected = "SerBuitrago";
			String actual = bank.getName();

			assertNotNull(actual);
			assertEquals(expected, actual);
		}

		@Test
		@DisplayName("Probando el nombre del banco con una condicion si el proyecto esta en desarrollo y una funcion lambda.")
		void getNameIsDevThat() {
			boolean isDev = "dev".equals(System.getProperty("ENV"));

			String actual = bank.getName();

			assumingThat(isDev, () -> {
				String expected = "SerBuitrago";

				assertNotNull(actual);
				assertEquals(expected, actual);
			});

			assertNotNull(actual);
		}
	}

	@Nested
	@DisplayName("Clase que permite probar la tranferencia de dinero.")
	class BankTransfer {
		@Test
		@DisplayName("Probando la tranferencia del banco.")
		void toTransfer() {
			Account origin = new Account("Sergio Stives Barrios Buitrago", new BigDecimal("200.000"));
			Account destination = new Account("Jonatan Javier Barrios Buitrago", new BigDecimal("100.000"));
			BigDecimal value = new BigDecimal("50.000");

			bank.toTransfer(origin, destination, value);

			Integer expectedOrigin = 150;
			Integer expectedDestination = 150;

			assertEquals(expectedOrigin, origin.getMoney().intValue());
			assertEquals(expectedDestination, destination.getMoney().intValue());
		}
	}

	@Nested
	@DisplayName("Clase que permite probar la relacion entre banco y cuenta.")
	class BankAccount {
		@Test
		@DisplayName("Probando relacion entre el banco y cuenta.")
		void addAccount() {
			Account account = new Account("Sergio Stives Barrios Buitrago", new BigDecimal("200.000"));
			bank.addAccount(account);
			bank.addAccount(account);

			Integer expected = 2;
			Integer actual = bank.getAccounts().size();

			assertNotNull(bank.getAccounts());
			assertEquals(expected, actual);
		}
	}
}
