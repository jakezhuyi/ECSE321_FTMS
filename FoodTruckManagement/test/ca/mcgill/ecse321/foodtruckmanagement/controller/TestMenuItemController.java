package ca.mcgill.ecse321.foodtruckmanagement.controller;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.foodtruckmanagement.model.FoodSupply;
import ca.mcgill.ecse321.foodtruckmanagement.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruckmanagement.model.MenuItem;
import ca.mcgill.ecse321.foodtruckmanagement.persistence.PersistenceXStream;

public class TestMenuItemController {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.setFilename("test"+File.separator+"ca"+File.separator+"mcgill"+File.separator+"ecse321"+File.separator+"foodtruckmanagement"+File.separator+"controller"+File.separator+"data.xml");
		PersistenceXStream.setAlias("menuitem", MenuItem.class);
	}

	@After
	public void tearDown() throws Exception {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		ftm.delete();
	}

	
	@Test
	public void testAddMenuItem() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getMenuItems().size());
		
		String name = "Burger";
		
		MenuItemController mic = new MenuItemController();
		try {
			mic.addMenuItem(name);
		} catch (InvalidInputException e) {
			//check that no error occurred
			fail();
		}
		
		assertEquals(1, ftm.getMenuItems().size());
		assertEquals(name, ftm.getMenuItem(0).getName());
		
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//Check File Contents ***May need to check view supply aswell??
		assertEquals(1, ftm2.getMenuItems().size());
		assertEquals(name, ftm2.getMenuItem(0).getName());
	}

	
	@Test
	public void testAddMenuItemNull() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getMenuItems().size());
		
		String name = null;
		String error = null;
		
		MenuItemController mic = new MenuItemController();
		try {
			mic.addMenuItem(name);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error **Pretty sure the extra space at the end isnt needed but its used in the supply controller
		assertEquals("Menu item name cannot be empty!", error);
		
		//check model in memory
		assertEquals(0, ftm.getMenuItems().size());
	}
	
	@Test
	public void testAddMenuItemEmpty() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getMenuItems().size());
		
		String name = "";
		String error = null;
		
		MenuItemController mic = new MenuItemController();
		try {
			mic.addMenuItem(name);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error **Pretty sure the extra space at the end isnt needed but its used in the supply controller
		assertEquals("Menu item name cannot be empty!", error);
		
		//check model in memory
		assertEquals(0, ftm.getMenuItems().size());
	}
	
	@Test
	public void testAddMenuItemAlreadyExists()
	{
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getMenuItems().size());
		
		String name = "Burger";
		String error = null;
		
		MenuItemController mic = new MenuItemController();
		try {
			mic.addMenuItem(name);
		} catch (InvalidInputException e) {
			//check that no error occured
			fail();
		}
		
		try {
			mic.addMenuItem(name);
		} catch (InvalidInputException e) {
			
			error = e.getMessage();
		}
		
		//check error **Pretty sure the extra space at the end isnt needed but its used in the supply controller
		assertEquals("Menu item already exists in the system!", error);
		
		//check model in memory
		assertEquals(1, ftm.getMenuItems().size());
	}
	
	@Test
	public void testClaimOrder()
	{
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getMenuItems().size());
		
		String name = "Burger";
		int amount = 1;
		
		MenuItemController mic = new MenuItemController();
		try {
			mic.addMenuItem(name);
		} catch (InvalidInputException e) {
			//check that no error occured
			fail();
		}
		
		try {
			mic.claimOrder(ftm.getMenuItem(0),amount);
		} catch (InvalidInputException e) {
			//check that no error occured
			fail();
		}
		
		assertEquals(1, ftm.getMenuItem(0).getAmountSold());
		
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//Check File Contents ***May need to check view supply aswell??
		assertEquals(1, ftm2.getMenuItem(0).getAmountSold());
		
	}

	@Test
	public void testClaimOrderNull() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getMenuItems().size());
		
		int amount = 0;
		String error = null;
		
		MenuItemController mic = new MenuItemController();
		try {
			mic.claimOrder(null,amount);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error **Pretty sure the extra space at the end isnt needed but its used in the supply controller
		assertEquals("Menu item name cannot be empty! Menu item amount cannot be empty!", error);
	}
	
	@Test
	public void testClaimOrderNegativeAmount(){
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getMenuItems().size());
		
		String name = "Burger";
		int amount = -1;
		String error = null;
		
		
		MenuItemController mic = new MenuItemController();
		try {
			mic.addMenuItem(name);
		} catch (InvalidInputException e) {
			//check that no error occured
			fail();
		}
		
		try {
			mic.claimOrder(ftm.getMenuItem(0),amount);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error **Pretty sure the extra space at the end isnt needed but its used in the supply controller
		assertEquals("Menu item amount cannot be empty!", error);
		
		
	}
}
