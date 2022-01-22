package org.serbuitrago.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
public class Account {
	
	private String name;
	private BigDecimal money;
	
	void debit(BigDecimal money) {
		this.money = this.money.subtract(money);
	}
	
	void credit(BigDecimal money) {
		this.money = this.money.add(money);
	}
}
