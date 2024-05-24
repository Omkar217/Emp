package com.mhtechin.empapp;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeManagementApp extends JFrame {

    private JTextField empIdField;
    private JTextField empNameField;
    private JTextField empSalaryField;
    private JTextField empAgeField;
    private JTextField addressLine1Field;
    private JTextField localityField;
    private JTextField cityField;
    private JTextField pincodeField;
    private JButton addEmployeeButton;
    private JButton showEmployeeButton;
    EmployeeDaoIntrf dao;


    public EmployeeManagementApp() {
    	dao = new EmployeeDao();
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        setTitle("Employee Management Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(50, 10));

        add(new JLabel("Employee ID:"));
        empIdField = new JTextField();
        add(empIdField);

        add(new JLabel("Employee Name:"));
        empNameField = new JTextField();
        add(empNameField);

        add(new JLabel("Employee Salary:"));
        empSalaryField = new JTextField();
        add(empSalaryField);

        add(new JLabel("Employee Age:"));
        empAgeField = new JTextField();
        add(empAgeField);

        add(new JLabel("Address Line 1:"));
        addressLine1Field = new JTextField();
        add(addressLine1Field);

        add(new JLabel("Locality:"));
        localityField = new JTextField();
        add(localityField);

        add(new JLabel("City:"));
        cityField = new JTextField();
        add(cityField);

        add(new JLabel("Pincode:"));
        pincodeField = new JTextField();
        add(pincodeField);

        add(new JLabel(""));
        addEmployeeButton = new JButton("Add Employee");
        add(addEmployeeButton);
        
        add(new JLabel(""));
        showEmployeeButton = new JButton("Show Employee based on Id");
        add(showEmployeeButton);
        
        add(new JLabel(":"));
        pincodeField = new JTextField();
        add(pincodeField);
        
        
        

        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int empId = Integer.parseInt(empIdField.getText());
                String empName = empNameField.getText();
                double empSalary = Double.parseDouble(empSalaryField.getText());
                int empAge = Integer.parseInt(empAgeField.getText());

                int addressId = 1;
                String addressLine1 = addressLine1Field.getText();
                String locality = localityField.getText();
                String city = cityField.getText();
                int pincode = Integer.parseInt(pincodeField.getText());

                Address address = new Address(addressId, addressLine1, locality, city, pincode, empId);
                List<Address> listOfAdressesPerEmployee = new ArrayList<>();
                listOfAdressesPerEmployee.add(address);

                try {
					dao.createEmployee(new Employee(empId, empName, empSalary, empAge, listOfAdressesPerEmployee));
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
            }
        });
        
//        showEmployeeButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//            	
//            }
//        });
        
       
        showEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame showEmployeeFrame = new JFrame("Employee Details");
                showEmployeeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                showEmployeeFrame.setSize(800, 600);

                JLabel empIdLabel = new JLabel("Enter Employee ID:");
                JTextField empIdField = new JTextField(10);
                JButton showButton = new JButton("Show Employee");

                JPanel inputPanel = new JPanel();
                inputPanel.add(empIdLabel);
                inputPanel.add(empIdField);
                inputPanel.add(showButton);

                showButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int empId = Integer.parseInt(empIdField.getText());
                  
    					dao.showEmployeeBasedOnId(empId);
//                        if (employee != null) {
//                            // Do something with the employee object in another method
//                        } else {
//                            JLabel errorLabel = new JLabel("Employee not found.");
//                            showEmployeeFrame.getContentPane().add(errorLabel, BorderLayout.CENTER);
//                            showEmployeeFrame.pack();
//                        }
                    }
                });

                showEmployeeFrame.getContentPane().add(inputPanel, BorderLayout.NORTH);
                showEmployeeFrame.setVisible(true);
            }
        });

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(EmployeeManagementApp::new);
    }
}