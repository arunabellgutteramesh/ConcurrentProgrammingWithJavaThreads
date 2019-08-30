package com.concurrentprogramming.randomgenerator;

import java.util.ArrayList;
import java.util.Random;

import com.concurrentprogramming.dataaccesslayer.Services;

public class RandomGenerator {

	Random random = new Random();
	Services service = new Services();
	int[] trIds = { 0, 1, 2 };
	int randomTrId;
	int randomAmount;
	int randomDeptId;
	int randomAccId;

	// Generates a random Department ID from the list of Department IDs
	public int getRandomDeptId() {
		ArrayList<Integer> deptIds = service.getAllDepartmentIds();
		randomDeptId = deptIds.get(random.nextInt(deptIds.size()));
		return randomDeptId;
	}

	// Generates a random Account ID from the list of Account IDs
	public int getRandomAccId() {
		ArrayList<Integer> accIds = service.getAllAccountIds();
		randomAccId = accIds.get(random.nextInt(accIds.size()));
		return randomAccId;
	}

	// Generates a random Transaction ID from the list of Transaction IDs
	public int getRandomTransactionId() {
		randomTrId = trIds[random.nextInt(trIds.length)];
		return randomTrId;
	}

	// Generates a random Amount between 500 and 100
	public int getRandomAmount() {
		randomAmount = random.nextInt((500 - 100) + 1) + 100; // random number between 500 and 100
		return randomAmount;
	}
}
