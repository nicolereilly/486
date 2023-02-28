package com.acme.financial;

public abstract class Account {
	// if we use double we need to worry about round-off error
	// many people use BigDecimal for monetary values
	protected int balance;
	protected int accountNumber;
	
	public Account(int startingBalance) {
		this.balance = startingBalance;
	}
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	public void deposit(int amount) {
		this.balance += amount;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public void withdraw(int amount) {
		this.balance = this.balance - amount;
	}
	
	public void transfer(int amount, Account other) {
		this.withdraw(amount);
		other.deposit(amount);
	}
}
