package org.serbuitrago.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Person {
	
	private String name;
	private String subname;
	private BigDecimal money;
}
