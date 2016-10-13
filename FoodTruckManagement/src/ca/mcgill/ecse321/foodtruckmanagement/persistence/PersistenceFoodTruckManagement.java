package ca.mcgill.ecse321.foodtruckmanagement.persistence;

import java.util.Iterator;

import ca.mcgill.ecse321.foodtruckmanagement.model.Employee;
import ca.mcgill.ecse321.foodtruckmanagement.model.Equipment;
import ca.mcgill.ecse321.foodtruckmanagement.model.FoodSupply;
import ca.mcgill.ecse321.foodtruckmanagement.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruckmanagement.model.MenuItem;
import ca.mcgill.ecse321.foodtruckmanagement.model.Schedule;
import ca.mcgill.ecse321.foodtruckmanagement.model.TransactionOrder;


public class PersistenceFoodTruckManagement {
	
	private static void initializeXStream() {

		PersistenceXStream.setFilename("foodtruckmanagement.xml");
		PersistenceXStream.setAlias("employee", Employee.class);
		PersistenceXStream.setAlias("equipment", Equipment.class);
		PersistenceXStream.setAlias("supply", FoodSupply.class);
		PersistenceXStream.setAlias("item", MenuItem.class);
		PersistenceXStream.setAlias("schedule", Schedule.class);
		PersistenceXStream.setAlias("order", TransactionOrder.class);
		PersistenceXStream.setAlias("manager", FoodTruckManager.class);
		
	}
	
	public static void loadFoodTruckManagementModel() {
		FoodTruckManager ftm = FoodTruckManager.getInstance();
		initializeXStream();
		
		FoodTruckManager ftm2 = (FoodTruckManager) PersistenceXStream.loadFromXMLwithXStream();
		if (ftm2 != null) {
			
			//Add saved employees to the ftm
			Iterator<Employee> eIt = ftm2.getEmployees().iterator();
			while (eIt.hasNext())
				ftm.addEmployee(eIt.next());
			
			//Add saved equipment to the ftm
			Iterator<Equipment> eqIt = ftm2.getEquipment().iterator();
			while (eqIt.hasNext())
				ftm.addEquipment(eqIt.next());
			
			//Add saved food supplies to the ftm
			Iterator<FoodSupply> fIt = ftm2.getFoodSupplies().iterator();
			while (fIt.hasNext())
				ftm.addFoodSupply(fIt.next());
			
			//Add saved orders to the ftm
			Iterator<TransactionOrder> tIt = ftm2.getTransactionOrders().iterator();
			while (tIt.hasNext())
				ftm.addTransactionOrder(tIt.next());
			
			//Add saved menu items to the ftm
			Iterator<MenuItem> mIt = ftm2.getMenuItems().iterator();
			while (mIt.hasNext())
				ftm.addMenuItem(mIt.next());
			
		}
		
	}
	
	

}
