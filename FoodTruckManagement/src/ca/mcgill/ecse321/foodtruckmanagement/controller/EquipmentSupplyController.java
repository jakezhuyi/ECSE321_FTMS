package ca.mcgill.ecse321.foodtruckmanagement.controller;

import ca.mcgill.ecse321.foodtruckmanagement.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagement.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruckmanagement.persistence.PersistenceXStream;

public class EquipmentSupplyController extends SupplyController {

	@Override
	public void addSupply(String name, int amount) throws InvalidInputException {
		String error = "";
		boolean isError = false;
		
		try{
			name = name.trim();
		}catch(NullPointerException e){}
		
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

	@Override
	public void removeSupply(String name, int amount)
			throws InvalidInputException {
		String error = "";
		boolean isError = false;
		
		try{
			name = name.trim();
		}catch(NullPointerException e){}
		
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

}
