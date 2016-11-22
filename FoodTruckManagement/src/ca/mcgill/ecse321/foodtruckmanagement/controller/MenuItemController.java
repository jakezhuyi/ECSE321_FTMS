package ca.mcgill.ecse321.foodtruckmanagement.controller;

import ca.mcgill.ecse321.foodtruckmanagement.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruckmanagement.model.MenuItem;
import ca.mcgill.ecse321.foodtruckmanagement.persistence.PersistenceXStream;

public class MenuItemController {

	public MenuItemController()
	{
		
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
		if(amount == 0)
		{
			isError = true;
			error = error + "Menu item amount cannot be empty!";
		}
		else if(amount < 0)
		{
			isError = true;
			error = error + "Menu item amount cannot be negative!";
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
