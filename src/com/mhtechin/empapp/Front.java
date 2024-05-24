package com.mhtechin.empapp;
import javax.swing.*;

public class Front {

	public static void main(String[] args) 
	{
	    JFrame frame = new JFrame();

        JLabel emp_id = new JLabel("Employee ID:");
        emp_id.setBounds(10, 10, 200, 20);

        JLabel emp_name = new JLabel("Employee Name:");
        emp_name.setBounds(10, 40, 200, 20);

        JLabel emp_salary = new JLabel("Employee Salary:");
        emp_salary.setBounds(10, 70, 200, 20);

        JLabel emp_age = new JLabel("Employee Age:");
        emp_age.setBounds(10, 100, 200, 20);

        JTextField idField = new JTextField();
        idField.setBounds(220, 10, 200, 20);

        JTextField nameField = new JTextField();
        nameField.setBounds(220, 40, 200, 20);

        JTextField salaryField = new JTextField();
        salaryField.setBounds(220, 70, 200, 20);

        JTextField ageField = new JTextField();
        ageField.setBounds(220, 100, 200, 20);

        JButton button = new JButton("GFG WebSite Click");
        button.setBounds(150, 200, 220, 50);

        frame.add(emp_id);
        frame.add(emp_name);
        frame.add(emp_salary);
        frame.add(emp_age);
        
        frame.add(idField);
        frame.add(nameField);
        frame.add(salaryField);
        frame.add(ageField);

        frame.add(button);

        frame.setSize(500, 300);
        frame.setLayout(null);
        frame.setVisible(true);
        
	        
	        
	}

}
