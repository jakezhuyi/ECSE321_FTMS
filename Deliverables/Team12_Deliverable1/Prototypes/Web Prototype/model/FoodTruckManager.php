<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-edef018 modeling language!*/

class FoodTruckManager
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static $theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FoodTruckManager Associations
  private $employees;
  private $equipment;
  private $foodSupplies;
  private $transactionOrders;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private function __construct()
  {
    $this->employees = array();
    $this->equipment = array();
    $this->foodSupplies = array();
    $this->transactionOrders = array();
  }

  public static function getInstance()
  {
    if(self::$theInstance == null)
    {
      self::$theInstance = new FoodTruckManager();
    }
    return self::$theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getEmployee_index($index)
  {
    $aEmployee = $this->employees[$index];
    return $aEmployee;
  }

  public function getEmployees()
  {
    $newEmployees = $this->employees;
    return $newEmployees;
  }

  public function numberOfEmployees()
  {
    $number = count($this->employees);
    return $number;
  }

  public function hasEmployees()
  {
    $has = $this->numberOfEmployees() > 0;
    return $has;
  }

  public function indexOfEmployee($aEmployee)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->employees as $employee)
    {
      if ($employee->equals($aEmployee))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getEquipment_index($index)
  {
    $aEquipment = $this->equipment[$index];
    return $aEquipment;
  }

  public function getEquipment()
  {
    $newEquipment = $this->equipment;
    return $newEquipment;
  }

  public function numberOfEquipment()
  {
    $number = count($this->equipment);
    return $number;
  }

  public function hasEquipment()
  {
    $has = $this->numberOfEquipment() > 0;
    return $has;
  }

  public function indexOfEquipment($aEquipment)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->equipment as $equipment)
    {
      if ($equipment->equals($aEquipment))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getFoodSupply_index($index)
  {
    $aFoodSupply = $this->foodSupplies[$index];
    return $aFoodSupply;
  }

  public function getFoodSupplies()
  {
    $newFoodSupplies = $this->foodSupplies;
    return $newFoodSupplies;
  }

  public function numberOfFoodSupplies()
  {
    $number = count($this->foodSupplies);
    return $number;
  }

  public function hasFoodSupplies()
  {
    $has = $this->numberOfFoodSupplies() > 0;
    return $has;
  }

  public function indexOfFoodSupply($aFoodSupply)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->foodSupplies as $foodSupply)
    {
      if ($foodSupply->equals($aFoodSupply))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public function getTransactionOrder_index($index)
  {
    $aTransactionOrder = $this->transactionOrders[$index];
    return $aTransactionOrder;
  }

  public function getTransactionOrders()
  {
    $newTransactionOrders = $this->transactionOrders;
    return $newTransactionOrders;
  }

  public function numberOfTransactionOrders()
  {
    $number = count($this->transactionOrders);
    return $number;
  }

  public function hasTransactionOrders()
  {
    $has = $this->numberOfTransactionOrders() > 0;
    return $has;
  }

  public function indexOfTransactionOrder($aTransactionOrder)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->transactionOrders as $transactionOrder)
    {
      if ($transactionOrder->equals($aTransactionOrder))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfEmployees()
  {
    return 0;
  }

  public function addEmployee($aEmployee)
  {
    $wasAdded = false;
    if ($this->indexOfEmployee($aEmployee) !== -1) { return false; }
    $this->employees[] = $aEmployee;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeEmployee($aEmployee)
  {
    $wasRemoved = false;
    if ($this->indexOfEmployee($aEmployee) != -1)
    {
      unset($this->employees[$this->indexOfEmployee($aEmployee)]);
      $this->employees = array_values($this->employees);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addEmployeeAt($aEmployee, $index)
  {  
    $wasAdded = false;
    if($this->addEmployee($aEmployee))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEmployees()) { $index = $this->numberOfEmployees() - 1; }
      array_splice($this->employees, $this->indexOfEmployee($aEmployee), 1);
      array_splice($this->employees, $index, 0, array($aEmployee));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveEmployeeAt($aEmployee, $index)
  {
    $wasAdded = false;
    if($this->indexOfEmployee($aEmployee) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEmployees()) { $index = $this->numberOfEmployees() - 1; }
      array_splice($this->employees, $this->indexOfEmployee($aEmployee), 1);
      array_splice($this->employees, $index, 0, array($aEmployee));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addEmployeeAt($aEmployee, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfEquipment()
  {
    return 0;
  }

  public function addEquipment($aEquipment)
  {
    $wasAdded = false;
    if ($this->indexOfEquipment($aEquipment) !== -1) { return false; }
    $this->equipment[] = $aEquipment;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeEquipment($aEquipment)
  {
    $wasRemoved = false;
    if ($this->indexOfEquipment($aEquipment) != -1)
    {
      unset($this->equipment[$this->indexOfEquipment($aEquipment)]);
      $this->equipment = array_values($this->equipment);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addEquipmentAt($aEquipment, $index)
  {  
    $wasAdded = false;
    if($this->addEquipment($aEquipment))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEquipment()) { $index = $this->numberOfEquipment() - 1; }
      array_splice($this->equipment, $this->indexOfEquipment($aEquipment), 1);
      array_splice($this->equipment, $index, 0, array($aEquipment));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveEquipmentAt($aEquipment, $index)
  {
    $wasAdded = false;
    if($this->indexOfEquipment($aEquipment) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfEquipment()) { $index = $this->numberOfEquipment() - 1; }
      array_splice($this->equipment, $this->indexOfEquipment($aEquipment), 1);
      array_splice($this->equipment, $index, 0, array($aEquipment));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addEquipmentAt($aEquipment, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfFoodSupplies()
  {
    return 0;
  }

  public function addFoodSupply($aFoodSupply)
  {
    $wasAdded = false;
    if ($this->indexOfFoodSupply($aFoodSupply) !== -1) { return false; }
    $this->foodSupplies[] = $aFoodSupply;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeFoodSupply($aFoodSupply)
  {
    $wasRemoved = false;
    if ($this->indexOfFoodSupply($aFoodSupply) != -1)
    {
      unset($this->foodSupplies[$this->indexOfFoodSupply($aFoodSupply)]);
      $this->foodSupplies = array_values($this->foodSupplies);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addFoodSupplyAt($aFoodSupply, $index)
  {  
    $wasAdded = false;
    if($this->addFoodSupply($aFoodSupply))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfFoodSupplies()) { $index = $this->numberOfFoodSupplies() - 1; }
      array_splice($this->foodSupplies, $this->indexOfFoodSupply($aFoodSupply), 1);
      array_splice($this->foodSupplies, $index, 0, array($aFoodSupply));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveFoodSupplyAt($aFoodSupply, $index)
  {
    $wasAdded = false;
    if($this->indexOfFoodSupply($aFoodSupply) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfFoodSupplies()) { $index = $this->numberOfFoodSupplies() - 1; }
      array_splice($this->foodSupplies, $this->indexOfFoodSupply($aFoodSupply), 1);
      array_splice($this->foodSupplies, $index, 0, array($aFoodSupply));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addFoodSupplyAt($aFoodSupply, $index);
    }
    return $wasAdded;
  }

  public static function minimumNumberOfTransactionOrders()
  {
    return 0;
  }

  public function addTransactionOrder($aTransactionOrder)
  {
    $wasAdded = false;
    if ($this->indexOfTransactionOrder($aTransactionOrder) !== -1) { return false; }
    $this->transactionOrders[] = $aTransactionOrder;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeTransactionOrder($aTransactionOrder)
  {
    $wasRemoved = false;
    if ($this->indexOfTransactionOrder($aTransactionOrder) != -1)
    {
      unset($this->transactionOrders[$this->indexOfTransactionOrder($aTransactionOrder)]);
      $this->transactionOrders = array_values($this->transactionOrders);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addTransactionOrderAt($aTransactionOrder, $index)
  {  
    $wasAdded = false;
    if($this->addTransactionOrder($aTransactionOrder))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfTransactionOrders()) { $index = $this->numberOfTransactionOrders() - 1; }
      array_splice($this->transactionOrders, $this->indexOfTransactionOrder($aTransactionOrder), 1);
      array_splice($this->transactionOrders, $index, 0, array($aTransactionOrder));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveTransactionOrderAt($aTransactionOrder, $index)
  {
    $wasAdded = false;
    if($this->indexOfTransactionOrder($aTransactionOrder) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfTransactionOrders()) { $index = $this->numberOfTransactionOrders() - 1; }
      array_splice($this->transactionOrders, $this->indexOfTransactionOrder($aTransactionOrder), 1);
      array_splice($this->transactionOrders, $index, 0, array($aTransactionOrder));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addTransactionOrderAt($aTransactionOrder, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->employees = array();
    $this->equipment = array();
    $this->foodSupplies = array();
    $this->transactionOrders = array();
  }

}
?>