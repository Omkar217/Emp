package com.mhtechin.empapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class EmployeeDao implements EmployeeDaoIntrf {
	
	Connection con;

	@Override
	public void createEmployee(Employee emp) throws SQLException {
	
		con = DBConnection.createDBConnection();	
		con.setAutoCommit(false);
		String empQuery = "insert into employee values(?,?,?,?)";
		String insertAddressQuery = "insert into address values(?,?,?,?,?,?)";
		PreparedStatement pstm;
		try {
			pstm = con.prepareStatement(empQuery);
			pstm.setInt(1, emp.getEmpId());
			pstm.setString(2, emp.getEmpName());
			pstm.setDouble(3, emp.getEmpSalary());
			pstm.setInt(4, emp.getEmpAge());
			int cnt = pstm.executeUpdate();
			
			System.out.println(" emp cnt: " +cnt );
			
			PreparedStatement addressStmt = con.prepareStatement(insertAddressQuery);
	                for (Address address : emp.getEmpAddress()) 
	                {
	                    addressStmt.setInt(1, address.getAddressId());
	        	        addressStmt.setInt(2, address.getEmpId());
	                    addressStmt.setString(3, address.getAddressline1());
	                    addressStmt.setString(4, address.getLocality());
	                    addressStmt.setString(5, address.getCity());
	                    addressStmt.setInt(6, address.getPincode());

	                    addressStmt.addBatch();//This method will add all Address objects in queue, one at a time.
	                    
	                }
	               int addressCnt[] = addressStmt.executeBatch();//This Method will  execute all the address that were in queue and give total number of rows that it gave. 
	               
	   			System.out.println("Address cnt: " + addressCnt);

			if(cnt > 0 && addressCnt.length > 0)
			{
				con.commit();
				System.out.println("Employee Inserted Successfully");
			}
			con.rollback();
		}catch (SQLException e) {
			try {
				con.rollback();
				con.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	           
	
}

	@Override
	public void showAllEmployee() {
		con = DBConnection.createDBConnection();
		String showAllDataquery="SELECT * FROM employeemanagement.employee";
		System.out.println("Employ Details");
		System.out.println("--------------------------------------------------");
		
		try {
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(showAllDataquery);
			while(result.next())
			{
				System.out.format("%d\t%s\t%f\t%d", result.getInt(1), result.getString(2), result.getDouble(3), result.getInt(4));
				System.out.println();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}

	@Override
	public void showEmployeeBasedOnId(int id) 
	{
		con = DBConnection.createDBConnection();
		String query = "select * from employee where empId =" +id;
		try
		{
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next())
			{
				int empId = rs.getInt(1);
				String empName = rs.getString(2);
				Double empSalary = rs.getDouble(3);
				int empAge = rs.getInt(4);
				System.out.println(" empId :"+ empId);
				System.out.println(" emp name :"+ empName);
				System.out.println(" emp salary :"+ empSalary);
				System.out.println(" emp age :"+ empAge);
				
				 String addressQuery = "select * from address where empId = " + empId;
		         Statement addressStmt = con.createStatement();
		         ResultSet addressRs = addressStmt.executeQuery(addressQuery);
		         
		         List<Address> addresses = new ArrayList<>();
		         
		         while (addressRs.next()) {
		                Address address = new Address();
		                address.setAddressId(addressRs.getInt(1));
		                address.setEmpId(addressRs.getInt(2));
		                address.setAddressline1(addressRs.getString(3));
		                address.setLocality(addressRs.getString(4));
		                address.setCity(addressRs.getString(5));
		                address.setPincode(addressRs.getInt(6));
		                
		                addresses.add(address);
		            }
		            System.out.println("addresses size : " + addresses.size());
		         	for(int i=0;i<addresses.size();i++)
		         	{
		         		System.out.println(" Emp Address Id : " + addresses.get(i).getAddressId());
		         		System.out.println(" Emp Id : " + addresses.get(i).getEmpId());
		         		System.out.println(" Address Line : " + addresses.get(i).getAddressline1());
		         		System.out.println(" Emp Locality : " + addresses.get(i).getLocality());
		         		System.out.println(" Emp City : " + addresses.get(i).getCity());
		         		System.out.println(" Emp Pincode : " + addresses.get(i).getPincode());
		         	}
			}
		}
		catch (SQLException e) {
		
			e.printStackTrace();
		}
		
	}
	
	public void giveTheColumnName(int empId, int empParameterToUpdate) throws SQLException, InvalidSalary, InvalidParameter
	{
		  if(empParameterToUpdate != 0)
          {     
         	 if(empParameterToUpdate == 1 )
         	 {
         		updateEmployee(empId, "empName");
         	 }
         	 else if(empParameterToUpdate == 2 )
         	 {
         		updateEmployee(empId, "empSalary"); 
         	 }
         	 else if(empParameterToUpdate == 3)
         	 {
         		updateEmployee(empId, "empAge");  
         	 }
         	 else
         	 {
         		 throw new InvalidParameter("Invalid Parameter"); 
         	 }
          }
	}

	@Override
	public void updateEmployee(int empId, String empParameterToUpdate) throws SQLException, InvalidSalary {
		con = DBConnection.createDBConnection();
		 String stringInput;
         String query = "SELECT * FROM  Employee LIMIT 1";
         try (Statement statement = con.createStatement();
         ResultSet resultSet = statement.executeQuery(query))
         {
             StringBuffer updateQuery = new StringBuffer("UPDATE Employee");

             ResultSetMetaData metaData = resultSet.getMetaData();
             int columnCount = metaData.getColumnCount();
             
             System.out.println(" The column count of this table is : " + columnCount);
             
             

             int sqlTypes = 0;// it denotes its null 
             while (resultSet.next()) 
             {
                 for (int i = 1; i <= columnCount; i++) 
                 {
                	 if(metaData.getColumnName(i).equals(empParameterToUpdate))
                	 {
                		 System.out.println(" name of column : " + metaData.getColumnName(i));
                		 updateQuery.append(" SET " +metaData.getColumnName(i));
                		 sqlTypes = metaData.getColumnType(i);
                	 }
                 }             
             }
             if(sqlTypes != 0)
             {     
            	 switch (sqlTypes) 
            	 {
            	     case -5:
            	         System.out.println("Big int");
            	         break;   
            	     case 1:
            	         System.out.println("Char");
            	         break; 
            	     case 3:
            	         System.out.println("Decimal");
            	         break;           
            	     case 8:
            	         System.out.println("Type is Double");
            	         stringInput = KeyboardInput.readString(" Enter the  changed data of Double Type: ");
            	         double numDouble = Double.parseDouble(stringInput);
            	         if(numDouble > 35000 && numDouble < 40000)
            	         {
            	        	 updateQuery.append(" = " + numDouble + " where empId = " + empId + ";");
            	         }
            	         else
            	        	 throw new InvalidSalary("Invalid Salary");
            	         break;
            	     case 6:
            	         System.out.println("Float");
            	         break;
            	     case 4:
            	         System.out.println("Type is Integer");
            	         stringInput = KeyboardInput.readString(" Enter the  changed data of Integer Type: ");
            	         int num = Integer.parseInt(stringInput);
            	         updateQuery.append(" = " + num + " where empId = " + empId + ";");
            	         //                      actual no         matNr(column)        old value
            	         break;
            	     case 2000:
            	         System.out.println("JavaObject");
            	         break;            
            	     case 12:
            	         System.out.println("Type is Varchar");
            	         stringInput = KeyboardInput.readString(" Enter the changed data of String Type: ");
            	         updateQuery.append(" = '" +stringInput+ "' where empId  = " +empId+ ";");
            	         break;
            	     case 0:
            	         System.out.println("Null");
            	         break;
            	     default:
            	         System.out.println("Unknown data type");
            	 }
            	 con.createStatement().execute(updateQuery.toString());
            	 resultSet.close();
            	 statement.close();
             }
         }
		
	}
	
	public void rollBackAllUpdatedChanges()
	{
//		try {
//	//		con.commit();
//	//		con.releaseSavepoint(savepoint1);   
//		//	con.releaseSavepoint(savepoint1);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	@Override
	public void deleteEmployee(int id) {
		con = DBConnection.createDBConnection();
		String query = "delete from employee where id=?";
		try
		{
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setInt(1, id);
			int cnt = pstm.executeUpdate();
			if(cnt!=0)
			{
				System.out.println("Employee deleted succesfully");
			}
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
	}


}
