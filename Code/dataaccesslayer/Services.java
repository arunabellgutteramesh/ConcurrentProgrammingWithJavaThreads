package com.concurrentprogramming.dataaccesslayer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Services {
	
	ArrayList<Integer> deptIds = new ArrayList<Integer>();
	ArrayList<Integer> accountIds = new ArrayList<Integer>();
	
	// Fetches all the Department IDs from DB
	public ArrayList getAllDepartmentIds() {
		Connection dbAccess = DataAccess.getDbAccess();
		Statement sttmnt;
		ResultSet rs;
		try {
			sttmnt = dbAccess.createStatement();
			rs = sttmnt.executeQuery("SELECT dept_id FROM departments");
			while (rs.next()) {
				deptIds.add(rs.getInt("dept_id"));
			}
			dbAccess.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return deptIds;
	}

	// Fetches all the Account IDs from DB
	public ArrayList getAllAccountIds() {
		Connection dbAccess = DataAccess.getDbAccess();
		Statement sttmnt;
		ResultSet rs;
		try {
			sttmnt = dbAccess.createStatement();
			rs = sttmnt.executeQuery("SELECT acc_id FROM accounts");
			while (rs.next()) {
				accountIds.add(rs.getInt("acc_id"));
			}
			dbAccess.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return accountIds;
	}
	
	// Fetches balance of a particular ID from DB
	public synchronized double getBalanceForAccId(int AccId) {
		Connection dbAccess = DataAccess.getDbAccess();
		PreparedStatement psttmnt;
		ResultSet rs;
		String querySting;
		double balance = -1.0;
		try {
			querySting = "SELECT acc_balance FROM accounts where acc_id = " + AccId;
			psttmnt = dbAccess.prepareStatement(querySting);
			rs = psttmnt.executeQuery();
			while (rs.next()) {
				balance = rs.getDouble("acc_balance");
			}
			dbAccess.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return balance;
	}

	// Sets/Updates balance for a particular ID in DB
	public synchronized double setBalanceForAccId(int AccId, double bal) {
		Connection dbAccess = DataAccess.getDbAccess();
		PreparedStatement psttmnt;
		String querySting;
		double balance = -1.0;
		try {
			querySting = "UPDATE accounts SET acc_balance = " + bal + " WHERE acc_id = " + AccId;
			psttmnt = dbAccess.prepareStatement(querySting);
			int result = psttmnt.executeUpdate();
			if (result != 0) {
				 balance = getBalanceForAccId(AccId);
			}
			dbAccess.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return balance;
	} 
}
