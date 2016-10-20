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
	private JButton viewSupplyButton;
	
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
		
		addSupplyButton.setText("Add To Supply");
		addSupplyButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addSupplyButtonActionPerformed(evt);
			}
		});
		
		removeSupplyButton.setText("Remove From Supply");
		removeSupplyButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				removeSupplyButtonActionPerformed(evt);
			}
		}); 
		
		viewSupplyButton.setText("View Supply");
		viewSupplyButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				viewSupplyButtonActionPerformed(evt);
			}
		});
		
		//layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setHorizontalGroup(
				layout.createParallelGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createSequentialGroup()
						
					.addComponent(supplyNameLabel)
						
					.addGroup(layout.createParallelGroup()
					.addComponent(supplyNameTextField, 200, 200, 400)
					.addComponent(addSupplyButton))
					
					
					.addComponent(supplyAmountLabel)
						
					.addGroup(layout.createParallelGroup()
						.addComponent(supplyAmountTextField, 20, 20, 400)
					    .addComponent(removeSupplyButton))
					    
					.addComponent(viewSupplyButton))
				);
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {addSupplyButton, removeSupplyButton, supplyNameTextField, supplyAmountTextField});
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				.addGroup(layout.createParallelGroup()
						.addComponent(supplyNameLabel)
						.addComponent(supplyNameTextField)
						.addComponent(supplyAmountLabel)
						.addComponent(supplyAmountTextField)
						.addComponent(viewSupplyButton))
				.addGroup(layout.createParallelGroup()		
						.addComponent(addSupplyButton)
						.addComponent(removeSupplyButton)
				));
		
		pack();
									
	}
	
	private void refreshData() {
		//set error
		errorMessage.setText(error);
		
		//refresh other text fields
		supplyNameTextField.setText("");
		supplyAmountTextField.setText("");
		
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
	
	private void viewSupplyButtonActionPerformed(java.awt.event.ActionEvent evt) {
		FoodTruckManagementController ftmc = new FoodTruckManagementController();
		
		String label = ftmc.viewSupply();
		JLabel supplyList = new JLabel();
		supplyList.setText(label);
		
		JFrame frame = new JFrame("Supply List");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().add(supplyList);
		
		Dimension d = new Dimension();
		d.setSize(500, 200);
		
		frame.setSize(d);
		
		frame.setVisible(true);
	}
	
	
	

}
