package com.concurrentprogramming.company;

import java.util.ArrayList;

public class Department implements Runnable {

	private int dept_id;
	private int acc_id;
	private int tr_id;
	private int amt;
	private int acc_id_second;
	public static ArrayList<Account> accountList;

	// Department constructor overloaded for TransferAccount (trID=2) scenario
	public Department(int deptId, int trId, int amount, int accId, int accIdSecond, ArrayList<Account> list) {
		dept_id = deptId;
		tr_id = trId;
		amt = amount;
		acc_id = accId;
		acc_id_second = accIdSecond;
		accountList = list;
	}

	// Department constructor overloaded for Deposit and Withdrawal (trID=0 and
	// TrID=1) scenario
	public Department(int deptId, int trId, int amount, int accId, ArrayList<Account> list) {
		dept_id = deptId;
		tr_id = trId;
		amt = amount;
		acc_id = accId;
		accountList = list;
	}

	// Defining runnable tasks for each Thread
	// Based of TrID being 0,1,2 -> respective calls are made to Deposit, Withdraw
	// and TransferFunds
	@Override
	public void run() {
		Account account = accountList.get(this.acc_id);
		switch (this.tr_id) {// 0 = deposit; 1 = withdraw; 2 = transferFunds;
		case 0: //Deposit
			System.out.println(Thread.currentThread().getName() + " is helping Department ID " + this.dept_id + " in making a deposit of € " + this.amt + " to Account ID "
					+ this.acc_id);
			account.deposit(account, this.amt);
			break;
		case 1: //Withdraw
			System.out.println(Thread.currentThread().getName() + " is helping Department ID " + this.dept_id + " in making a withdrawal of € " + this.amt
					+ " from Account ID " + this.acc_id);
			account.withdraw(account, this.amt);
			break;
		case 2: //TransferFunds
			System.out.println(Thread.currentThread().getName() + " is helping Department ID " + this.dept_id + " in making a transfer of € " + this.amt
					+ " from Account ID " + this.acc_id + " to Account ID " + this.acc_id_second);
			Account destinationAccount = accountList.get(this.acc_id_second);
			account.transferFunds(account, destinationAccount, this.amt);
			break;
		}
	}
}
