package ca.mcgill.ecse321.foodtruckmanagement.controller;

import java.util.List;

import ca.mcgill.ecse321.foodtruckmanagement.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagement.model.FoodSupply;
import ca.mcgill.ecse321.foodtruckmanagement.model.FoodTruckManager;
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
	
	public String viewSupply() 
	{
		String supplyList = "<html>\n<body>\n<table>\n<tr>\n<th>Food Supply</th>\n<th>Equipment</th></tr>\n<tr>\n";
		FoodTruckManager fm = FoodTruckManager.getInstance();
		for (int i=0; i<fm.numberOfFoodSupplies(); i++)
		{
			supplyList = supplyList + "<td>" + fm.getFoodSupply(i).getName() + " : " + fm.getFoodSupply(i).getAmount() + "</td>\n";
			String.format(supplyList);
		}
		supplyList = supplyList + "</tr>\n<tr>\n";
		for (int i=0; i<fm.numberOfEquipment(); i++)
		{
			supplyList = supplyList + "<td>" + fm.getEquipment(i).getName() + " : " + fm.getEquipment(i).getAmount() + "</td>\n";
			String.format(supplyList);
		}
		
		supplyList = supplyList + "</tr>\n</table>\n</body>\n</html>";
		return supplyList;
	}
	
}

	