package com.mhtechin.empapp;

import java.sql.SQLException;
import java.util.List;

public interface EmployeeDaoIntrf {
	
	public void createEmployee(Employee emp) throws SQLException;

	public void showAllEmployee();
	
	public void showEmployeeBasedOnId(int id);
	
	public void updateEmployee(int id, String empParameterToUpdate) throws SQLException, InvalidSalary;
	
	public void rollBackAllUpdatedChanges();
	
	public void deleteEmployee(int id);

	public void giveTheColumnName(int empIdToUpdate, int empParameterToUpdate) throws SQLException, InvalidSalary, InvalidParameter;


}
