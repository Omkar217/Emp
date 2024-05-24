package com.mhtechin.empapp;

import java.util.ArrayList;
import java.util.List;

public class Employee {

	private int empId;
	private String empName;
	private double empSalary;
	private int empAge;
	private List<Address> empAddress;
	
	public Employee(int empId, String empName, double empSalary, int empAge, List<Address> empAddress) {
		super();
		this.empId = empId;
		this.empName = empName;
		this.empSalary = empSalary;
		this.empAge = empAge;
		this.empAddress = new ArrayList<>();
		this.empAddress.addAll(empAddress);
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public double getEmpSalary() {
		return empSalary;
	}

	public void setEmpSalary(double empSalary) {
		this.empSalary = empSalary;
	}

	public int getEmpAge() {
		return empAge;
	}

	public void setEmpAge(int empAge) {
		this.empAge = empAge;
	}

	public List<Address> getEmpAddress() {
		return empAddress;
	}

	public void setEmpAddress(List<Address> empAddress) {
		this.empAddress = empAddress;
	}
	
	
}
