<?php
require_once (__DIR__.'/InputValidator.php');

require_once (__DIR__.'/../persistence/PersistenceFoodTruckManager.php');
require_once (__DIR__.'/../model/FoodSupply.php');
require_once (__DIR__.'/../model/Equipment.php');
require_once (__DIR__.'/../model/Employee.php');

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
			foreach ( $ftm->getEquipment () as $food )
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
	
	public function removeFoodSupply($food_name, $food_num)
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
			// if it does not, put an error
			foreach ( $ftm->getFoodSupplies () as $food )
			{
				if (strcmp ( $food->getName(), $food_name ) == 0)
				{
					$matched = TRUE;
					$old_amount = $food->getAmount();
					$new_amount = $old_amount - $food_num;
					if($new_amount < 0)
					{
						throw new Exception("Cannot remove more than you currently have!");
					}
					$food->setAmount($new_amount);
					break;
				}
			}
				
			if(!$matched)
			{
				throw new Exception("Food Supply does not exist!");
			}
				
	
			// 4. Write all of the data
			$pm->writeDataToStore($ftm);
		}
	}
	public function addEquipment($equipment_name, $equipment_num)
	{
	
		// 1. Validate Input
		$ename = InputValidator::validate_input($equipment_name);
		if($ename == null || strlen($ename) == 0)
		{
			throw new Exception("Equipment name cannot be empty!");
		}
	
		else if($equipment_num <= 0)
		{
			throw new Exception("Equipment cannot be less than or equal to zero!");
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
			foreach ( $ftm->getEquipment () as $equipment )
			{
				if (strcmp ( $equipment->getName(), $equipment_name ) == 0)
				{
					$matched = TRUE;
					$old_amount = $equipment->getSupply();
					$equipment_num += $old_amount;
					$food->setAmount($equipment_num);
					break;
				}
			}
				
			if(!$matched)
			{
				$new_equipment = new Equipment($equipment_name, $equipment_num);
				$ftm->addEquipment($new_equipment);
			}
				
	
			// 4. Write all of the data
			$pm->writeDataToStore($ftm);
		}
	}
	
	public function removeEquipment($equipment_name, $equipment_num)
	{
	
		// 1. Validate Input
		$name = InputValidator::validate_input($equipment_name);
		if($name == null || strlen($name) == 0)
		{
			throw new Exception("Equipment name cannot be empty!");
		}
	
		else if($equipment_num <= 0)
		{
			throw new Exception("Equipment cannot be less than or equal to zero!");
		}
	
		else
		{
			$matched = FALSE;
	
			// 2. Load all of the data
			$pm = new PersistenceFoodTruckManager();
			$ftm = $pm->loadDataFromStore();
	
			// 3. check if the food item exists
			// if it does change the amount
			// if it does not, put an error
			foreach ( $ftm->getEquipment () as $equipment )
			{
				if (strcmp ( $equipment->getName(), $equipment_name ) == 0)
				{
					$matched = TRUE;
					$old_amount = $equipment->getSupply();
					$new_amount = $old_amount - $equipment_num;
					if($new_amount < 0)
					{
						throw new Exception("Cannot remove more than you currently have!");
					}
					$equipment->setSupply($new_amount);
					break;
				}
			}
	
			if(!$matched)
			{
				throw new Exception("Equipment does not exist!");
			}
	
	
			// 4. Write all of the data
			$pm->writeDataToStore($ftm);
		}
	}
	public function createEmployee($em_name, $em_role)
	{
		$pm = new PersistenceFoodTruckManager();
		$ftm = $pm->loadDataFromStore();
		
		$name = InputValidator::validate_input($em_name);
		$role = InputValidator::validate_input($em_role);
		
		if($name == null || strlen($name) == 0 || $role == null || strlen($role) == 0)
		{
			throw new Exception("Name/Role cannot be empty!");
		}
		
		$new_employee = new Employee($em_name, $em_role);
		$ftm->addEmployee($new_employee);
		$pm->writeDataToStore($ftm);
		
	}
}
?>