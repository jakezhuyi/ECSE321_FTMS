package ca.mcgill.ecse321.foodtruckmanagement.view;

import java.awt.Color; 
import java.awt.Dimension;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import ca.mcgill.ecse321.foodtruckmanagement.controller.EmployeeController;
import ca.mcgill.ecse321.foodtruckmanagement.controller.EquipmentSupplyController;
import ca.mcgill.ecse321.foodtruckmanagement.controller.FoodSupplyController;
import ca.mcgill.ecse321.foodtruckmanagement.controller.InvalidInputException;
import ca.mcgill.ecse321.foodtruckmanagement.controller.MenuItemController;
import ca.mcgill.ecse321.foodtruckmanagement.controller.SupplyController;
import ca.mcgill.ecse321.foodtruckmanagement.model.Employee;
import ca.mcgill.ecse321.foodtruckmanagement.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruckmanagement.model.MenuItem;

public class FoodTruckManagementPage extends JFrame {


	private static final long serialVersionUID = 6324184905041245298L;
	
	//Add/viewing supply
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
	
	//Add/View Employees
	private JTextField employeeNameTextField;
	private JLabel employeeNameLabel;
	private JTextField employeeRoleTextField;
	private JLabel employeeRoleLabel;
	private JButton addEmployeeButton;
	private JButton viewEmployeesButton;
	
	//Remove Employees
	private JLabel chooseEmployeeLabel3;
	private JComboBox<String> employeeList3;
	private JButton removeEmployeeButton;
	
	//Add/View Menu Items
	private JTextField menuItemTextField;
	private JLabel menuItemNameLabel;
	private JLabel menuItemLabel;
	private JButton addMenuItemButton;
	
	//Claim Order Items
	private JComboBox<String> claimedMenuItemList;
	private JLabel claimedMenuItemLabel;
	private JLabel claimOrderLabel;
	private JTextField claimedMenuItemAmountTextField;
	private JLabel claimedMenuItemAmountLabel;
	private JButton claimOrderButton;
	
	//View Popularity Report
	private JButton viewPopularityReportButton;
	
	//Assign Schedule
	private JComboBox<String> employeeList;
	private JLabel chooseEmployeeLabel;
	private JDatePickerImpl scheduleDatePicker;
	private JLabel scheduleDateLabel;
	private JSpinner startTimeSpinner;
	private JLabel startTimeLabel;
	private JSpinner endTimeSpinner;
	private JLabel endTimeLabel;
	private JButton assignScheduleButton;
	
	//View Schedule
	private JComboBox<String> employeeList2;
	private JLabel chooseEmployeeLabel2;
	private JButton viewScheduleButton;
	
	//Labels
	private JLabel supplyMenuLabel;
	private JLabel employeeMenuLabel;
	private JLabel scheduleMenuLabel;
	
	//data elements
	private String error = null;
		
		//Used for employee combo box for assign schedule
		private Integer selectedEmployee = -1;
		private HashMap<Integer, Employee> employees;
		
		//Used for menu item combo box for claim order
		private Integer selectedMenuItem = -1;
		private HashMap<Integer, MenuItem> menuItems;
	
		//Used for viewing employee schedule
		private Integer selectedEmployee2 = -1;
		private HashMap<Integer, Employee> employees2;
	
		//Used for removing employees
		private Integer selectedEmployee3 = -1;
		private HashMap<Integer, Employee> employees3;
	
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
		supplyMenuLabel = new JLabel();
		
		//global settings and listeners
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setTitle("Food Truck Management");
		
		supplyMenuLabel.setText("<html><b><u>Supply Menu:</u></b></html>");
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
			employeeMenuLabel = new JLabel();
			
			employeeMenuLabel.setText("<html><b><u>Employee Menu:</b></u></html>");
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
			
			
			
		//Elements for menu item
			menuItemTextField = new JTextField();
			menuItemLabel = new JLabel();
			menuItemNameLabel = new JLabel();
			addMenuItemButton = new JButton();
			
			menuItemLabel.setText("<html><b><u>Menu Items:</b></u></html>");
			menuItemNameLabel.setText("Menu Item Name:");
			addMenuItemButton.setText("Add Menu Item");
			addMenuItemButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					addMenuItemButtonActionPerformed(evt);
				}
			});
			
			
		//Elements for claim order
			claimedMenuItemLabel = new JLabel();
			claimedMenuItemList = new JComboBox<String>();
			claimOrderLabel = new JLabel();
			claimedMenuItemAmountLabel = new JLabel();
			claimedMenuItemAmountTextField = new JTextField();
			claimOrderButton = new JButton();
			
			claimOrderLabel.setText("<html><b><u>Claim Order:</b></u></html>");
			claimedMenuItemLabel.setText("Menu Item: ");
			claimedMenuItemAmountLabel.setText("Amount: ");
			claimOrderButton.setText("Claim Order");
			claimOrderButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					claimOrderButtonActionPerformed(evt);
				}
			});
			//Create combo box for list of all menu items
			claimedMenuItemList = new JComboBox<String>(new String[0]);
			claimedMenuItemList.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					JComboBox<String> cb = (JComboBox<String>) evt.getSource();
					selectedMenuItem = cb.getSelectedIndex();
				}
			});
			
		//Elements for viewing popularity report
			viewPopularityReportButton = new JButton();
			
			viewPopularityReportButton.setText("View Popularity Report");
			viewPopularityReportButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					viewPopularityReportButtonActionPerformed(evt);
				}
			});
		
		//Elements for assigning schedule
			
			//Create combo box for list of all employees
			employeeList = new JComboBox<String>(new String[0]);
			employeeList.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					JComboBox<String> cb = (JComboBox<String>) evt.getSource();
					selectedEmployee = cb.getSelectedIndex();
				}
			});
			
			//Create date picker and start time/end time spinners
			SqlDateModel model = new SqlDateModel();
			Properties p = new Properties();
			p.put("text.today", "Today");
			p.put("text.month", "month");
			p.put("text.year", "year");
			JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
			scheduleDatePicker = new JDatePickerImpl(datePanel, new DateLabelFormatter());
			startTimeSpinner = new JSpinner(new SpinnerDateModel());
			JSpinner.DateEditor startTimeEditor = new JSpinner.DateEditor(startTimeSpinner, "HH:mm");
			startTimeSpinner.setEditor(startTimeEditor); //will only show current time
			endTimeSpinner = new JSpinner(new SpinnerDateModel());
			JSpinner.DateEditor endTimeEditor = new JSpinner.DateEditor(endTimeSpinner, "HH:mm");
			endTimeSpinner.setEditor(endTimeEditor); //will only show current time
			
			//Instantiate labels and buttons
			chooseEmployeeLabel = new JLabel();
			scheduleMenuLabel = new JLabel();
			assignScheduleButton = new JButton();
			scheduleDateLabel = new JLabel();
			startTimeLabel = new JLabel();
			endTimeLabel = new JLabel();
			
			//Set label text/assign schedule button action
			chooseEmployeeLabel.setText("Employee:");
			scheduleMenuLabel.setText("<html><b><u>Schedule Menu:</u></b></html>");
			scheduleDateLabel.setText("Date:");
			startTimeLabel.setText("Start Time:");
			endTimeLabel.setText("End Time:");
			assignScheduleButton.setText("Assign Schedule");
			assignScheduleButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					assignScheduleButtonActionPerformed(evt);
				}
			});
			
			
			//Elements for viewing a schedule
				//Create combo box for list of all employees
			employeeList2 = new JComboBox<String>(new String[0]);
			employeeList2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					JComboBox<String> cb = (JComboBox<String>) evt.getSource();
					selectedEmployee2 = cb.getSelectedIndex();
				}
			});
			
			//Instantiate Labels/Buttons
			chooseEmployeeLabel2 = new JLabel();
			viewScheduleButton = new JButton();
			
			//Set Label Text/assign schedule button
			chooseEmployeeLabel2.setText("Employee:");
			viewScheduleButton.setText("View Schedule");
			viewScheduleButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					viewScheduleButtonActionPerformed(evt);
				}
			});
			
			//Elements for removing an employee
			employeeList3 = new JComboBox<String>(new String[0]);
			employeeList3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					JComboBox<String> cb = (JComboBox<String>) evt.getSource();
					selectedEmployee3 = cb.getSelectedIndex();
				}
			});
			
			chooseEmployeeLabel3 = new JLabel();
			removeEmployeeButton = new JButton();
			
			chooseEmployeeLabel3.setText("Employee:");
			removeEmployeeButton.setText("Fire Employee");
			removeEmployeeButton.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent evt) {
					removeEmployeeButtonActionPerformed(evt);
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
					.addComponent(supplyMenuLabel)		
					.addComponent(supplyNameLabel)
					.addComponent(blankLine)
					.addComponent(menuItemLabel)
					.addComponent(menuItemNameLabel)
					.addComponent(blankLine)
					.addComponent(employeeMenuLabel)
					.addComponent(employeeNameLabel)
					.addComponent(blankLine)
					.addComponent(chooseEmployeeLabel3)
					.addComponent(blankLine)
					.addComponent(scheduleMenuLabel)
					.addComponent(chooseEmployeeLabel)
					.addComponent(startTimeLabel)
					.addComponent(blankLine)
					.addComponent(chooseEmployeeLabel2)
					.addComponent(blankLine)
					.addComponent(claimOrderLabel)
					.addComponent(claimedMenuItemLabel))
						
					.addGroup(layout.createParallelGroup()
					.addComponent(viewSupplyButton)
					.addComponent(supplyNameTextField, 200, 200, 400)
					.addComponent(addSupplyButton)
					.addComponent(viewPopularityReportButton)
					.addComponent(menuItemTextField, 200, 200, 400)
					.addComponent(addMenuItemButton)
					.addComponent(viewEmployeesButton)
					.addComponent(employeeNameTextField, 200, 200, 400)
					.addComponent(employeeList3)
					.addComponent(assignScheduleButton)
					.addComponent(employeeList)
					.addComponent(startTimeSpinner)
					.addComponent(employeeList2)
					.addComponent(claimedMenuItemList))
					
					.addGroup(layout.createParallelGroup()
					.addComponent(supplyAmountLabel)
					.addComponent(employeeRoleLabel)
					.addComponent(removeEmployeeButton)
					.addComponent(scheduleDateLabel)
					.addComponent(endTimeLabel)
					.addComponent(viewScheduleButton)
					.addComponent(claimedMenuItemAmountLabel))
						
					.addGroup(layout.createParallelGroup()
						.addComponent(supplyAmountTextField, 200, 200, 400)
					    .addComponent(removeSupplyButton)
					    .addComponent(employeeRoleTextField, 200, 200, 400)
					    .addComponent(scheduleDatePicker)
					    .addComponent(endTimeSpinner)
					    .addComponent(claimedMenuItemAmountTextField, 200, 200, 400))
					 
					 .addGroup(layout.createParallelGroup()   
					 .addComponent(equipmentNameLabel)
					 .addComponent(addEmployeeButton)
					 .addComponent(claimOrderButton))
						
					.addGroup(layout.createParallelGroup()
					.addComponent(equipmentNameTextField, 200, 200, 400)
					.addComponent(addEquipmentButton))
					
					.addGroup(layout.createParallelGroup()
					.addComponent(equipmentAmountLabel))
						
					.addGroup(layout.createParallelGroup()
						.addComponent(equipmentAmountTextField, 20, 20, 400)
					    .addComponent(removeEquipmentButton)))
				);
		
		layout.linkSize(SwingConstants.HORIZONTAL, new java.awt.Component[] {
				addSupplyButton, removeSupplyButton, supplyNameTextField});
		
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(errorMessage)
				
				.addGroup(layout.createParallelGroup()
						.addComponent(supplyMenuLabel)
						.addComponent(viewSupplyButton))
				
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
				.addComponent(menuItemLabel)
				.addComponent(viewPopularityReportButton))
				.addGroup(layout.createParallelGroup()
				.addComponent(menuItemNameLabel)
				.addComponent(menuItemTextField))
				.addComponent(addMenuItemButton)
				
				.addComponent(blankLine)
				
				.addGroup(layout.createParallelGroup()
						.addComponent(employeeMenuLabel)
						.addComponent(viewEmployeesButton))
				
				.addGroup(layout.createParallelGroup()
						.addComponent(employeeNameLabel)
						.addComponent(employeeNameTextField)
						.addComponent(employeeRoleLabel)
						.addComponent(employeeRoleTextField)
						.addComponent(addEmployeeButton)
						.addComponent(viewEmployeesButton))
				
				.addComponent(blankLine)
				
				.addGroup(layout.createParallelGroup()
						.addComponent(chooseEmployeeLabel3)
						.addComponent(employeeList3)
						.addComponent(removeEmployeeButton))
				
				.addComponent(blankLine)
				
				.addGroup(layout.createParallelGroup()
				.addComponent(scheduleMenuLabel)
				.addComponent(assignScheduleButton))
				
				.addGroup(layout.createParallelGroup()
				.addComponent(chooseEmployeeLabel)
				.addComponent(employeeList)
				.addComponent(scheduleDateLabel)
				.addComponent(scheduleDatePicker))
				
				.addGroup(layout.createParallelGroup()
				.addComponent(startTimeLabel)
				.addComponent(startTimeSpinner)
				.addComponent(endTimeLabel)
				.addComponent(endTimeSpinner)
				.addComponent(assignScheduleButton))
				
				.addComponent(blankLine)
				
				.addGroup(layout.createParallelGroup()
						.addComponent(chooseEmployeeLabel2)
						.addComponent(employeeList2)
						.addComponent(viewScheduleButton))
				
				.addComponent(blankLine)
				
				.addComponent(claimOrderLabel)
				.addGroup(layout.createParallelGroup()
				.addComponent(claimedMenuItemLabel)
				.addComponent(claimedMenuItemList)
				.addComponent(claimedMenuItemAmountLabel)
				.addComponent(claimedMenuItemAmountTextField)
				.addComponent(claimOrderButton))
				
						
				);
		
		pack();
									
	}
	
	private void refreshData() {
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		
		//set error
		errorMessage.setText(error);
		
		//set employee list for assign employee schedule field
		employees = new HashMap<Integer, Employee>();
		employeeList.removeAllItems();
		Iterator<Employee> eIt = fm.getEmployees().iterator();
		Integer index = 0;
		while (eIt.hasNext()) {
			Employee e = eIt.next();
			employees.put(index, e);
			employeeList.addItem(e.getName());
			index++;
		}
		selectedEmployee = -1;
		employeeList.setSelectedIndex(selectedEmployee);
		
		//set employee list 2 for view employee schedule field
		employees2 = new HashMap<Integer, Employee>();
		employeeList2.removeAllItems();
		Iterator<Employee> eIt2 = fm.getEmployees().iterator();
		Integer index2 = 0;
		while (eIt2.hasNext()) {
			Employee e = eIt2.next();
			employees2.put(index2, e);
			employeeList2.addItem(e.getName());
			index2++;
		}
		selectedEmployee2 = -1;
		employeeList2.setSelectedIndex(selectedEmployee2);
		
		//Set employee list 3 
		employees3 = new HashMap<Integer, Employee>();
		employeeList3.removeAllItems();
		Iterator<Employee> eIt3 = fm.getEmployees().iterator();
		Integer index3 = 0;
		while (eIt3.hasNext()) {
			Employee e = eIt3.next();
			employees3.put(index3, e);
			employeeList3.addItem(e.getName());
			index3++;
		}
		selectedEmployee3 = -1;
		employeeList3.setSelectedIndex(selectedEmployee3);
		
		//set menu item list for claim order field
				menuItems = new HashMap<Integer, MenuItem>();
				claimedMenuItemList.removeAllItems();
				Iterator<MenuItem> mIt = fm.getMenuItems().iterator();
				Integer index4 = 0;
				while (mIt.hasNext()) {
					MenuItem m = mIt.next();
					menuItems.put(index4, m);
					claimedMenuItemList.addItem(m.getName());
					index4++;
				}
				selectedMenuItem = -1;
				claimedMenuItemList.setSelectedIndex(selectedMenuItem);
		
		//refresh other text fields
		supplyNameTextField.setText("");
		supplyAmountTextField.setText("");
		equipmentNameTextField.setText("");
		equipmentAmountTextField.setText("");
		employeeNameTextField.setText("");
		employeeRoleTextField.setText("");
		menuItemTextField.setText("");
		claimedMenuItemAmountTextField.setText("");
		scheduleDatePicker.getModel().setValue(null);
		startTimeSpinner.setValue(new Date());
		endTimeSpinner.setValue(new Date());
		
		pack();
	}
	
	private void addSupplyButtonActionPerformed(java.awt.event.ActionEvent evt) {
		//call controller
		SupplyController ftmc = new FoodSupplyController();
		error = null;
		
		String name = supplyNameTextField.getText();
		
		int amount = 0;
		
		try {
			amount = Integer.parseInt(supplyAmountTextField.getText());
		} catch (Exception e) {
			amount = 0;
		
		} finally {
		
			try {
				ftmc.addSupply(name, amount);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			} 
			
			refreshData();
		}
	
	}
	
	private void removeSupplyButtonActionPerformed(java.awt.event.ActionEvent evt) {
		SupplyController ftmc = new FoodSupplyController();
		error = null;
		
		String name = supplyNameTextField.getText();
		int amount = 0;
		try {
			amount = Integer.parseInt(supplyAmountTextField.getText());
		} catch (Exception e) {
			amount = 0;
		
		} finally {
		
			try {
				ftmc.removeSupply(name, amount);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			} 
			
			refreshData();
		}
		
	}
	
	private void addEquipmentButtonActionPerformed(java.awt.event.ActionEvent evt) {
		//call controller
		SupplyController ftmc = new EquipmentSupplyController();
		error = null;
		
		String name = equipmentNameTextField.getText();
		
		int amount = 0;
		
		try {
			amount = Integer.parseInt(equipmentAmountTextField.getText());
		} catch (Exception e) {
			amount = 0;
		
		} finally {
		
			try {
				ftmc.addSupply(name, amount);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			} 
			
			refreshData();
		}
	
	}
	
	private void removeEquipmentButtonActionPerformed(java.awt.event.ActionEvent evt) {
		SupplyController ftmc = new EquipmentSupplyController();
		error = null;
		
		String name = equipmentNameTextField.getText();
		int amount = 0;
		try {
			amount = Integer.parseInt(equipmentAmountTextField.getText());
		} catch (Exception e) {
			amount = 0;
		
		} finally {
		
			try {
				ftmc.removeSupply(name, amount);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			} 
			
			refreshData();
		}
		
	}
	
	private void viewSupplyButtonActionPerformed(java.awt.event.ActionEvent evt) {
		SupplyController ftmc = new EquipmentSupplyController();
		
		String label = ftmc.viewSupply();
		JLabel supplyList = new JLabel();
		supplyList.setText(label);
		
		JFrame frame = new JFrame("Supply List");
		
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		frame.getContentPane().add(supplyList);
		
		
		frame.pack();
		
		frame.setVisible(true);
	}
	
	
	private void addEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt) {
		EmployeeController ftmc = new EmployeeController();
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
	
	private void removeEmployeeButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = null;
		EmployeeController ftmc = new EmployeeController();
		
		try {
			ftmc.removeEmployee(employees3.get(selectedEmployee3));
			JFrame frame = new JFrame("You're Fired!");
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			JLabel label = new JLabel();
			String text = "<html><center><big>" + employees3.get(selectedEmployee3).getName() + ", You're Fired!</big></center><br/><img src=\"http://www.jobscience.com/wp-content/uploads/2013/08/Donald-Trump-Youre-Fired.jpg\" alt=\"Mountain View\" style=\"width:304px;height:228px\"></html>";
			label.setText(text);
			
			
			frame.getContentPane().add(label);
			
			frame.pack();
			frame.setVisible(true);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		
		refreshData();
		
	}
	
	private void viewEmployeesButtonActionPerformed(java.awt.event.ActionEvent evt) {
		EmployeeController ftmc = new EmployeeController();
		
		String label = ftmc.viewEmployees();
		JLabel employeeList = new JLabel();
		employeeList.setText(label);
		
		JFrame frame = new JFrame("Employee List");
		
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().add(employeeList);
		frame.pack();
		frame.setVisible(true);
	}
	
	private void assignScheduleButtonActionPerformed(java.awt.event.ActionEvent evt) {
		
		error = null;
		
		//force the same date for start and end time to ensure only times differ
		Calendar calendar = Calendar.getInstance();
		calendar.setTime((Date)startTimeSpinner.getValue());
		calendar.set(2000,1,1);
		Time startTime = new Time(calendar.getTime().getTime());
		calendar.setTime((Date)endTimeSpinner.getValue());
		calendar.set(2000,1,1);
		Time endTime = new Time(calendar.getTime().getTime());
		
		
		//call the controller
		EmployeeController ftmc = new EmployeeController();
		try {
			ftmc.assignSchedule(employees.get(selectedEmployee),(java.sql.Date) scheduleDatePicker.getModel().getValue(), startTime, endTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		
		//update visuals
		refreshData();
	}
	

	private void viewScheduleButtonActionPerformed(java.awt.event.ActionEvent evt) {
		error = null;
		
		EmployeeController ftmc = new EmployeeController();
		try {
			String label = ftmc.viewSchedule(employees2.get(selectedEmployee2));
			JLabel weeklySchedule = new JLabel();
			weeklySchedule.setText(label);
			JFrame frame = new JFrame(employees2.get(selectedEmployee2).getName() + "'s Schedule");
			frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
			frame.getContentPane().add(weeklySchedule);
			frame.pack();
			frame.setVisible(true);
			
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		refreshData();
	}
	
	private void addMenuItemButtonActionPerformed(java.awt.event.ActionEvent evt) {
		//call controller
		MenuItemController ftmc = new MenuItemController();
		error = null;
		
		String name = menuItemTextField.getText();
		
			try {
				ftmc.addMenuItem(name);
			} catch (InvalidInputException e) {
				error = e.getMessage();
			} 
			refreshData();
	}
	
	private void claimOrderButtonActionPerformed(java.awt.event.ActionEvent evt){
		//call controller
				MenuItemController ftmc = new MenuItemController();
				error = null;
				
				MenuItem claimedMenuItem = menuItems.get(selectedMenuItem);
				int amount = 0;
				
				try {
					amount = Integer.parseInt(claimedMenuItemAmountTextField.getText());
				} catch (Exception e) {
					amount = 0;
				
				} finally {
				
					try {
						ftmc.claimOrder(claimedMenuItem,amount);
					} catch (InvalidInputException e) {
						error = e.getMessage();
					} 
					
					refreshData();
				}
		
		
	}
	


	private void viewPopularityReportButtonActionPerformed(java.awt.event.ActionEvent evt) {
		MenuItemController ftmc = new MenuItemController();
		
		String label = ftmc.viewPopularityReport();
		JLabel popularityReportLabel = new JLabel();
		popularityReportLabel.setText(label);
		
		JFrame frame = new JFrame("Popularity Report");
		
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		frame.getContentPane().add(popularityReportLabel);
		
		frame.pack();
		
		frame.setVisible(true);
	}
	

	

}
