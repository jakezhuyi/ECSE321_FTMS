package ca.mcgill.ecse321.foodtruckmanagement.controller;

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
		
		FoodSupply f = new FoodSupply(name,amount);
		FoodTruckManager fm = FoodTruckManager.getInstance();
		fm.addFoodSupply(f);
		PersistenceXStream.saveToXMLwithXStream(fm);
	}
	
}