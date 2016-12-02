package ca.mcgill.ecse321.foodtruckmanagement.controller;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.foodtruckmanagement.controller.InvalidInputException;
import ca.mcgill.ecse321.foodtruckmanagement.model.FoodSupply;
import ca.mcgill.ecse321.foodtruckmanagement.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruckmanagement.persistence.PersistenceXStream;



public class TestFoodSupplyController {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.setFilename("test"+File.separator+"ca"+File.separator+"mcgill"+File.separator+"ecse321"+File.separator+"foodtruckmanagement"+File.separator+"controller"+File.separator+"data.xml");
		PersistenceXStream.setAlias("foodsupply", FoodSupply.class);
	}

	@After
	public void tearDown() throws Exception {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		ftm.delete();
	}

	
	@Test
	public void testAddFoodSupply() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getFoodSupplies().size());
		
		String name = "Tomatoe";
		int amount = 1;
		
		SupplyController sc = new FoodSupplyController();
		try {
			sc.addSupply(name,amount);
		} catch (InvalidInputException e) {
			//check that no error occurred
			fail();
		}
		
		assertEquals(1, ftm.getFoodSupplies().size());
		assertEquals(name, ftm.getFoodSupply(0).getName());
		assertEquals(amount,ftm.getFoodSupply(0).getAmount());
		
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//Check File Contents ***May need to check view supply aswell??
		assertEquals(1, ftm2.getFoodSupplies().size());
		assertEquals(name, ftm2.getFoodSupply(0).getName());
		assertEquals(amount,ftm2.getFoodSupply(0).getAmount());
	}

	@Test
	public void testAddTooFoodSupply() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getFoodSupplies().size());
		
		String name = "Tomatoe";
		int amount = 1;
		
		SupplyController sc = new FoodSupplyController();
		try {
			sc.addSupply(name,amount);
		} catch (InvalidInputException e) {
			//check that no error occurred
			fail();
		}
		
		//add 1 to existing supply
		try {
			sc.addSupply(name,amount);
		} catch (InvalidInputException e) {
			//check that no error occurred
			fail();
		}
		
		assertEquals(2,ftm.getFoodSupply(0).getAmount());
		
		//Check model
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();

		assertEquals(2,ftm2.getFoodSupply(0).getAmount());
	}
	
	@Test
	public void testAddFoodSupplyNull() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getFoodSupplies().size());
		
		String name = null;
		int amount = 0;
		
		String error = null;
		SupplyController sc = new FoodSupplyController();
		try {
			sc.addSupply(name,amount);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error **Pretty sure the extra space at the end isnt needed but its used in the supply controller
		assertEquals("Supply name cannot be empty! Supply amount must be an integer greater than zero! ", error);
		
		//check model in memory
		assertEquals(0, ftm.getFoodSupplies().size());
	}
	
	@Test
	public void testAddFoodSupplyEmpty() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getFoodSupplies().size());
		
		String name = "";
		int amount = 0;
		
		String error = null;
		SupplyController sc = new FoodSupplyController();
		try {
			sc.addSupply(name,amount);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error **Pretty sure the extra space at the end isnt needed but its used in the supply controller
		assertEquals("Supply name cannot be empty! Supply amount must be an integer greater than zero! ", error);
		
		//check model in memory
		assertEquals(0, ftm.getFoodSupplies().size());
	}
	
	
	@Test
	public void testAddFoodSupplyNegativeAmount() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getFoodSupplies().size());
		
		String name = "Tomatoe";
		int amount = -1;
		
		String error = null;
		SupplyController sc = new FoodSupplyController();
		try {
			sc.addSupply(name,amount);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error **Pretty sure the extra space at the end isnt needed but its used in the supply controller
		assertEquals("Supply amount must be an integer greater than zero! ", error);
		
		//check model in memory
		assertEquals(0, ftm.getFoodSupplies().size());
	}
	
	@Test
	public void testRemoveFoodSupply() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getFoodSupplies().size());
		
		String name = "Tomatoe";
		int amount = 1;
		
		SupplyController sc = new FoodSupplyController();
		
		try{
			sc.addSupply(name,amount);
		}catch(InvalidInputException e){
			//check that no error occured
			fail();
		}
		
		
		try {
			sc.removeSupply(name,amount);
		} catch (InvalidInputException e) {
			//check that no error occurred
			fail();
		}
		
		assertEquals(0, ftm.getFoodSupply(0).getAmount());
		
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//Check in memory
		assertEquals(0, ftm2.getFoodSupply(0).getAmount());
	}

	
	@Test
	public void testRemoveFoodSupplyNull() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getFoodSupplies().size());
		
		String name = null;
		int amount = 0;
		
		String error = null;
		SupplyController sc = new FoodSupplyController();
		try {
			sc.removeSupply(name,amount);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error **Pretty sure the extra space at the end isnt needed but its used in the supply controller
		assertEquals("Supply name cannot be empty! Supply amount must be an integer greater than zero! ", error);
	}
	
	@Test
	public void testRemoveFoodSupplyEmpty() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getFoodSupplies().size());
		
		String name = "";
		int amount = 0;
		
		String error = null;
		SupplyController sc = new FoodSupplyController();
		try {
			sc.removeSupply(name,amount);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error **Pretty sure the extra space at the end isnt needed but its used in the supply controller
		assertEquals("Supply name cannot be empty! Supply amount must be an integer greater than zero! ", error);

	}
	
	
	@Test
	public void testRemoveFoodSupplyNegativeAmount() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getFoodSupplies().size());
		
		String name = "Tomatoe";
		int amount = 1;
		
		String error = null;
		SupplyController sc = new FoodSupplyController();
		
		try{
		sc.addSupply(name, amount);
		} catch(InvalidInputException e)
		{
			//check that no error occured
			fail();
		}
		
		amount = -1;
		
		try {
			sc.removeSupply(name,amount);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error **Pretty sure the extra space at the end isnt needed but its used in the supply controller
		assertEquals("Supply amount must be an integer greater than zero! ", error);
		
		//check model in memory
		assertEquals(1, ftm.getFoodSupplies().size());
	}
	
	public void testRemoveFoodSupplyWithGreaterAmountThanExists() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getFoodSupplies().size());
		
		String name = "Tomatoe";
		int amount = 1;
		
		String error = null;
		SupplyController sc = new FoodSupplyController();
		
		try{
		sc.addSupply(name, amount);
		} catch(InvalidInputException e)
		{
			//check that no error occured
			fail();
		}
		
		amount = 2;
		
		try {
			sc.removeSupply(name,amount);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error **Pretty sure the extra space at the end isnt needed but its used in the supply controller
		assertEquals("The existing supply of " + ftm.getFoodSupply(0).getName() + " is only " + ftm.getFoodSupply(0).getAmount() + "! ", error);
		
		//check model in memory
		assertEquals(1, ftm.getFoodSupplies().size());
	}
	
	public void testRemoveFoodSupplyNameNotInSupply()
	{
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getFoodSupplies().size());
		
		String name = "Tomatoe";
		int amount = 1;
		
		String error = null;
		SupplyController sc = new FoodSupplyController();
		
		try{
			sc.addSupply(name,amount);
		}catch(InvalidInputException e)
		{
			//check that no error occured
			fail();
		}
		
		name = "Cheese";
		
		try{
			sc.removeSupply(name, amount);
		}catch(InvalidInputException e)
		{
			error = e.getMessage();
		}
		
		//check error
		assertEquals("The supply does not contain any " + name + "!",error);
		
		//check model in memory
		assertEquals(1,ftm.getFoodSupplies().size());
	}

}
