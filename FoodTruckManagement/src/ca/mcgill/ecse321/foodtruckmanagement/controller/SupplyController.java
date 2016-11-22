package ca.mcgill.ecse321.foodtruckmanagement.controller;

import ca.mcgill.ecse321.foodtruckmanagement.model.FoodTruckManager;

public abstract class SupplyController {

	public abstract void addSupply(String name, int amount) throws InvalidInputException;
	public abstract void removeSupply(String name, int amount) throws InvalidInputException;
	
	public SupplyController()
	{
		
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
	
}
