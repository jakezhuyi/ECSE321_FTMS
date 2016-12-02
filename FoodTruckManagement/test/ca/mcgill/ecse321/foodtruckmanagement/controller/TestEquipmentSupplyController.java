package ca.mcgill.ecse321.foodtruckmanagement.controller;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.foodtruckmanagement.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagement.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruckmanagement.persistence.PersistenceXStream;

public class TestEquipmentSupplyController {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.setFilename("test"+File.separator+"ca"+File.separator+"mcgill"+File.separator+"ecse321"+File.separator+"foodtruckmanagement"+File.separator+"controller"+File.separator+"data.xml");
		PersistenceXStream.setAlias("equipment", Equipment.class);
	}

	@After
	public void tearDown() throws Exception {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		ftm.delete();
	}

	
	@Test
	public void testAddEquipmentSupply() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getEquipment().size());
		
		String name = "Grill";
		int amount = 1;
		
		SupplyController sc = new EquipmentSupplyController();
		try {
			sc.addSupply(name,amount);
		} catch (InvalidInputException e) {
			//check that no error occurred
			fail();
		}
		
		assertEquals(1, ftm.getEquipment().size());
		assertEquals(name, ftm.getEquipment(0).getName());
		assertEquals(amount,ftm.getEquipment(0).getAmount());
		
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		assertEquals(1, ftm2.getEquipment().size());
		assertEquals(name, ftm2.getEquipment(0).getName());
		assertEquals(amount,ftm2.getEquipment(0).getAmount());
	}

	@Test
	public void testAddTooEquipmentSupply() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getEquipment().size());
		
		String name = "Grill";
		int amount = 1;
		
		SupplyController sc = new EquipmentSupplyController();
		try {
			sc.addSupply(name,amount);
		} catch (InvalidInputException e) {
			//check that no error occurred
			fail();
		}
		
		//Add 1 to existing supply amount
		try {
			sc.addSupply(name,amount);
		} catch (InvalidInputException e) {
			//check that no error occurred
			fail();
		}
		
		assertEquals(2,ftm.getEquipment(0).getAmount());
		
		//Check model
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();

		assertEquals(2,ftm2.getEquipment(0).getAmount());
	}
	
	@Test
	public void testAddEquipmentSupplyNull() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getEquipment().size());
		
		String name = null;
		int amount = 0;
		
		String error = null;
		SupplyController sc = new EquipmentSupplyController();
		try {
			sc.addSupply(name,amount);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error **Pretty sure the extra space at the end isnt needed but its used in the supply controller
		assertEquals("Equipment name cannot be empty! Equipment amount must be an integer greater than zero! ", error);
		
		//check model in memory
		assertEquals(0, ftm.getEquipment().size());
	}
	
	@Test
	public void testAddEquipmentSupplyEmpty() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getEquipment().size());
		
		String name = "";
		int amount = 0;
		
		String error = null;
		SupplyController sc = new EquipmentSupplyController();
		try {
			sc.addSupply(name,amount);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error **Pretty sure the extra space at the end isnt needed but its used in the supply controller
		assertEquals("Equipment name cannot be empty! Equipment amount must be an integer greater than zero! ", error);
		
		//check model in memory
		assertEquals(0, ftm.getEquipment().size());
	}
	
	
	@Test
	public void testAddEquipmentSupplyNegativeAmount() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getEquipment().size());
		
		String name = "Grill";
		int amount = -1;
		
		String error = null;
		SupplyController sc = new EquipmentSupplyController();
		try {
			sc.addSupply(name,amount);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error **Pretty sure the extra space at the end isnt needed but its used in the supply controller
		assertEquals("Equipment amount must be an integer greater than zero! ", error);
		
		//check model in memory
		assertEquals(0, ftm.getEquipment().size());
	}
	
	
	
	@Test
	public void testRemoveEquipmentSupply() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getEquipment().size());
		
		String name = "Grill";
		int amount = 1;
		
		SupplyController sc = new EquipmentSupplyController();
		
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
		
		assertEquals(0, ftm.getEquipment(0).getAmount());
		
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		//Check in memory
		assertEquals(0, ftm2.getEquipment(0).getAmount());
	}

	
	@Test
	public void testRemoveEquipmentSupplyNull() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getEquipment().size());
		
		String name = null;
		int amount = 0;
		
		String error = null;
		SupplyController sc = new EquipmentSupplyController();
		try {
			sc.removeSupply(name,amount);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error **Pretty sure the extra space at the end isnt needed but its used in the supply controller
		assertEquals("Equipment name cannot be empty! Equipment amount must be an integer greater than zero! ", error);
	}
	
	@Test
	public void testRemoveEquipmentSupplyEmpty() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getEquipment().size());
		
		String name = "";
		int amount = 0;
		
		String error = null;
		SupplyController sc = new EquipmentSupplyController();
		try {
			sc.removeSupply(name,amount);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error **Pretty sure the extra space at the end isnt needed but its used in the supply controller
		assertEquals("Equipment name cannot be empty! Equipment amount must be an integer greater than zero! ", error);

	}
	
	
	@Test
	public void testRemoveEquipmentSupplyNegativeAmount() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getEquipment().size());
		
		String name = "Grill";
		int amount = 1;
		
		String error = null;
		SupplyController sc = new EquipmentSupplyController();
		
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
		assertEquals("Equipment amount must be an integer greater than zero! ", error);
		
		//check model in memory
		assertEquals(1, ftm.getEquipment().size());
	}
	
	public void testRemoveEquipmentSupplyWithGreaterAmountThanExists() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getEquipment().size());
		
		String name = "Grill";
		int amount = 1;
		
		String error = null;
		SupplyController sc = new EquipmentSupplyController();
		
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
		assertEquals("The existing supply of " + ftm.getEquipment(0).getName() + " is only " + ftm.getEquipment(0).getAmount() + "! ", error);
		
		//check model in memory
		assertEquals(1, ftm.getEquipment().size());
	}
	
	public void testRemoveEquipmentSupplyNameNotInSupply()
	{
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0,ftm.getEquipment().size());
		
		String name = "Grill";
		int amount = 1;
		
		String error = null;
		SupplyController sc = new EquipmentSupplyController();
		
		try{
			sc.addSupply(name,amount);
		}catch(InvalidInputException e)
		{
			//check that no error occured
			fail();
		}
		
		name = "Toaster";
		
		try{
			sc.removeSupply(name, amount);
		}catch(InvalidInputException e)
		{
			error = e.getMessage();
		}
		
		//check error
		assertEquals("The supply does not contain any " + name + "!",error);
		
		//check model in memory
		assertEquals(1,ftm.getEquipment().size());
	}
	
}

