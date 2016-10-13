<?php
require_once (__DIR__.'/InputValidator.php');

require_once (__DIR__.'/../persistence/PersistenceFoodTruckManager.php');
require_once (__DIR__.'/../model/FoodSupply.php');

class Controller
{
	public function __construct()
	{
	
	}
	
	public function createFoodSupply($food_name, $food_num)
	{
		//$matched = FALSE;
		
		// 1. Validate Input
		$name = InputValidator::validate_input($food_name);
		if($name == null || strlen($name) == 0)
		{
			throw new Exception("Food Supply name cannot be empty!");
		}
		
		else if($food_num <= 0)
		{
			throw new Exception("Food Supply cannot be less than or equal to zero!");
		}
		
		else
		{
			// 2. Load all of the data
			$pm = new PersistenceFoodTruckManager();
			$ftm = $pm->loadDataFromStore();
				
			// 3. add the food item to the list
			$plusOneFood = new FoodSupply($food_name, $food_num);
			$ftm->addFoodSupply($plusOneFood);
				
			// 4. Write all of the data
			$pm->writeDataToStore($ftm);	
		}
	}
}
?>