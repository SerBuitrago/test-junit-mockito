package org.serbuitrago.model;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.TestInstance;

//@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AccountTest {

	Account account;

	@BeforeEach
	void beforeEach() {
		System.out.println("Account - @BeforeEach");
		this.account = new Account("Sergio Stives Barrios Buitrago", new BigDecimal("1000.02"));
	}

	@AfterEach
	void afterEach() {
		System.out.println("Account - @AfterEach");
	}

	@BeforeAll
	static void beforeAll() {
		System.out.println("Account - @BeforeAll");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("Account - @AfterAll");
	}

	@Nested
	@DisplayName("Clase que permite probar el nombre de la cuenta.")
	class AccountName {
		@Test
		@DisplayName("Probando el nombre de la cuenta.")
		void getName() {
			String actual = account.getName();
			String expected = "Sergio Stives Barrios Buitrago";

			assertEquals(expected, actual);
		}

		@RepeatedTest(5)
		@DisplayName("Probando el nombre de la cuenta 5 veces.")
		void getNameFive() {
			String actual = account.getName();
			String expected = "Sergio Stives Barrios Buitrago";

			assertEquals(expected, actual);
		}

		@DisplayName("Probando el nombre de la cuenta 5 veces con mensaje personalizado.")
		@RepeatedTest(value = 5, name = "{displayName} - Repetición numero {currentRepetition} de {totalRepetitions}")
		void getNameFiveMessage() {
			String actual = account.getName();
			String expected = "Sergio Stives Barrios Buitrago";

			assertEquals(expected, actual);
		}

		@DisplayName("Probando el nombre de la cuenta 5 veces con mensaje personalizado y casos de prueba.")
		@RepeatedTest(value = 5, name = "{displayName} - Repetición numero {currentRepetition} de {totalRepetitions}")
		void getNameRepetition(RepetitionInfo repetitionInfo) {
			String actual = account.getName();
			String expected = "Sergio Stives Barrios Buitrago";
			switch (repetitionInfo.getCurrentRepetition()) {
			case 1:
				System.out.println("Estamos en la prueba 1.....");
				break;
			case 2:
				System.out.println("Estamos en la prueba 2.....");
				assertEquals(expected, actual);
				break;
			case 3:
				System.out.println("Estamos en la prueba 3.....");
				assertEquals(expected, actual);
				break;
			case 4:
				System.out.println("Estamos en la prueba 4.....");
				break;
			default:
				System.out.println("Estamos en la ultima prueba....");
				assertEquals(expected, actual);
				break;
			}

		}
	}

	@Nested
	@DisplayName("Clase que permite probar el dinero de la cuenta.")
	class AccountMoney {
		@Test
		@Disabled
		@DisplayName("Probando la plata de la cuenta.")
		void getMoney() {
			fail();
			BigDecimal actual = account.getMoney();
			BigDecimal expected = new BigDecimal("1000.02");

			assertEquals(expected, actual);
		}
	}

	@Nested
	@DisplayName("Clase que permite probar las operaciones de la cuenta.")
	class AccountOperation {
		@Test
		@DisplayName("Probando el debito de la cuenta.")
		void debit() {
			account.debit(new BigDecimal("100"));

			Integer actual = account.getMoney().intValue();
			Integer expected = 900;

			assertNotNull(account.getMoney());
			assertEquals(expected, actual);
		}

		@Test
		@DisplayName("Probando el credito de la cuenta.")
		void credit() {
			account.credit(new BigDecimal("100"));

			Integer actual = account.getMoney().intValue();
			Integer expected = 1100;

			assertNotNull(account.getMoney());
			assertEquals(expected, actual);
		}
	}

	@Nested
	class AccountOther {
		@Test
		@DisplayName("Probando la relacion entre la cuenta y banco.")
		void referenceBankAssertAll() {
			Account accountTwo = new Account("Jonatan Javier Barrios Buitrago", BigDecimal.ONE);

			Bank bank = new Bank("SerBuitrago");
			bank.addAccount(account);
			bank.addAccount(accountTwo);

			String expectedBank = "SerBuitrago";
			String expectedName = "Sergio Stives Barrios Buitrago";

			assertAll(() -> {
				assertNotNull(account.getBank());
				assertNotNull(accountTwo.getBank());
			}, () -> {
				assertEquals(expectedBank, account.getBank().getName());
				assertEquals(expectedBank, accountTwo.getBank().getName());
			}, () -> {
				assertEquals(expectedName, bank.getAccounts().stream()
						.filter(a -> a.getName().equals(account.getName())).findFirst().get().getName());
			}, () -> {
				assertTrue(bank.getAccounts().stream().anyMatch(a -> a.getName().equals(expectedName)));
			});
		}

		@Test
		@DisplayName("Probando mensajes personalizados y la relacion entre la cuenta y banco.")
		void referenceBankAssertAllMessage() {
			Account accountTwo = new Account("Jonatan Javier Barrios Buitrago", BigDecimal.ONE);

			Bank bank = new Bank("SerBuitrago");
			bank.addAccount(account);
			bank.addAccount(accountTwo);

			String expectedBank = "SerBuitrago";
			String expectedName = "Sergio Stives Barrios Buitrago";

			assertAll(() -> {
				assertNotNull(account.getBank(), "El banco no puede estar nulo en la cuenta 1.");
				assertNotNull(accountTwo.getBank(), "El banco no puede estar nulo en la cuenta 2.");
			}, () -> {
				assertEquals(expectedBank, account.getBank().getName(),
						"El nombre del banco no es el esperado en la cuenta 1.");
				assertEquals(expectedBank, accountTwo.getBank().getName(),
						"El nombre del banco no es el esperado en la cuenta 2.");
			}, () -> {
				assertEquals(expectedName, bank.getAccounts().stream()
						.filter(a -> a.getName().equals(account.getName())).findFirst().get().getName(),
						"El nombre de la cuenta no fue el esperado.");
			}, () -> {
				assertTrue(bank.getAccounts().stream().anyMatch(a -> a.getName().equals(expectedName)),
						"El nombre de la cuenta no fue el esperado.");
			});
		}

		@Test
		@DisplayName("Probando mensajes personalizados con lambda y la relacion entre la cuenta y banco.")
		void referenceBankAssertAllLambdaMessage() {
			Account accountTwo = new Account("Jonatan Javier Barrios Buitrago", BigDecimal.ONE);

			Bank bank = new Bank("SerBuitrago");
			bank.addAccount(account);
			bank.addAccount(accountTwo);

			String expectedBank = "SerBuitrago";
			String expectedName = "Sergio Stives Barrios Buitrago";

			assertAll(() -> {
				assertNotNull(account.getBank(), () -> "El banco no puede estar nulo en la cuenta 1.");
				assertNotNull(accountTwo.getBank(), () -> "El banco no puede estar nulo en la cuenta 2.");
			}, () -> {
				assertEquals(expectedBank, account.getBank().getName(),
						() -> "El nombre del banco no es el esperado en la cuenta 1.");
				assertEquals(expectedBank, accountTwo.getBank().getName(),
						() -> "El nombre del banco no es el esperado en la cuenta 2.");
			}, () -> {
				assertEquals(expectedName, bank.getAccounts().stream()
						.filter(a -> a.getName().equals(account.getName())).findFirst().get().getName(),
						() -> "El nombre de la cuenta no fue el esperado.");
			}, () -> {
				assertTrue(bank.getAccounts().stream().anyMatch(a -> a.getName().equals(expectedName)),
						() -> "El nombre de la cuenta no fue el esperado.");
			});
		}
	}
}
