package com.acme.financial;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class BankAccountTC {

	

	@Test
	void testDeposit() throws Exception {
		Account acct = new Checking(0);
		
		assertNotNull(acct);
		assertEquals(0, acct.getBalance(), "balance should be 0");
		
		acct.deposit(100);
		assertEquals(100, acct.getBalance(), "balance should be 100");
		
	}
	
	void testWithdraw() throws Exception {
		
		Account acct = new Checking(100);
		assertNotNull(acct);
		assertEquals(100, acct.getBalance(), "balance should be 100");
		
		acct.withdraw(50);
		assertEquals(50, acct.getBalance(), "balance should be 50");
	}
	
	void testTransfer() throws Exception {
		
		Account acct1 = new Savings(100);
		assertNotNull(acct1);
		assertEquals(100, acct1.getBalance(), "balance should be 100");
		
		
		Account acct2 = new Savings(100);
		assertNotNull(acct2);
		assertEquals(100, acct2.getBalance(), "balance should be 100");
		
		acct1.transfer(25, acct2);
		assertEquals(75, acct1.getBalance(), "balance should be 75");
		assertEquals(125, acct2.getBalance(), "balance should be 25");
	}
	


}
