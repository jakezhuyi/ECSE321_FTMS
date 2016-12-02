package ca.mcgill.ecse321.foodtruckmanagement.controller;

import ca.mcgill.ecse321.foodtruckmanagement.model.FoodSupply;
import ca.mcgill.ecse321.foodtruckmanagement.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruckmanagement.persistence.PersistenceXStream;

public class FoodSupplyController extends SupplyController {

	@Override
	public void addSupply(String name, int amount) throws InvalidInputException{
		String error = "";
		boolean isError = false;
		
		try{
			name = name.trim();
		}catch(NullPointerException e){}
		
		//Check whether the name is empty or null. If so, throw error.
		if(name == null || name.length() == 0)
		{
			error = "Supply name cannot be empty! ";
			isError = true;
		}
		//Check whether the amount is <= 0. If so, throw error
		if(amount <= 0)
		{
			error = error + "Supply amount must be an integer greater than zero! ";
			isError = true;
		}
		if(isError)
			throw new InvalidInputException(error);
		
		name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();

		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		
		//Loop through all food supply in the system and see if it exists. If so
		//add to the existing supply. If not, create a new food supply object.
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

	@Override
	public void removeSupply(String name, int amount) throws InvalidInputException{
		String error = "";
		boolean isError = false;
		
		try{
			name = name.trim();
		}catch(NullPointerException e){}
		
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

}
