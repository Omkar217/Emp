package com.mhtechin.empapp;

public interface EmployeeDaoIntrf {
	
	//create employee
	public void createEmployee(Employee emp);
	//show all emp
	public void showAllEmployee();
	
	public void showEmployeeBasedOnId(int id);
	
	public void updateEmployee(int id, String name);
	
	public void deleteEmployee(int id);

}
