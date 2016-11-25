<?php
require_once (__DIR__.'/../controller/Controller.php');
require_once (__DIR__.'/../persistence/PersistenceFoodTruckManager.php');
require_once (__DIR__.'/../model/FoodTruckManager.php');
require_once (__DIR__.'/../model/Employee.php');
require_once (__DIR__.'/../model/Equipment.php');
require_once (__DIR__.'/../model/FoodSupply.php');
require_once (__DIR__.'/../model/MenuItem.php');
require_once (__DIR__.'/../model/Schedule.php');
require_once (__DIR__.'/../model/TransactionOrder.php');

class ControllerTest extends PHPUnit_Framework_TestCase
{
	protected $c;
	protected $pm;
	protected $rm;

	protected function setUp()
	{
		$this->c = new Controller();
		$this->pm = new PersistenceFoodTruckManager();
		$this->rm = $this->pm->loadDataFromStore();
		$this->rm->delete();
		$this->pm->writeDataToStore($this->rm);
	}
	
	protected function tearDown()
	{}
	
	public function testFoodSupply()
	{
		$this->assertEquals(0, count($this->rm->getFoodSupplies()));
		
		$name = "Tomato";
		$amount = 3;
		
		try 
		{
			$this->c->createFoodSupply($name,$amount);
		} 
		
		catch (Exception $e) 
		{
			// check that no error occurred
			$this->fail();
		}
		
		// check file contents
		$this->rm = $this->pm->loadDataFromStore();
		$this->assertEquals(1, count($this->rm->getFoodSupplies()));
		$this->assertEquals($name, $this->rm->getFoodSupply_index(0)->getName());
		$this->assertEquals($amount, $this->rm->getFoodSupply_index(0)->getAmount());
		
		$name = "Tomato";
		$amount = 2;
		
		try
		{
			$this->c->removeFoodSupply($name,$amount);
		}
		
		catch (Exception $e)
		{
			// check that no error occurred
			$this->fail();
		}
		
		// check file contents
		$this->rm = $this->pm->loadDataFromStore();
		$this->assertEquals(1, count($this->rm->getFoodSupplies()));
		$this->assertEquals($name, $this->rm->getFoodSupply_index(0)->getName());
		$this->assertEquals(1, $this->rm->getFoodSupply_index(0)->getAmount());
		
	}
	
	public function testCreateFoodSupplyZero()
	{
		$this->assertEquals(0, count($this->rm->getFoodSupplies()));
	
		$name = "Tomato";
		$amount = 0;
	
		try
		{
			$this->c->createFoodSupply($name,$amount);
		}
	
		catch (Exception $e)
		{
			// check that no error occurred
			$error = $e->getMessage();
		}
	
		// check file contents
		$this->rm = $this->pm->loadDataFromStore();
		$this->assertEquals("Food Supply cannot be less than or equal to zero!", $error);	
	}
	
	public function testEmployee()
	{
		$this->assertEquals(0, count($this->rm->getEmployees()));

		$name = "Frank";
		$role = "Cook";
		
		try
		{
			$this->c->createEmployee($name,$role);
		}
		catch (Exception $e)
		{
			// check that no error occurred
			$this->fail();
		}
		
		$this->rm = $this->pm->loadDataFromStore();
		$this->assertEquals($name, $this->rm->getEmployee_index(0)->getName());
		$this->assertEquals($role, $this->rm->getEmployee_index(0)->getRole());
		
		try
		{
			$this->c->fireEmployee($name);
		}
		catch (Exception $e)
		{
			// check that no error occurred
			$this->fail();
		}
		
		$this->rm = $this->pm->loadDataFromStore();
		$this->assertEquals(0, $this->rm->numberOfEmployees());
	}
	
	public function testNoNameEmployee()
	{
		$this->assertEquals(0, count($this->rm->getEmployees()));
	
		$name = "";
		$role = "Cook";
	
		try
		{
			$this->c->createEmployee($name,$role);
		}
	
		catch (Exception $e)
		{
			// check that no error occurred
			$error = $e->getMessage();
		}
	
		// check file contents
		$this->rm = $this->pm->loadDataFromStore();
		$this->assertEquals("Name/Role cannot be empty!", $error);
	}
	
	public function testEquipment()
	{
		$this->assertEquals(0, count($this->rm->getEquipment()));
	
		$name = "Stove";
		$amount = 3;
	
		try
		{
			$this->c->addEquipment($name,$amount);
		}
	
		catch (Exception $e)
		{
			// check that no error occurred
			$this->fail();
		}
	
		// check file contents
		$this->rm = $this->pm->loadDataFromStore();
		$this->assertEquals(1, count($this->rm->getEquipment()));
		$this->assertEquals($name, $this->rm->getEquipment_index(0)->getName());
		$this->assertEquals($amount, $this->rm->getEquipment_index(0)->getSupply());
	
		$name = "Stove";
		$amount = 2;
	
		try
		{
			$this->c->removeEquipment($name,$amount);
		}
	
		catch (Exception $e)
		{
			// check that no error occurred
			$this->fail();
		}
	
		// check file contents
		$this->rm = $this->pm->loadDataFromStore();
		$this->assertEquals(1, count($this->rm->getEquipment()));
		$this->assertEquals($name, $this->rm->getEquipment_index(0)->getName());
		$this->assertEquals(1, $this->rm->getEquipment_index(0)->getSupply());
	
	}
	
	public function testCreateEquipmentNegative()
	{
		$this->assertEquals(0, count($this->rm->getFoodSupplies()));
	
		$name = "Tomato";
		$amount = -1;
	
		try
		{
			$this->c->addEquipment($name,$amount);
		}
	
		catch (Exception $e)
		{
			// check that no error occurred
			$error = $e->getMessage();
		}
	
		// check file contents
		$this->rm = $this->pm->loadDataFromStore();
		$this->assertEquals("Equipment cannot be less than or equal to zero!", $error);
	}
	
	public function testMenuItem()
	{
		$this->assertEquals(0, count($this->rm->getMenuItems()));
	
		$name = "Burger";
	
		try
		{
			$this->c->addMenuItem($name);
		}
	
		catch (Exception $e)
		{
			// check that no error occurred
			$this->fail();
		}
	
		// check file contents
		$this->rm = $this->pm->loadDataFromStore();
		$this->assertEquals(1, count($this->rm->getMenuItems()));
		$this->assertEquals($name, $this->rm->getMenuItem_index(0)->getName());
	
	
	}
	
	public function testMenuItemNull()
	{
		$this->assertEquals(0, count($this->rm->getMenuItems()));
	
		$name = "";
	
		try
		{
			$this->c->addMenuItem($name);
		}
	
		catch (Exception $e)
		{
			$error = $e->getMessage();
		}
	
		// check file contents
		$this->rm = $this->pm->loadDataFromStore();
		$this->assertEquals("Name cannot be empty!", $error);
	
	}
	
	public function testMenuItemSpaces()
	{
		$this->assertEquals(0, count($this->rm->getMenuItems()));
	
		$name = " ";
	
		try
		{
			$this->c->addMenuItem($name);
		}
	
		catch (Exception $e)
		{
			$error = $e->getMessage();
		}
	
		// check file contents
		$this->rm = $this->pm->loadDataFromStore();
		$this->assertEquals("Name cannot be empty!", $error);
	
	}
}
?>