package com.concurrentprogramming.main;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.concurrentprogramming.company.Account;
import com.concurrentprogramming.company.AccountList;
import com.concurrentprogramming.company.Department;
import com.concurrentprogramming.randomgenerator.RandomGenerator;


public class Main {
	
	public static ArrayList<Account> accountsList = new AccountList().createAccountArrayList();
	
	public static void main(String[] args) {
		
		int rDeptId = 0, rAccId = 0, rTrId = 0, rAmt = 0, rAccIdSecond = 0;
		
		int numberOfCores = Runtime.getRuntime().availableProcessors();
		ExecutorService executor = Executors.newFixedThreadPool(numberOfCores); //number of threads are the same as number of cores available on system (for better performance). Here, it is 4
		
		RandomGenerator ranGen = new RandomGenerator();
		for(int i=0;i<15000;i++) {
			Runnable dept;
			rAccIdSecond = 0; //initialize destination Account ID to 0 at start of every loop
			rDeptId = ranGen.getRandomDeptId(); //generate a random Department ID
			rAccId = ranGen.getRandomAccId(); //generate a random Account ID
			rTrId = ranGen.getRandomTransactionId(); //generate a random Transaction ID to choose from deposit, withdrawal and transfer funds
			rAmt = ranGen.getRandomAmount(); //generate a random amount to transact with
			if(rTrId==2) { // 2 => TransferFunds
				rAccIdSecond = ranGen.getRandomAccId(); //generate a random destination account ID
				while(rAccIdSecond == rAccId) { 
					rAccIdSecond = ranGen.getRandomAccId(); //generate a destination account ID until it differs from source account ID
				}
				dept = new Department(rDeptId,rTrId,rAmt,rAccId,rAccIdSecond,accountsList); //call overloaded constructor of department class
			}else {
				dept = new Department(rDeptId,rTrId,rAmt,rAccId,accountsList); //call overloaded constructor of department class
			}
			executor.execute(dept);
		}
		executor.shutdown();
		while(!executor.isTerminated()) {};
		System.out.println("Threads have finished executing their tasks."); 
	}
}
