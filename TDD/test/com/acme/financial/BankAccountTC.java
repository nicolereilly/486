package com.acme.financial;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.acme.financial.BankAccount;

class BankAccountTC {

	@Test
	void testDeposit() throws Exception {
		BankAccount acct = new BankAccount(0);
		
		assertNotNull(acct);
		assertEquals(0, acct.getBalance(), "balance should be 0");
		
		acct.deposit(100);
		assertEquals(100, acct.getBalance(), "balance should be 100");
		
	}
	
	void testWithdraw() throws Exception {
		
		BankAccount acct = new BankAccount(100);
		assertNotNull(acct);
		assertEquals(100, acct.getBalance(), "balance should be 100");
		
		acct.withdraw(50);
		assertEquals(50, acct.getBalance(), "balance should be 50");
	}
	
	void testTransfer() throws Exception { /*not done*/
		
		BankAccount acct = new BankAccount(100);
		assertNotNull(acct);
		assertEquals(100, acct.getBalance(), "balance should be 100");
		
		acct.withdraw(50);
		assertEquals(50, acct.getBalance(), "balance should be 50");
	}
	
	
	/*
	 testTransfer
	 create one account with 100
	 create another with 100
	 
	 make sure neither object is null
	 make sure the balance of both is 100
	 
	 acct1.transfer(25, acct2);
	 make sure acct1 now has 75
	 make sure acct2 now has 125
	 */

}
