package org.serbuitrago.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Bank {
	
	private String name;
	private List<Account> accounts;
	
	public Bank(String name) {
		this(name, new ArrayList<>());
	}

	void toTransfer(Account origin, Account destination, BigDecimal value) {
		origin.debit(value);
		destination.credit(value);
	}
	
	void addAccount(Account account) {
		this.accounts.add(account);
		account.bank(this);
	}
}
