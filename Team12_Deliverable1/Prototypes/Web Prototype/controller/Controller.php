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
			$matched = FALSE;
			
			// 2. Load all of the data
			$pm = new PersistenceFoodTruckManager();
			$ftm = $pm->loadDataFromStore();
				
			// 3. check if the food item exists
			// if it does change the amount
			// if it does not, set the amount
			foreach ( $ftm->getFoodSupplies () as $food )
			{
				if (strcmp ( $food->getName(), $food_name ) == 0)
				{
					$matched = TRUE;
					$old_amount = $food->getAmount();
					$food_num += $old_amount;
					$food->setAmount($food_num);
					break;
				}
			}
			
			if(!$matched)
			{
				$new_food = new FoodSupply($food_name, $food_num);
				$ftm->addFoodSupply($new_food);
			}
			
				
			// 4. Write all of the data
			$pm->writeDataToStore($ftm);	
		}
	}
}
?>