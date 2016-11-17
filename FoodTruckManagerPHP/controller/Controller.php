<?php
require_once (__DIR__.'/InputValidator.php');

require_once (__DIR__.'/../persistence/PersistenceFoodTruckManager.php');
require_once (__DIR__.'/../model/FoodSupply.php');
require_once (__DIR__.'/../model/Equipment.php');
require_once (__DIR__.'/../model/Employee.php');
require_once (__DIR__.'/../model/Schedule.php');

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
					$equipment->setSupply($equipment_num);
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
	
	public function fireEmployee($em_name)
	{
		$pm = new PersistenceFoodTruckManager();
		$ftm = $pm->loadDataFromStore();
		
		$error = "";
		$employees = $ftm->getEmployees();
		
		if($em_name == null)
		{ 
			throw new Exception("Employee needs to be selected for firing.");
		}

		foreach ( $ftm->getEmployees () as $employee )
		{
			if (strcmp ( $employee->getName(), $em_name ) == 0)
			{
				$ftm->removeEmployee($employee);
				$pm->writeDataToStore($ftm);
				return;
			}
		}
		
		throw new Exception("Employee does not exist.");
	}
	
	public function setSchedule($employee_name, $event_date, $starttime, $endtime)
	{
		$name = InputValidator::validate_input($employee_name);
		$date = InputValidator::validate_input($event_date);
		$stime = InputValidator::validate_input($starttime);
		$etime = InputValidator::validate_input($endtime);
	
		//echo $name;
		$error = null;
		if($name == null || strlen($name) == 0) $error .= "@Employee name cannot be empty! ";
		if($date == null || strlen($date) == 0 || !strtotime($date)) $error .= "@2Date must be specified correctly (YYYY-MM-DD)! ";
		if($stime == null || strlen($stime) == 0 || !strtotime($stime)) $error .= "@3Start time must be specified correctly (HH:MM)! ";
		if($etime == null || strlen($etime) == 0 || !strtotime($etime)) $error .= "@4End time must be specified correctly (HH:MM)! ";
		if($etime != null && $stime != null && strlen($stime) !=0 && strlen($etime) !=0  && strtotime($etime) < strtotime($stime))
			$error .= "@4Event end time cannot be before event start time!";
	
		//$error = trim($error);
		if(!empty($error)) throw new Exception(trim($error));
	
		// Load all of the data
		$pm = new PersistenceFoodTruckManager();
		$ftm = $pm->loadDataFromStore();
		
		foreach ( $ftm->getEmployees () as $employee)
		{
			if(strcmp($employee->getName(), $employee_name) == 0)
			{
				foreach($employee->getSchedules() as $schedule)
				{
					if(strcmp($schedule->getWorkDay(), $event_date) == 0)
					{
						$schedule->setStartTime(date('H:i', strtotime($stime)));
						$schedule->setEndTime(date('H:i', strtotime($etime)));
						$pm->writeDataToStore($ftm);
						return;
					}
				}
				// set schedule
				$event = new Schedule(date('Y-m-d', strtotime($date)), date('H:i', strtotime($stime)), date('H:i', strtotime($etime)));
				$employee->addSchedule($event);
			}
		}
	
	
		// Write all of the data
		$pm->writeDataToStore($ftm);
	
	}
}
?>