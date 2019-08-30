package com.concurrentprogramming.company;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.locks.ReentrantLock;

import com.concurrentprogramming.dataaccesslayer.DataAccess;
import com.concurrentprogramming.dataaccesslayer.Services;

public class Account {
	
	public double account_balance;
	public int account_id;
	
	public Account(int num, double bal) {
        this.account_id = num;
        this.account_balance = bal;
    }

	// This function deposits amount to account
	public synchronized boolean deposit(Account acc, double amount) {
		Services service = new Services(); //DB services
		double new_balance = -1.0; //to check for success status in DB transactions

		acc.account_balance = service.getBalanceForAccId(acc.account_id); //fetches balance from DB
		acc.account_balance = acc.account_balance + amount; //increments the balance by amount that is to be deposited 
		new_balance = service.setBalanceForAccId(acc.account_id, acc.account_balance); //sets the updated balance to DB
		System.out.println("New balance in Account ID " + acc.account_id + " is € " + new_balance);
		if (new_balance == -1.0) {
			return false; //DB update was unsuccessful
		} else {
			return true; //DB update was successful
		}
	}
	
	// This function withdraws amount from account
	public synchronized boolean withdraw(Account acc, double amount) {
		Services service = new Services(); //DB services
		double new_balance = -1.0; //to check for success status in DB transactions
		
		acc.account_balance = service.getBalanceForAccId(acc.account_id); //fetches balance from DB
		if (amount <= acc.account_balance) { //checks if account has enough balance to be withdrawn
			acc.account_balance = acc.account_balance - amount; //decrements the balance by amount that is to be withdrawn 
			new_balance = service.setBalanceForAccId(acc.account_id, acc.account_balance); //sets the updated balance to DB
			System.out.println("New balance in Account ID " + acc.account_id + " is € " + new_balance);
		} else {
			System.out.println("Insufficient funds in Account ID " + acc.account_id + ".  Withdrawal was unsuccessful.");
		}
		if (new_balance == -1.0) {
			return false; //DB update was unsuccessful
		} else {
			return true; //DB update was successful
		}
	}
    
	// This function transfers amount from source account to destination account
	public synchronized void transferFunds(Account acc, Account destinationAcc, double amount) {
		if (withdraw(acc, amount)) { //if withdrawal is successful from source account then deposit amount to destination account
			if (!deposit(destinationAcc, amount)) { //if deposit is unsuccessful to destination account then deposit back amount to source account
				System.out.println("Transfer of amount "+ amount +" from source Account ID " + acc.account_id + " to destination Account ID " + destinationAcc.account_id + " was unsuccessful.");
				deposit(acc, amount); 
			}
		}
	}
	
}
