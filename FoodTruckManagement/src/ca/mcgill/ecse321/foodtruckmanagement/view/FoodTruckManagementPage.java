package ca.mcgill.ecse321.foodtruckmanagement.view;

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
	private JTextField supplyNameTextField;
	private JLabel supplyNameLabel;
	private JTextField supplyAmountTextField;
	private JLabel supplyAmountLabel;
	private JButton addSupplyButton;
	
	/* Creates new Page */
	public FoodTruckManagementPage() {
		initComponents();
		refreshData();
	}
	
	/*Initialize the form*/
	private void initComponents() {
		
		//elements for supply
		supplyNameTextField = new JTextField();
		supplyNameLabel = new JLabel();
		supplyAmountTextField = new JTextField();
		supplyAmountLabel = new JLabel();
		addSupplyButton = new JButton();
		
		//global settings and listeners
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Food Truck Management");
		
		supplyNameLabel.setText("Food Supply:");
		supplyAmountLabel.setText("Amount:");
		
		addSupplyButton.setText("Add Supply");
		addSupplyButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addSupplyButtonActionPerformed(evt);
			}
		});
		
		//layout
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setAutoCreateGaps(true);
		layout.setHorizontalGroup(
				layout.createSequentialGroup()
				.addComponent(supplyNameLabel)
				.addComponent(supplyNameTextField, 200, 200, 400)
				.addComponent(supplyAmountLabel)
				.addGroup(layout.createParallelGroup()
					.addComponent(supplyAmountTextField, 20, 20, 400)
					.addComponent(addSupplyButton))
				);
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {addSupplyButton, supplyNameTextField, supplyAmountTextField});
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addGroup(layout.createParallelGroup()
						.addComponent(supplyNameLabel)
						.addComponent(supplyNameTextField)
						.addComponent(supplyAmountLabel)
						.addComponent(supplyAmountTextField))
				.addComponent(addSupplyButton)
				);
		
		pack();
									
	}
	
	private void refreshData() {
		supplyNameTextField.setText("");
		supplyAmountTextField.setText("");
	}
	
	private void addSupplyButtonActionPerformed(java.awt.event.ActionEvent evt) {
		//call controller
		FoodTruckManagementController ftmc = new FoodTruckManagementController();
		
		String name = supplyNameTextField.getText();
		int amount = Integer.parseInt(supplyAmountTextField.getText());
		
		try {
			ftmc.addFoodSupply(name, amount);
		} catch (InvalidInputException e) {
			e.printStackTrace();
		}
		
		refreshData();
		
	}
	
	
	

}
