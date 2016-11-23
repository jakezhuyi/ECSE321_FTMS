package ca.mcgill.ecse321.foodtruckmanagement.controller;

import static org.junit.Assert.*;

import java.io.File;
import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ca.mcgill.ecse321.foodtruckmanagement.model.Employee;
import ca.mcgill.ecse321.foodtruckmanagement.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruckmanagement.model.MenuItem;
import ca.mcgill.ecse321.foodtruckmanagement.model.Schedule;
import ca.mcgill.ecse321.foodtruckmanagement.persistence.PersistenceXStream;

public class TestEmployeeController {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		PersistenceXStream.setFilename("test"+File.separator+"ca"+File.separator+"mcgill"+File.separator+"ecse321"+File.separator+"foodtruckmanagement"+File.separator+"controller"+File.separator+"data.xml");
		PersistenceXStream.setAlias("employee", Employee.class);
		PersistenceXStream.setAlias("schedule", Schedule.class);
	}

	@After
	public void tearDown() throws Exception {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		ftm.delete();
	}

	@Test
	public void testAddEmployee() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
		
		String name = "Jeff";
		String role = "Chef";
		
		EmployeeController ec = new EmployeeController();
		try {
			ec.addEmployee(name, role);
		} catch (InvalidInputException e) {
			fail();
		}
		
		checkResultEmployee(name, role, ftm);
		
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		checkResultEmployee(name, role, ftm2);
		
	}
	
	@Test
	public void testAddEmployeeNull() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
		
		String name = null;
		String role = null;
		
		String error = null;
		EmployeeController ec = new EmployeeController();
		try {
			ec.addEmployee(name, role);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Employee name cannot be empty! Employee role cannot be empty! ", error);
		
		//check model
		assertEquals(0, ftm.getEmployees().size());
	}
	
	@Test
	public void testAddEmployeeEmpty() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
		
		String name = "";
		String role = "";
		
		String error = null;
		EmployeeController ec = new EmployeeController();
		try {
			ec.addEmployee(name, role);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		//check error
		assertEquals("Employee name cannot be empty! Employee role cannot be empty! ", error);
		
		//check model
		assertEquals(0, ftm.getEmployees().size());
	}
	
	@Test
	public void testRemoveEmployee() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
		
		String nameE = "Jeff"; String roleE = "Chef";
		Employee employee = new Employee(nameE, roleE);
		ftm.addEmployee(employee);
		checkResultEmployee(nameE, roleE, ftm);
		
		EmployeeController ec = new EmployeeController();
		try {
			ec.removeEmployee(employee);
		} catch (InvalidInputException e) {
			//check that no error occurred 
			fail();
		}
		
		assertEquals(0, ftm.getEmployees().size());
		
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		assertEquals(0, ftm2.getEmployees().size());
	}
	
	@Test
	public void testRemoveEmployeeNull() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
		
		Employee employee = null;
		String error = null;
		
		EmployeeController ec = new EmployeeController();
		try {
			ec.removeEmployee(employee);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("Employee needs to be selected for firing! ", error);
		
		assertEquals(0, ftm.getEmployees().size());
	}
	
	@Test
	public void testRemoveEmployeeDoesNotExist() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
		
		String nameE = "Jeff"; String roleE = "Chef";
		Employee employee = new Employee(nameE, roleE);
		
		String error = null;
		EmployeeController ec = new EmployeeController();
		try {
			ec.removeEmployee(employee);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("Employee does not exist! ", error);
		
		assertEquals(0, ftm.getEmployees().size());
	}
	
	@Test
	public void testAssignSchedule() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
		
		String nameE = "Jeff"; String roleE = "Chef";
		Employee employee = new Employee(nameE, roleE);
		ftm.addEmployee(employee);
		checkResultEmployee(nameE, roleE, ftm);
	
		assertEquals(0, ftm.getEmployee(0).getSchedules().size());
		
		Calendar c = Calendar.getInstance();
		
		//Make the schedule date the day after today
		c.add(Calendar.DATE, 1);
		Date scheduleDate = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		
		//Make the end time 1 hour after start time
		c.add(Calendar.HOUR, 1);
		Time endTime = new Time(c.getTimeInMillis());
		
		EmployeeController ec = new EmployeeController();
		try {
			ec.assignSchedule(employee, scheduleDate, startTime, endTime);
		} catch (InvalidInputException e) {
			fail();
		}
		
		checkResultSchedule(scheduleDate, startTime, endTime, ftm);
		
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		
		checkResultSchedule(scheduleDate, startTime, endTime, ftm2);
		
	}
	
	private void checkResultEmployee (String name, String role, FoodTruckManager ftm) {
		assertEquals(1, ftm.getEmployees().size());
		assertEquals(name, ftm.getEmployee(0).getName());
		assertEquals(role, ftm.getEmployee(0).getRole());
	}
	
	private void checkResultSchedule (Date schedDate, Time startTime, Time endTime, FoodTruckManager ftm) {
		assertEquals(1, ftm.getEmployees().size());
		assertEquals(1, ftm.getEmployee(0).getSchedules().size());
		//assertEquals(schedDate, ftm.getEmployee(0).getSchedule(0).getWorkDay());
		//assertEquals(startTime, ftm.getEmployee(0).getSchedule(0).getStartTime());
		//assertEquals(endTime, ftm.getEmployee(0).getSchedule(0).getEndTime());
	}
}
