<?php
require_once (__DIR__.'/../persistence/PersistenceFoodTruckManager.php');
require_once (__DIR__.'/../model/FoodTruckManager.php');
require_once (__DIR__.'/../model/Employee.php');

class PersistenceFoodTruckManagerTest extends PHPUnit_Framework_TestCase
{
	protected $pm;
	
	protected function setUp()
	{
		$this->pm = new PersistenceFoodTruckManager();
	}
	
	protected function tearDown()
	{}
	
	public function testPersistence()
	{
		// 1. Create test data
		$ftm = FoodTruckManager::getInstance();
		$employee = new Employee("Frank", "Cook");
		$ftm->addEmployee($employee);
		
		// 2. Write to the data
		$this->pm->writeDataToStore($ftm);
		
		// 3. Clear data from memory
		$ftm->delete();
		
		$this->assertEquals(0, count($ftm->getEmployees()));
		
		// 4. Load it back in
		$ftm = $this->pm->loadDataFromStore();
		
		// 5. Check that we got it back
		$this->assertEquals(1, count($ftm->getEmployees()));
		$myEmployee = $ftm->getEmployee_index(0);
		$this->assertEquals("Frank", $myEmployee->getName());
	}
}
?>