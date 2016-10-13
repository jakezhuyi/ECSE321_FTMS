<?php
require_once (__DIR__.'/InputValidator.php');
require_once (__DIR__.'/../model/FoodSupply.php');

class Controller
{
	public function __construct()
	{
	
	}
	
	public function createFoodSupply($food_name)
	{
		// 1. Validate Input
		$name = InputValidator::validate_input($participant_name);
		if($name == null || strlen($name) == 0)
		{
			throw new Exception("Food Supply name cannot be empty!");
		}
		else
		{
			// 2. Load all of the data
			$pm = new PersistenceFoodTruckManager();
			$ftm = $pm->loadDataFromStore();
				
			// 3. check if the food item exists
			// if it does add one to its amount.
			// if it does not, set the amount to 1
			foreach ( $ftm->getFoodSupply () as $food )
			{
				if (strcmp ( $food->getName(), $food_name ) == 0)
				{
					$num_plus_one = $food->getAmount()++;
					$matched = TRUE;
					break;
				}
			}
			
			if(!$matched) $num_plus_one = 1;
			
			$plusOneFood = new FoodSupply($food_name, $num_plus_one);
			$ftm->addFoodSupply($plusOneFood);
				
			// 4. Write all of the data
			$pm->writeDataToStore($ftm);	
		}
	}
}
?>