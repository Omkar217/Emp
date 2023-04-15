package com.mhtechin.empapp;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		System.out.println("Welcome to Employee management application");
		EmployeeDaoIntrf dao = new EmployeeDao();
		
		Scanner sc = new Scanner(System.in); 
		do {
			System.out.println("1.Add Employee\n"+
								"2.Show All Employee\n"+				
								"3.Show  Employee based on Id \n"+
								"4.Update the Employee\n"+
								"5.Delete  Employee\n");
			System.out.println("Enter the choice:");
			int ch=sc.nextInt();
			switch(ch)
			{
				case 1:
					System.out.println("Enter Id:");
					int id=sc.nextInt();
					System.out.println("Enter name:");
					String name  = sc.next();
					System.out.println("Enter salary:");
					double salary = sc.nextDouble();
					System.out.println("Enter age:");
					int age = sc.nextInt();
					dao.createEmployee(new Employee(id,name,salary,age));
					break;
					
				case 2:
					System.out.println("Show All the Employee:");
					dao.showAllEmployee();
					break;
			
				case 3:
					System.out.println("Enter the Id you want to search:");
					int empId  = sc.nextInt();
					dao.showEmployeeBasedOnId(empId);
					break;
					
				case 4:
					System.out.println("Enter the employee Id you want to update:");
					int empIdToUpdate = sc.nextInt();
					System.out.println("Enter the name to update:");
					String empName = sc.next();
					dao.updateEmployee(empIdToUpdate, empName);
					break;
					
				case 5:
					System.out.println("Enter the employee id to delete");
					id = sc.nextInt();
					dao.deleteEmployee(id);
					break;
					
				default:
					System.out.println("Enter valid value");
					break;
					
					
			}
												
		}while(true);

	}

}
