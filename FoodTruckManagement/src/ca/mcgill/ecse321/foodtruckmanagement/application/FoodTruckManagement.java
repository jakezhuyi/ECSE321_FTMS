package ca.mcgill.ecse321.foodtruckmanagement.application;

import ca.mcgill.ecse321.foodtruckmanagement.persistence.PersistenceFoodTruckManagement;
import ca.mcgill.ecse321.foodtruckmanagement.view.FoodTruckManagementPage;

public class FoodTruckManagement {
	
	public static void main (String[] args) {
		//Load Model
		PersistenceFoodTruckManagement.loadFoodTruckManagementModel();
		
		//Start UI
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new FoodTruckManagementPage().setVisible(true);
			}
		});
		
	}

}
