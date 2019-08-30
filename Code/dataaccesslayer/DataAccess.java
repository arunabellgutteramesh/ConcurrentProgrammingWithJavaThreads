package com.concurrentprogramming.dataaccesslayer;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataAccess {

	static Connection dbConnection;
	//Creates a DB connection and returns to the calling function
	public synchronized static Connection getDbAccess() {
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			dbConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CP_Schema?useSSL=false", "root", "sql12345");
			if (dbConnection != null) {
				//System.out.println("Connected to SQL Database and Schema");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dbConnection;
	}
} 