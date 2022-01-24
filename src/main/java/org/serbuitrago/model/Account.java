package org.serbuitrago.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Account {
	
	private String name;
	private BigDecimal money;
	
	private Bank bank;
	
	public Account(String name, BigDecimal money) {
		this(name, money, new Bank(null));
	}
	
	void debit(BigDecimal money) {
		this.money = this.money.subtract(money);
	}
	
	void credit(BigDecimal money) {
		this.money = this.money.add(money);
	}
	
	void bank(Bank bank) {
		this.bank = bank;
	}
}
