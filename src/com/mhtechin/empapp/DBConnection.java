package com.mhtechin.empapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	static Connection con;
	public static Connection createDBConnection()
	{
		//load Driver 
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			//get connection 
			String url="jdbc:mysql://localhost:3306/studlib";
			String username="librarystudent";
			String password="librarystudent";
			con = DriverManager.getConnection(url,username,password);
		} 
		catch (ClassNotFoundException | SQLException e) {
			
			e.printStackTrace();
		}
		return con;
			
	}
}
