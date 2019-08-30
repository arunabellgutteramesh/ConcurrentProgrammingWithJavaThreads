package com.concurrentprogramming.company;

import java.util.ArrayList;
import com.concurrentprogramming.company.Account;
import com.concurrentprogramming.dataaccesslayer.Services;

public class AccountList {

	public static ArrayList<Account> accountList;

	// Creates an ArrayList of Account type
	public ArrayList<Account> createAccountArrayList() {
		accountList = new ArrayList<Account>();
		Services service = new Services();
		double bal;
		// it initializes the 50 accounts with Account ID from 1 to 50
		// and balance as fetched from DB for the IDs 1 to 50 respectively
		for (int i = 0; i < 51; i++) {
			bal = service.getBalanceForAccId(i);
			accountList.add(new Account(i, bal));
		}
		return accountList;
	}
}
