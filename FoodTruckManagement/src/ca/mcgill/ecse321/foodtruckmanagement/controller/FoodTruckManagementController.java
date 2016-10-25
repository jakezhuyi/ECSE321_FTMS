package ca.mcgill.ecse321.foodtruckmanagement.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import ca.mcgill.ecse321.foodtruckmanagement.model.Employee;
import ca.mcgill.ecse321.foodtruckmanagement.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagement.model.FoodSupply;
import ca.mcgill.ecse321.foodtruckmanagement.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruckmanagement.model.MenuItem;
import ca.mcgill.ecse321.foodtruckmanagement.model.Schedule;
import ca.mcgill.ecse321.foodtruckmanagement.persistence.PersistenceXStream;

public class FoodTruckManagementController {

	public FoodTruckManagementController()
	{
		
	}
	
	public void addFoodSupply(String name, int amount) throws InvalidInputException
	{
		String error = "";
		boolean isError = false;
		name = name.trim();

		if(name == null || name.length() == 0)
		{
			error = "Supply name cannot be empty! ";
			isError = true;
		}
		if(amount <= 0)
		{
			error = error + "Supply amount must be an integer greater than zero! ";
			isError = true;
		}
		if(isError)
			throw new InvalidInputException(error);
		
		name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		
		for(int i = 0; i < fm.numberOfFoodSupplies(); i++)
		{
			if(fm.getFoodSupply(i).getName().toUpperCase().equals(name.toUpperCase()))
			{
				amount = fm.getFoodSupply(i).getAmount() + amount;
				fm.getFoodSupply(i).setAmount(amount);
				PersistenceXStream.saveToXMLwithXStream(fm);
				return;
			}
		}
		
		FoodSupply f = new FoodSupply(name,amount);
		fm.addFoodSupply(f);
		PersistenceXStream.saveToXMLwithXStream(fm);
		
	}
	
	public void removeFoodSupply(String name, int amount) throws InvalidInputException
	{
		String error = "";
		boolean isError = false;
		name = name.trim();
		
		//Check whether the name field is empty
		if(name == null || name.length() == 0)
		{
			error = "Supply name cannot be empty! ";
			isError = true;
		}
		
		//Check whether the amount is valid
		if(amount <= 0)
		{
			error = error + "Supply amount must be an integer greater than zero! ";
			isError = true;
		}
		
		//Throw an error if either field is invalid
		if(isError)
		{
			throw new InvalidInputException(error);
		}
		
		//Call the model
		FoodTruckManager fm = FoodTruckManager.getInstance();
		
		
		//Search whether the requested food supply is in the supply
		for(int i=0; i<fm.numberOfFoodSupplies(); i++)
		{
			if(fm.getFoodSupply(i).getName().toUpperCase().equals(name.toUpperCase()))
			{
				//If they request to remove more than the existing amount, throw an error
				if (fm.getFoodSupply(i).getAmount() < amount)
				{
					error = "The existing supply of " + fm.getFoodSupply(i).getName() + " is only " + fm.getFoodSupply(i).getAmount() + "! ";
					throw new InvalidInputException(error);
				}
				
				//Remove the requested amount and update the model
				amount = fm.getFoodSupply(i).getAmount() - amount;
				fm.getFoodSupply(i).setAmount(amount);
				PersistenceXStream.saveToXMLwithXStream(fm);
				return;
			}
		}
		
		//If the food supply is not in the supply, throw an error
		throw new InvalidInputException("The supply does not contain any " + name + "!");
	}
	
	public void addEquipment(String name, int amount) throws InvalidInputException
	{
		String error = "";
		boolean isError = false;
		name = name.trim();
		
		if(name == null || name.length() == 0)
		{
			error = "Equipment name cannot be empty! ";
			isError = true;
		}
		if(amount <= 0)
		{
			error = error + "Equipment amount must be an integer greater than zero! ";
			isError = true;
		}
		if(isError)
			throw new InvalidInputException(error);
		
		name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		
		for(int i = 0; i < fm.numberOfEquipment(); i++)
		{
			if(fm.getEquipment(i).getName().toUpperCase().equals(name.toUpperCase()))
			{
				amount = fm.getEquipment(i).getAmount() + amount;
				fm.getEquipment(i).setAmount(amount);
				PersistenceXStream.saveToXMLwithXStream(fm);
				return;
			}
		}
		
		Equipment e = new Equipment(name,amount);
		fm.addEquipment(e);
		PersistenceXStream.saveToXMLwithXStream(fm);
		
	}
	
	public void removeEquipment(String name, int amount) throws InvalidInputException
	{
		String error = "";
		boolean isError = false;
		name = name.trim();
		
		//Check whether the name field is empty
		if(name == null || name.length() == 0)
		{
			error = "Equipment name cannot be empty! ";
			isError = true;
		}
		
		//Check whether the amount is valid
		if(amount <= 0)
		{
			error = error + "Equipment amount must be an integer greater than zero! ";
			isError = true;
		}
		
		//Throw an error if either field is invalid
		if(isError)
		{
			throw new InvalidInputException(error);
		}
		
		//Call the model
		FoodTruckManager fm = FoodTruckManager.getInstance();
		
		
		//Search whether the requested food supply is in the supply
		for(int i=0; i<fm.numberOfEquipment(); i++)
		{
			if(fm.getEquipment(i).getName().toUpperCase().equals(name.toUpperCase()))
			{
				//If they request to remove more than the existing amount, throw an error
				if (fm.getEquipment(i).getAmount() < amount)
				{
					error = "The existing supply of " + fm.getEquipment(i).getName() + " is only " + fm.getEquipment(i).getAmount() + "! ";
					throw new InvalidInputException(error);
				}
				
				//Remove the requested amount and update the model
				amount = fm.getEquipment(i).getAmount() - amount;
				fm.getEquipment(i).setAmount(amount);
				PersistenceXStream.saveToXMLwithXStream(fm);
				return;
			}
		}
		
		//If the food supply is not in the supply, throw an error
		throw new InvalidInputException("The supply does not contain any " + name + "!");
	}
	
	public void addEmployee(String name, String role) throws InvalidInputException
	{
		String error = "";
		boolean isError = false;
		name = name.trim();
		
		if (name == null || name.length() == 0)
		{
			error = "Employee name cannot be empty! ";
			isError = true;
		}
		
		if (role == null || role.length() == 0) 
		{
			error = error + "Employee role cannot be empty! ";
			isError = true;
		}
		
		if (isError)
		{
			throw new InvalidInputException(error);
		}
		
		name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		role = role.substring(0, 1).toUpperCase() + role.substring(1).toLowerCase();
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		Employee e = new Employee(name, role);
		fm.addEmployee(e);
		PersistenceXStream.saveToXMLwithXStream(fm);
		return;
	}
	
	public void removeEmployee (Employee e) throws InvalidInputException {
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		String error = "";
		
		if (e == null)
			error = "Employee needs to be selected for firing! ";
		else if (!fm.getEmployees().contains(e))
			error = error + "Employee does not exist! ";
		
		if (error.length() != 0)
			throw new InvalidInputException(error);
		
		fm.removeEmployee(e);
		PersistenceXStream.saveToXMLwithXStream(fm);
	}
	
	public void assignSchedule(Employee e, Date date, Time startTime, Time endTime) throws InvalidInputException
	{
		FoodTruckManager fm = FoodTruckManager.getInstance();
		
		String error = "";
		
		if (e == null)
			error = "Employee needs to be selected for assigning a schedule! ";
		else if (!fm.getEmployees().contains(e))
			error = error + "Employee does not exist! ";
		if (date == null)
			error = error + "Work date cannot be empty! ";
		else if (Calendar.getInstance().getTime().compareTo(date) > 0) 
		{
			//Make sure the date is before today's date and not today's date
			Calendar c = Calendar.getInstance();
			Date currentDate = new Date(c.getTimeInMillis());
			
				if(!currentDate.toString().equals(date.toString()))
					error = error + "Work date cannot be before today's date!"; 
		}
		if (startTime == null)
			error = error + "Shift start time cannot be empty! ";
		if (endTime == null)
			error = error + "Shift end time cannot be empty! ";
		if (endTime != null && startTime != null && endTime.getTime() < startTime.getTime())
			error = error + "Shift end time cannot be before shift start time! ";
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		//Loop through all of the employees
		for (int i=0; i<fm.numberOfEmployees(); i++)
		{
			//Check to see when the employee names match
			if (e.getName().toUpperCase().equals(fm.getEmployee(i).getName().toUpperCase()))
			{				
				for(int j=0; j<fm.getEmployee(i).numberOfSchedules(); j++)
				{
					//Check to see if the dates are the same
					boolean sameDate = fm.getEmployee(i).getSchedule(j).getWorkDay().toString().equals(date.toString());
					
					//Check if the employee already has a schedule for this date, if so update the schedule
					if (sameDate)
					{
						fm.getEmployee(i).getSchedule(j).setStartTime(startTime);
						fm.getEmployee(i).getSchedule(j).setEndTime(endTime);
						PersistenceXStream.saveToXMLwithXStream(fm);
						return;
					}
				}
				
				//If the employee doesn't have a schedule for that date, assign it to him
				Schedule s = new Schedule(date, startTime, endTime);
				fm.getEmployee(i).addSchedule(s);
				PersistenceXStream.saveToXMLwithXStream(fm);
				return;
			}
		}
	}
	
	public String viewSchedule (Employee e) throws InvalidInputException
	{
		FoodTruckManager fm = FoodTruckManager.getInstance();
		
		String error = "";
		
		if (e == null)
			error = "Employee needs to be selected for viewing a schedule! ";
		else if (!fm.getEmployees().contains(e))
			error = error + "Employee does not exist! ";
		
		if (error.length() != 0)
			throw new InvalidInputException(error);
		
		
		//Set the calendar to the Monday of the current week
		Calendar c = Calendar.getInstance();		
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int year = c.get(Calendar.YEAR);

		//Add this weeks schedule
		String scheduleList = calculateSched(c, e, day, month, year);
		
		//Add next weeks schedule
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		year = c.get(Calendar.YEAR);
		scheduleList = scheduleList + calculateSched(c, e, day, month, year);
		
		scheduleList = scheduleList + "</html>";
		
		return scheduleList;
		
	}
	
	public String viewEmployees()
	{
		String employeeList = "<html><h3 style=\"text-align:center;\">Employee List</h3>";
		employeeList = employeeList + "<table><tr><td><b><u>Name</b></u></td><td></td><td></td><td><b><u>Role</b></u></td></tr>";
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		
		for (int i=0; i<fm.numberOfEmployees(); i++)
		{
			employeeList = employeeList + "<tr><td>" + fm.getEmployee(i).getName() + "</td><td></td><td></td>" + "<td>" + fm.getEmployee(i).getRole() + "</td></tr>";
		}
		
		employeeList = employeeList + "</table></html>";
		return employeeList;
	}
	
	public String viewSupply() 
	{
		String supplyList = "<html><h3 style=\"text-align:center;\">Supply List </h3>";
		supplyList = supplyList +"<table><tr><td><b><u>Food Supply</b></u></td><td><b><u>Equipment</b></u></td></tr>";
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		
		int maxValue = Math.max(fm.numberOfFoodSupplies(), fm.numberOfEquipment());
		String [][] supplyListArray = new String [maxValue][2];
		
		for (int i=0; i<fm.numberOfFoodSupplies(); i++)
		{
			String entry = "<td>" + fm.getFoodSupply(i).getName() + " : " + fm.getFoodSupply(i).getAmount() + "</td>";
			supplyListArray[i][0] = entry;
		}
		
		for (int i=0; i<fm.numberOfEquipment(); i++)
		{
			String entry = "<td>" + fm.getEquipment(i).getName() + " : " + fm.getEquipment(i).getAmount() + "</td>";
			supplyListArray[i][1] = entry;
		}
		
		for (int i=0; i<maxValue; i++)
		{
			if(supplyListArray[i][0] == null) supplyListArray[i][0] = "<td></td>";
			if(supplyListArray[i][1] == null) supplyListArray[i][1] = "<td></td>";
			supplyList = supplyList + "<tr>" + supplyListArray[i][0] + supplyListArray[i][1] + "</tr>";
		}
		
		supplyList = supplyList + "</table></html>";
		
		return supplyList;
	}
	
	private static String getMonth(int month){
	    String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	    return monthNames[month];
	}
	
	private static String calculateSched(Calendar c, Employee e, int day, int month, int year) {
		String scheduleList = "<html><h3 style=\"text-align:center;\">" + e.getName() + "'s Schedule for Week of " + getMonth(month) + " " + day + ", " + year + "</h3></title>";
		
		scheduleList = scheduleList + "<table><tr><th><u>Monday</u></th><th><u>Tuesday</u></th><th><u>Wednesday</u></th><th><u>Thursday</u></th>"
									+ "<th><u>Friday</u></th><th><u>Saturday</u></th><th><u>Sunday</u></th></tr>";
		
		scheduleList = scheduleList + "<tr>";
		
		//Loop through all the days of the week and check the employee's schedule
		for (int i=0; i<7; i++)
		{
			Date date = new Date(c.getTimeInMillis());
			scheduleList = scheduleList + "<td>";
			
			if (e.numberOfSchedules() == 0)
				scheduleList = scheduleList + "<i>Not Scheduled</i>";

			for (int j=0; j<e.numberOfSchedules(); j++)
			{				
				Schedule employeeSchedule = e.getSchedule(j);
				
				if(employeeSchedule.getWorkDay().toString().equals(date.toString()))
				{
					scheduleList = scheduleList + employeeSchedule.getStartTime().toString().substring(0,5) + " - " + 
									employeeSchedule.getEndTime().toString().substring(0, 5);
					break;
				}
				if (j==e.numberOfSchedules()-1)
					scheduleList = scheduleList + "<i>Not Scheduled</i>";
			}
			scheduleList = scheduleList + "</td>";
			c.add(Calendar.DAY_OF_WEEK, 1);
		}
		
		scheduleList = scheduleList + "</tr></table><br/><br/>";
		
		return scheduleList;
	}
	
	public void addMenuItem(String name) throws InvalidInputException
	{
		String error = "";
		boolean isError = false;
		name = name.trim();

		FoodTruckManager fm = FoodTruckManager.getInstance();
		
		if(name == null || name.length() == 0)
		{
			error = "Menu item name cannot be empty!";
			isError = true;
		} else {
		for(int i = 0; i < fm.numberOfMenuItems(); i++)
		{
			if(fm.getMenuItem(i).getName().toUpperCase().equals(name.toUpperCase()))
			{
				isError = true;
				error = "Menu item already exists in the system!";
			}
		}
		}
		if(isError)
			throw new InvalidInputException(error);
		
		name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		MenuItem m = new MenuItem(name,0);
		fm.addMenuItem(m);
		PersistenceXStream.saveToXMLwithXStream(fm);
		
	}
	
	public void claimOrder(MenuItem m, int amount) throws InvalidInputException
	{
		String error = "";
		boolean isError = false;
		String menuItem = "";
		try{
			menuItem = m.getName().trim();
		} catch (Exception e)
		{
			menuItem = null;
		}
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		
		if(menuItem == null || menuItem.length() == 0)
		{
			isError = true;
			error = "Menu item cannot be empty! ";
		} 
		if(amount <= 0)
		{
			isError = true;
			error = error + "Menu item amount cannot be empty!";
		}
		
		if(!isError)
		{
		for(int i = 0; i < fm.numberOfMenuItems(); i++)
		{
			if(fm.getMenuItem(i).getName().toUpperCase().equals(menuItem.toUpperCase()))
			{
				fm.getMenuItem(i).setAmountSold(fm.getMenuItem(i).getAmountSold()+amount);
				PersistenceXStream.saveToXMLwithXStream(fm);
				return;
			}
		}
		}
		else
		{
			throw new InvalidInputException(error);
		}
		
		throw new InvalidInputException("There is no such menu item in the system!");
		
	}
	
	public String viewPopularityReport() 
	{
		String popularityReport = "<html><big><b><u>Popularity Report</b></u></big>";
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		
		//Sort menu items by number sold in decreasing order
			int numItems = fm.numberOfMenuItems();
			int[] decreasingOrder = new int[numItems];
			int nextMostSoldIndex = 0;
			int transferIndex = 0;
		
			//Fill decreasingOrder array with numbers ranging from 0 to numItems-1 representing every index
			for(int i = 0; i < numItems; i++)
			{
				decreasingOrder[i] = i;
			}
			
			//Sort array by index
			for(int i = 0; i < numItems; i++)
			{
				nextMostSoldIndex = i;
				for(int j = i+1; j < numItems; j++)
				{
					if(fm.getMenuItem(decreasingOrder[j]).getAmountSold() > fm.getMenuItem(decreasingOrder[nextMostSoldIndex]).getAmountSold())
					{
						nextMostSoldIndex = j;
					}
				}
				transferIndex = decreasingOrder[i];
				decreasingOrder[i] = decreasingOrder[nextMostSoldIndex];
				decreasingOrder[nextMostSoldIndex] = transferIndex;
			}
			
			popularityReport = popularityReport + "<h4>The Most Popular Menu Item is: " + fm.getMenuItem(decreasingOrder[0]).getName() + 
								"!</h4> It has sold " + fm.getMenuItem(decreasingOrder[0]).getAmountSold() + " times.<br/><br/>";
			
		
			
			//Display the top 10 most popular items (or less if there are less than 10 items)
			int top10 = Math.min(numItems, 10);
			for(int i = 1; i < top10; i++)
			{
				popularityReport = popularityReport + (i+1) + ") " +fm.getMenuItem(decreasingOrder[i]).getName() + "	:	" + fm.getMenuItem(decreasingOrder[i]).getAmountSold() + " sales." +"<br/>"; 
			}
			
			popularityReport = popularityReport + "</html>";
		return popularityReport;
	}
	
	
}

	