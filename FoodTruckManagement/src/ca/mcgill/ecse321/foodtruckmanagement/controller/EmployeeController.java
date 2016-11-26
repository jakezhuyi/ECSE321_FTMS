package ca.mcgill.ecse321.foodtruckmanagement.controller;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;

import ca.mcgill.ecse321.foodtruckmanagement.model.Employee;
import ca.mcgill.ecse321.foodtruckmanagement.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruckmanagement.model.Schedule;
import ca.mcgill.ecse321.foodtruckmanagement.persistence.PersistenceXStream;

public class EmployeeController {

	public EmployeeController()
	{
		
	}
	
	public void addEmployee(String name, String role) throws InvalidInputException
	{
		String error = "";
		boolean isError = false;
		
		
		if (name == null || name.length() == 0)
		{
			error = "Employee name cannot be empty! ";
			isError = true;
		}
		
		if (role == null || role.length() == 0) 
		{
			error = error + "Employee role cannot be empty! ";
			isError = true;
		}
		
		if (isError)
		{
			throw new InvalidInputException(error);
		}
		
		name = name.trim();
		
		name = name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
		role = role.substring(0, 1).toUpperCase() + role.substring(1).toLowerCase();
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		Employee e = new Employee(name, role);
		fm.addEmployee(e);
		PersistenceXStream.saveToXMLwithXStream(fm);
		return;
	}
	
	public void removeEmployee (Employee e) throws InvalidInputException {
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		String error = "";
		
		if (e == null)
			error = "Employee needs to be selected for firing! ";
		else if (!fm.getEmployees().contains(e))
			error = error + "Employee does not exist! ";
		
		if (error.length() != 0)
			throw new InvalidInputException(error);
		
		fm.removeEmployee(e);
		PersistenceXStream.saveToXMLwithXStream(fm);
	}
	
	public void assignSchedule(Employee e, Date date, Time startTime, Time endTime) throws InvalidInputException
	{
		FoodTruckManager fm = FoodTruckManager.getInstance();
		
		String error = "";
		
		if (e == null)
			error = "Employee needs to be selected for assigning a schedule! ";
		else if (!fm.getEmployees().contains(e))
			error = error + "Employee does not exist! ";
		if (date == null)
			error = error + "Work date cannot be empty! ";
		if (startTime == null)
			error = error + "Shift start time cannot be empty! ";
		if (endTime == null)
			error = error + "Shift end time cannot be empty! ";
		if (endTime != null && startTime != null && endTime.getTime() < startTime.getTime())
			error = error + "Shift end time cannot be before shift start time! ";
		if(error.length() > 0)
			throw new InvalidInputException(error);
		
		//Loop through all of the employees
		for (int i=0; i<fm.numberOfEmployees(); i++)
		{
			//Check to see when the employee names match
			if (e.getName().toUpperCase().equals(fm.getEmployee(i).getName().toUpperCase()))
			{				
				for(int j=0; j<fm.getEmployee(i).numberOfSchedules(); j++)
				{
					//Check to see if the dates are the same
					boolean sameDate = fm.getEmployee(i).getSchedule(j).getWorkDay().toString().equals(date.toString());
					
					//Check if the employee already has a schedule for this date, if so update the schedule
					if (sameDate)
					{
						fm.getEmployee(i).getSchedule(j).setStartTime(startTime);
						fm.getEmployee(i).getSchedule(j).setEndTime(endTime);
						PersistenceXStream.saveToXMLwithXStream(fm);
						return;
					}
				}
				
				//If the employee doesn't have a schedule for that date, assign it to him
				Schedule s = new Schedule(date, startTime, endTime);
				fm.getEmployee(i).addSchedule(s);
				PersistenceXStream.saveToXMLwithXStream(fm);
				return;
			}
		}
	}
	
	public String viewSchedule (Employee e) throws InvalidInputException
	{
		FoodTruckManager fm = FoodTruckManager.getInstance();
		
		String error = "";
		
		if (e == null)
			error = "Employee needs to be selected for viewing a schedule! ";
		else if (!fm.getEmployees().contains(e))
			error = error + "Employee does not exist! ";
		
		if (error.length() != 0)
			throw new InvalidInputException(error);
		
		
		//Set the calendar to the Monday of the current week
		Calendar c = Calendar.getInstance();		
		c.setFirstDayOfWeek(Calendar.MONDAY);
		c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
		
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DAY_OF_MONTH);
		int year = c.get(Calendar.YEAR);

		//Add this weeks schedule
		String scheduleList = calculateSched(c, e, day, month, year);
		
		//Add next weeks schedule
		month = c.get(Calendar.MONTH);
		day = c.get(Calendar.DAY_OF_MONTH);
		year = c.get(Calendar.YEAR);
		scheduleList = scheduleList + calculateSched(c, e, day, month, year);
		
		scheduleList = scheduleList + "</html>";
		
		return scheduleList;
		
	}
	
	public String viewEmployees()
	{
		String employeeList = "<html><h3 style=\"text-align:center;\">Employee List</h3>";
		employeeList = employeeList + "<table><tr><td><b><u>Name</b></u></td><td></td><td></td><td><b><u>Role</b></u></td></tr>";
		
		FoodTruckManager fm = FoodTruckManager.getInstance();
		
		for (int i=0; i<fm.numberOfEmployees(); i++)
		{
			employeeList = employeeList + "<tr><td>" + fm.getEmployee(i).getName() + "</td><td></td><td></td>" + "<td>" + fm.getEmployee(i).getRole() + "</td></tr>";
		}
		
		employeeList = employeeList + "</table></html>";
		return employeeList;
	}
	
	
	private static String getMonth(int month){
	    String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
	    return monthNames[month];
	}
	
	private static String calculateSched(Calendar c, Employee e, int day, int month, int year) {
		String scheduleList = "<html><h3 style=\"text-align:center;\">" + e.getName() + "'s Schedule for Week of " + getMonth(month) + " " + day + ", " + year + "</h3></title>";
		
		scheduleList = scheduleList + "<table><tr><th><u>Monday</u></th><th><u>Tuesday</u></th><th><u>Wednesday</u></th><th><u>Thursday</u></th>"
									+ "<th><u>Friday</u></th><th><u>Saturday</u></th><th><u>Sunday</u></th></tr>";
		
		scheduleList = scheduleList + "<tr>";
		
		//Loop through all the days of the week and check the employee's schedule
		for (int i=0; i<7; i++)
		{
			Date date = new Date(c.getTimeInMillis());
			scheduleList = scheduleList + "<td>";
			
			if (e.numberOfSchedules() == 0)
				scheduleList = scheduleList + "<i>Not Scheduled</i>";

			for (int j=0; j<e.numberOfSchedules(); j++)
			{				
				Schedule employeeSchedule = e.getSchedule(j);
				
				if(employeeSchedule.getWorkDay().toString().equals(date.toString()))
				{
					scheduleList = scheduleList + employeeSchedule.getStartTime().toString().substring(0,5) + " - " + 
									employeeSchedule.getEndTime().toString().substring(0, 5);
					break;
				}
				if (j==e.numberOfSchedules()-1)
					scheduleList = scheduleList + "<i>Not Scheduled</i>";
			}
			scheduleList = scheduleList + "</td>";
			c.add(Calendar.DAY_OF_WEEK, 1);
		}
		
		scheduleList = scheduleList + "</tr></table><br/><br/>";
		
		return scheduleList;
	}
	
}
