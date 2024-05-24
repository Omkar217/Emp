package com.mhtechin.empapp;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFrame;

import com.sun.beans.introspect.PropertyInfo.Name;

public class Main extends JFrame {

	public static void main(String[] args) throws SQLException {
		
		System.out.println("Welcome to Employee management application");
		EmployeeDaoIntrf dao = new EmployeeDao();
		
		Scanner sc = new Scanner(System.in); 
		while(true) 
		{
			System.out.println("1.Add Employee\n"+
								"2.Show All Employee\n"+				
								"3.Show  Employee based on Id \n"+
								"4.Update the Employee\n"+
								"5.Delete  Employee\n" +
								"6. Rollback");
			System.out.println("Enter the choice:");
			int ch=sc.nextInt();
			switch(ch)
			{
				case 1:
					System.out.println("Enter Id:");
					int empId=sc.nextInt();
					sc.nextLine();
					System.out.println("Enter name:");
					String empName  = sc.nextLine();
					System.out.println("Enter salary:");
					double empSalary = sc.nextDouble();
					System.out.println("Enter age:");
					int empAge = sc.nextInt();
					System.out.println("Enter Total Number of Employee Addresses: ");
					int numberOfAddresses = sc.nextInt();
					List<Address> listOfAdressesPerEmployee = new ArrayList<>(numberOfAddresses);
					int i=1;
					while(i<=numberOfAddresses)
					{
					   System.out.println("Enter Employee Address No: " + i);
					    
					    System.out.println("Enter Employee Address Line :");
					    String address1 = sc.next();
					    sc.nextLine();
					      
					    System.out.println("Enter Employee's Locality:");
					    String locality =sc.next();
					    sc.nextLine();
					    
					    System.out.println("Enter the Employee's City:");
					    String city = sc.next();
					    
					    System.out.println("Enter Employee's Pincode:");
					    int pincode= sc.nextInt();
					    
						Address address = new Address(i,address1,locality,city,pincode,empId);
						listOfAdressesPerEmployee.add(address);
						i++;
					}
					
					dao.createEmployee(new Employee(empId,empName,empSalary,empAge,listOfAdressesPerEmployee));
					break;
					
				case 2:
					System.out.println("Show All the Employee:");
					dao.showAllEmployee();
					break;
			
				case 3:
					System.out.println("Enter the Id you want to search:");
					int employeeIdToSearch  = sc.nextInt();
					dao.showEmployeeBasedOnId(employeeIdToSearch);
					break;
					
				case 4:
					System.out.println(" Enter the employee Id you want to update : ");
					int empIdToUpdate = sc.nextInt();
//					System.out.println(" Do you want to change the address also of employee : ");
//					String ans = sc.ne
					System.out.println(" Enter the parameter you want to update :\n "+
										 "1.empName \n"+
										 "2.empSalary \n"+
										 "3.empAge \n");
					int empParameterToUpdate = sc.nextInt();
				

				try {
					dao.giveTheColumnName(empIdToUpdate, empParameterToUpdate);
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (InvalidSalary e) {
					e.printStackTrace();
				} catch (InvalidParameter e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					break;
					
				case 5:
					System.out.println("Enter the employee id to delete");
					int employeeIdToDelete = sc.nextInt();
					dao.deleteEmployee(employeeIdToDelete);
					break;
					
				case 6:
					dao.rollBackAllUpdatedChanges();
					break;
					
				default:
					System.out.println("Enter valid value");
					break;
			}
		}
		

	}
	

}
