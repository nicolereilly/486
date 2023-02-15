package com.acme.financial;

public class Checking extends Account {
	public Checking(int startingBalance) {
		super(startingBalance);
	}

	public void withdraw(int amount) {
		this.balance = this.balance - 1;
	}
}
