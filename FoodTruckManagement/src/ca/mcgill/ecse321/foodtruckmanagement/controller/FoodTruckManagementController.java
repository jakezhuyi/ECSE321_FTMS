package ca.mcgill.ecse321.foodtruckmanagement.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import ca.mcgill.ecse321.foodtruckmanagement.model.Employee;
import ca.mcgill.ecse321.foodtruckmanagement.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagement.model.FoodSupply;
import ca.mcgill.ecse321.foodtruckmanagement.model.FoodTruckManager;
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
	
	public void assignSchedule(Employee e, Date date, Time startTime, Time endTime) throws InvalidInputException
	{
		FoodTruckManager fm = FoodTruckManager.getInstance();
		
		String error = "";
		
		if (e == null)
			error = "Employee needs to be selected for assigning a schedule! ";
		if (!fm.getEmployees().contains(e))
			error = error + "Employee does not exist! ";
		if (date == null)
			error = error + "Work date cannot be empty! ";
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
					//Check if the employee already has a schedule for this date, if so update the schedule
					if (fm.getEmployee(i).getSchedule(j).getWorkDay().equals(date))
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
	
	public String viewEmployees()
	{
		String employeeList = "<html><table><tr><td><b><u>Name</b></u></td><td><b><u>Role</b></u></td></tr>";
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		
		for (int i=0; i<fm.numberOfEmployees(); i++)
		{
			employeeList = employeeList + "<tr><td>" + fm.getEmployee(i).getName() + "</td>" + "<td>" + fm.getEmployee(i).getRole() + "</td></tr>";
		}
		
		employeeList = employeeList + "</table></html>";
		return employeeList;
	}
	
	public String viewSupply() 
	{
		String supplyList = "<html><table><tr><td><b><u>Food Supply</b></u></td><td><b><u>Equipment</b></u></td></tr>";
		
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
	
}

	