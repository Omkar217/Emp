package com.mhtechin.empapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	static Connection con;
	public static Connection createDBConnection()
	{
		try 
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/employeemanagement";
			String username="employee";
			String password="employee";
			con = DriverManager.getConnection(url,username,password);
			System.out.println(con.getCatalog());
		} 
		catch (ClassNotFoundException | SQLException e) 
		{	
			e.printStackTrace();
		}
		return con;
			
	}
}
