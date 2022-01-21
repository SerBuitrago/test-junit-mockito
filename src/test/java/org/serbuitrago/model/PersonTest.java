package org.serbuitrago.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

public class PersonTest {
	
	Person person = new Person("Sergio Stives", "Barrios Buitrago", BigDecimal.TEN);

	@Test
	void test_name() {
		String actual = person.getName();
		String expected = "Sergio Stives";
		assertEquals(expected, actual);
	}
	
	@Test
	void test_subname() {
		String actual = person.getSubname();
		String expected = "Barrios Buitrago";
		assertEquals(expected, actual);
	}
	
	@Test
	void test_money() {
		BigDecimal actual = person.getMoney();
		BigDecimal expected = BigDecimal.TEN;
		assertEquals(expected, actual);
	}	
}
