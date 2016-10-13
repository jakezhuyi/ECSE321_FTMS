package ca.mcgill.ecse321.foodtruckmanagement.controller;

import java.util.List;

import ca.mcgill.ecse321.foodtruckmanagement.model.FoodSupply;
import ca.mcgill.ecse321.foodtruckmanagement.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruckmanagement.persistence.PersistenceXStream;

public class FoodTruckManagementController {

	public FoodTruckManagementController()
	{
		
	}
	
	public void addFoodSupply(String name, int amount) throws InvalidInputException
	{
		if(name == null || name.trim().length() == 0)
			throw new InvalidInputException("Supply name cannot be empty!");
		if(amount <= 0)
			throw new InvalidInputException("Supply amount must be greater than zero!");
		
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
	
}