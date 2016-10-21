package ca.mcgill.ecse321.foodtruckmanagement.view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import ca.mcgill.ecse321.foodtruckmanagement.controller.FoodTruckManagementController;
import ca.mcgill.ecse321.foodtruckmanagement.controller.InvalidInputException;

public class FoodTruckManagementPage extends JFrame {


	private static final long serialVersionUID = 6324184905041245298L;
	
	//UI elements
	private JLabel errorMessage;
	private JTextField supplyNameTextField;
	private JLabel supplyNameLabel;
	private JTextField supplyAmountTextField;
	private JLabel supplyAmountLabel;
	private JButton addSupplyButton;
	private JButton removeSupplyButton;
	private JTextField equipmentNameTextField;
	private JLabel equipmentNameLabel;
	private JTextField equipmentAmountTextField;
	private JLabel equipmentAmountLabel;
	private JButton addEquipmentButton;
	private JButton removeEquipmentButton;
	private JButton viewSupplyButton;
	
	private JTextField employeeNameTextField;
	private JLabel employeeNameLabel;
	private JTextField employeeRoleTextField;
	private JLabel employeeRoleLabel;
	private JButton addEmployeeButton;
	private JButton viewEmployeesButton;
	
	//data elements
	private String error = null;
	
	/* Creates new Page */
	public FoodTruckManagementPage() {
		initComponents();
		refreshData();
	}
	
	/*Initialize the form*/
	private void initComponents() {
		
		//elements for error message
		errorMessage = new JLabel();
		errorMessage.setForeground(Color.RED);
		
		//elements for supply
		supplyNameTextField = new JTextField();
		supplyNameLabel = new JLabel();
		supplyAmountTextField = new JTextField();
		supplyAmountLabel = new JLabel();
		addSupplyButton = new JButton();
		removeSupplyButton = new JButton();
		viewSupplyButton = new JButton();
		
		//global settings and listeners
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Food Truck Management");
		
		supplyNameLabel.setText("Food Supply Name:");
		supplyAmountLabel.setText("Amount:");
		
		addSupplyButton.setText("Add To Food Supply");
		addSupplyButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addSupplyButtonActionPerformed(evt);
			}
		});
		
		removeSupplyButton.setText("Remove From Food Supply");
		removeSupplyButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeSupplyButtonActionPerformed(evt);
			}
		}); 
		
		//elements for equipment
				equipmentNameTextField = new JTextField();
				equipmentNameLabel = new JLabel();
				equipmentAmountTextField = new JTextField();
				equipmentAmountLabel = new JLabel();
				addEquipmentButton = new JButton();
				removeEquipmentButton = new JButton();
				
				
				equipmentNameLabel.setText("Equipment Name:");
				equipmentAmountLabel.setText("Amount:");
				
				addEquipmentButton.setText("Add To Equipment");
				addEquipmentButton.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						addEquipmentButtonActionPerformed(evt);
					}
				});
				
				removeEquipmentButton.setText("Remove From Equipment");
				removeEquipmentButton.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						removeEquipmentButtonActionPerformed(evt);
					}
				});
		
		viewSupplyButton.setText("View Supply");
		viewSupplyButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				viewSupplyButtonActionPerformed(evt);
			}
		});
		
		//elements for employee
			employeeNameTextField = new JTextField();
			employeeNameLabel = new JLabel();
			employeeRoleTextField = new JTextField();
			employeeRoleLabel = new JLabel();
			addEmployeeButton = new JButton();
			viewEmployeesButton = new JButton();
			
			employeeNameLabel.setText("Employee Name:");
			employeeRoleLabel.setText("Employee Role:");
			
			addEmployeeButton.setText("Hire An Employee");
			addEmployeeButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					addEmployeeButtonActionPerformed(evt);
				}
			});
			
			viewEmployeesButton.setText("View Employees");
			viewEmployeesButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					viewEmployeesButtonActionPerformed(evt);
				}
			});
			
		//Blank line to separate elements of the UI	
		JLabel blankLine = new JLabel(" ");
		
		//layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createSequentialGroup()
					
					.addGroup(layout.createParallelGroup()	
					.addComponent(supplyNameLabel)
					.addComponent(blankLine)
					.addComponent(employeeNameLabel))
						
					.addGroup(layout.createParallelGroup()
					.addComponent(supplyNameTextField, 200, 200, 400)
					.addComponent(addSupplyButton)
					.addComponent(employeeNameTextField, 200, 200, 400))
					
					.addGroup(layout.createParallelGroup()
					.addComponent(supplyAmountLabel)
					.addComponent(employeeRoleLabel))
						
					.addGroup(layout.createParallelGroup()
						.addComponent(supplyAmountTextField, 200, 200, 400)
					    .addComponent(removeSupplyButton)
					    .addComponent(employeeRoleTextField, 200, 200, 400))
					 
					 .addGroup(layout.createParallelGroup()   
					 .addComponent(equipmentNameLabel)
					 .addComponent(addEmployeeButton))
						
					.addGroup(layout.createParallelGroup()
					.addComponent(equipmentNameTextField, 200, 200, 400)
					.addComponent(addEquipmentButton)
					.addComponent(viewEmployeesButton))
					
					
					.addComponent(equipmentAmountLabel)
						
					.addGroup(layout.createParallelGroup()
						.addComponent(equipmentAmountTextField, 20, 20, 400)
					    .addComponent(removeEquipmentButton))
					
					.addComponent(viewSupplyButton))
				);
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {
				addSupplyButton, removeSupplyButton, supplyNameTextField, supplyAmountTextField});
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(supplyNameLabel)
						.addComponent(supplyNameTextField)
						.addComponent(supplyAmountLabel)
						.addComponent(supplyAmountTextField)
						.addComponent(equipmentNameLabel)
						.addComponent(equipmentNameTextField)
						.addComponent(equipmentAmountLabel)
						.addComponent(equipmentAmountTextField)
						.addComponent(viewSupplyButton))
						
				.addGroup(layout.createParallelGroup()		
						.addComponent(addSupplyButton)
						.addComponent(removeSupplyButton)
						.addComponent(addEquipmentButton)
						.addComponent(removeEquipmentButton))
						
						
				.addComponent(blankLine)
				.addGroup(layout.createParallelGroup()
						.addComponent(employeeNameLabel)
						.addComponent(employeeNameTextField)
						.addComponent(employeeRoleLabel)
						.addComponent(employeeRoleTextField)
						.addComponent(addEmployeeButton)
						.addComponent(viewEmployeesButton))
						
				);
		
		pack();
									
	}
	
	private void refreshData() {
		//set error
		errorMessage.setText(error);
		
		//refresh other text fields
		supplyNameTextField.setText("");
		supplyAmountTextField.setText("");
		equipmentNameTextField.setText("");
		equipmentAmountTextField.setText("");
		employeeNameTextField.setText("");
		employeeRoleTextField.setText("");
		
		pack();
	}
	
	private void addSupplyButtonActionPerformed(java.awt.event.ActionEvent evt) {
		//call controller
		FoodTruckManagementController ftmc = new FoodTruckManagementController();
		error = null;
		
		String name = supplyNameTextField.getText();
		
		int amount = 0;
		
		try {
			amount = Integer.parseInt(supplyAmountTextField.getText());
		} catch (Exception e) {
			amount = 0;
		
		} finally {
		
			try {
				ftmc.addFoodSupply(name, amount);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			} 
			
			refreshData();
		}
	
	}
	
	private void removeSupplyButtonActionPerformed(java.awt.event.ActionEvent evt) {
		FoodTruckManagementController ftmc = new FoodTruckManagementController();
		error = null;
		
		String name = supplyNameTextField.getText();
		int amount = 0;
		try {
			amount = Integer.parseInt(supplyAmountTextField.getText());
		} catch (Exception e) {
			amount = 0;
		
		} finally {
		
			try {
				ftmc.removeFoodSupply(name, amount);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			} 
			
			refreshData();
		}
		
	}
	
	private void addEquipmentButtonActionPerformed(java.awt.event.ActionEvent evt) {
		//call controller
		FoodTruckManagementController ftmc = new FoodTruckManagementController();
		error = null;
		
		String name = equipmentNameTextField.getText();
		
		int amount = 0;
		
		try {
			amount = Integer.parseInt(equipmentAmountTextField.getText());
		} catch (Exception e) {
			amount = 0;
		
		} finally {
		
			try {
				ftmc.addEquipment(name, amount);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			} 
			
			refreshData();
		}
	
	}
	
	private void removeEquipmentButtonActionPerformed(java.awt.event.ActionEvent evt) {
		FoodTruckManagementController ftmc = new FoodTruckManagementController();
		error = null;
		
		String name = equipmentNameTextField.getText();
		int amount = 0;
		try {
			amount = Integer.parseInt(equipmentAmountTextField.getText());
		} catch (Exception e) {
			amount = 0;
		
		} finally {
		
			try {
				ftmc.removeEquipment(name, amount);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			} 
			
			refreshData();
		}
		
	}
	
	private void addEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt) {
		FoodTruckManagementController ftmc = new FoodTruckManagementController();
		error = null;
		String name = employeeNameTextField.getText();
		String role = employeeRoleTextField.getText();
		
		try {
			ftmc.addEmployee(name, role);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		refreshData();
				
	}
	
	private void viewSupplyButtonActionPerformed(java.awt.event.ActionEvent evt) {
		FoodTruckManagementController ftmc = new FoodTruckManagementController();
		
		String label = ftmc.viewSupply();
		JLabel supplyList = new JLabel();
		supplyList.setText(label);
		
		JFrame frame = new JFrame("Supply List");
		
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		frame.getContentPane().add(supplyList);
		
		
		frame.pack();
		
		frame.setVisible(true);
	}
	
	private void viewEmployeesButtonActionPerformed(java.awt.event.ActionEvent evt) {
		FoodTruckManagementController ftmc = new FoodTruckManagementController();
		
		String label = ftmc.viewEmployees();
		JLabel employeeList = new JLabel();
		employeeList.setText(label);
		
		JFrame frame = new JFrame("Employee List");
		
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().add(employeeList);
		frame.pack();
		frame.setVisible(true);
	}
	


}
