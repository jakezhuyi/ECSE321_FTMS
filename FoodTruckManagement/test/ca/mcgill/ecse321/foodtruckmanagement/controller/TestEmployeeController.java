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
		c.add(Calendar.HOUR_OF_DAY, 1);
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
	
	@Test
	public void testAssignScheduleNull() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
		
		Employee employee = null; 
		Date date = null;
		Time startTime = null;
		Time endTime = null;
		
		String error = null;
		EmployeeController ec = new EmployeeController();
		try {
			ec.assignSchedule(employee, date, startTime, endTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("Employee needs to be selected for assigning a schedule! Work date cannot be empty! Shift start time cannot be empty! Shift end time cannot be empty! ", error);
		assertEquals(0, ftm.getEmployees().size());
	}
	
	@Test
	public void testAssignScheduleEmployeeDoesNotExist() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
		
		String nameE = "Jeff"; String roleE = "Chef";
		Employee employee = new Employee(nameE, roleE);
		
		Calendar c = Calendar.getInstance();
		
		//Make the schedule date the day after today
		c.add(Calendar.DATE, 1);
		Date scheduleDate = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		
		//Make the end time 1 hour after start time
		c.add(Calendar.HOUR_OF_DAY, 1);
		Time endTime = new Time(c.getTimeInMillis());
		
		String error = null;
		EmployeeController ec = new EmployeeController();
		try {
			ec.assignSchedule(employee, scheduleDate, startTime, endTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("Employee does not exist! ", error);
		assertEquals(0, ftm.getEmployees().size());		
	}
	
	@Test
	public void testAssignScheduleEndTimeBeforeStartTime() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
		
		String nameE = "Jeff"; String roleE = "Chef";
		Employee employee = new Employee(nameE, roleE);
		ftm.addEmployee(employee);
		checkResultEmployee(nameE, roleE, ftm);
		
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, 1);
		Date schedDate = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		c.add(Calendar.HOUR_OF_DAY, -1);
		Time endTime = new Time(c.getTimeInMillis());
		
		String error = null;
		EmployeeController ec = new EmployeeController();
		try {
			ec.assignSchedule(employee, schedDate, startTime, endTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("Shift end time cannot be before shift start time! ", error);
		assertEquals(1, ftm.getEmployees().size());
		assertEquals(0, ftm.getEmployee(0).getSchedules().size());
	}
	
	@Test
	public void testAssignScheduleDateBeforeCurrentDate() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
		
		String nameE = "Jeff"; String roleE = "Chef";
		Employee employee = new Employee(nameE, roleE);
		ftm.addEmployee(employee);
		checkResultEmployee(nameE, roleE, ftm);
	
		assertEquals(0, ftm.getEmployee(0).getSchedules().size());
		
		Calendar c = Calendar.getInstance();
		
		//Make the schedule date the day before today
		c.add(Calendar.DATE, -1);
		Date scheduleDate = new Date(c.getTimeInMillis());
		Time startTime = new Time(c.getTimeInMillis());
		
		c.add(Calendar.HOUR_OF_DAY, 1);
		Time endTime = new Time(c.getTimeInMillis());
		
		String error = null;
		
		EmployeeController ec = new EmployeeController();
		try {
			ec.assignSchedule(employee, scheduleDate, startTime, endTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("Work date cannot be before today's date! ", error);
		assertEquals(1, ftm.getEmployees().size());
		assertEquals(0, ftm.getEmployee(0).getSchedules().size());
	}
	
	@Test
	public void testAssignScheduleStartTimeBeforeCurrentTime() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		assertEquals(0, ftm.getEmployees().size());
		
		String nameE = "Jeff"; String roleE = "Chef";
		Employee employee = new Employee(nameE, roleE);
		ftm.addEmployee(employee);
		checkResultEmployee(nameE, roleE, ftm);
	
		assertEquals(0, ftm.getEmployee(0).getSchedules().size());
		
		//Date is today
		Calendar c = Calendar.getInstance();
		Date scheduleDate = new Date(c.getTimeInMillis());

		//Make start time before current time
		c.add(Calendar.MINUTE, -1);
		Time startTime = new Time(c.getTimeInMillis());
		
		c.add(Calendar.HOUR_OF_DAY, 1);
		Time endTime = new Time(c.getTimeInMillis());
		
		String error = null;
		
		EmployeeController ec = new EmployeeController();
		try {
			ec.assignSchedule(employee, scheduleDate, startTime, endTime);
		} catch (InvalidInputException e) {
			error = e.getMessage();
		}
		
		assertEquals("Shift start time cannot be before current time! ", error);
		assertEquals(1, ftm.getEmployees().size());
		assertEquals(0, ftm.getEmployee(0).getSchedules().size());
	}
	
	private void checkResultEmployee (String name, String role, FoodTruckManager ftm) {
		assertEquals(1, ftm.getEmployees().size());
		assertEquals(name, ftm.getEmployee(0).getName());
		assertEquals(role, ftm.getEmployee(0).getRole());
	}
	
	private void checkResultSchedule (Date schedDate, Time startTime, Time endTime, FoodTruckManager ftm) {
		assertEquals(1, ftm.getEmployees().size());
		assertEquals(1, ftm.getEmployee(0).getSchedules().size());
		assertEquals(schedDate.toString(), ftm.getEmployee(0).getSchedule(0).getWorkDay().toString());
		assertEquals(startTime.toString(), ftm.getEmployee(0).getSchedule(0).getStartTime().toString());
		assertEquals(endTime.toString(), ftm.getEmployee(0).getSchedule(0).getEndTime().toString());
	}
}
