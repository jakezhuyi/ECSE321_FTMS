package ca.mcgill.ecse321.foodtruckmanagement;


	import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import ca.mcgill.ecse321.foodtruckmanagement.controller.TestEmployeeController;
import ca.mcgill.ecse321.foodtruckmanagement.controller.TestEquipmentSupplyController;
import ca.mcgill.ecse321.foodtruckmanagement.controller.TestFoodSupplyController;
import ca.mcgill.ecse321.foodtruckmanagement.controller.TestMenuItemController;


	@RunWith(Suite.class)
	@SuiteClasses({TestEmployeeController.class, TestEquipmentSupplyController.class, TestFoodSupplyController.class, TestMenuItemController.class})
	public class AllTests {
		
	}

