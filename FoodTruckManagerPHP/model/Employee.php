<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-edef018 modeling language!*/

class Employee
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Employee Attributes
  private $name;
  private $role;

  //Employee Associations
  private $schedules;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aRole)
  {
    $this->name = $aName;
    $this->role = $aRole;
    $this->schedules = array();
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setName($aName)
  {
    $wasSet = false;
    $this->name = $aName;
    $wasSet = true;
    return $wasSet;
  }

  public function setRole($aRole)
  {
    $wasSet = false;
    $this->role = $aRole;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getRole()
  {
    return $this->role;
  }

  public function getSchedule_index($index)
  {
    $aSchedule = $this->schedules[$index];
    return $aSchedule;
  }

  public function getSchedules()
  {
    $newSchedules = $this->schedules;
    return $newSchedules;
  }

  public function numberOfSchedules()
  {
    $number = count($this->schedules);
    return $number;
  }

  public function hasSchedules()
  {
    $has = $this->numberOfSchedules() > 0;
    return $has;
  }

  public function indexOfSchedule($aSchedule)
  {
    $wasFound = false;
    $index = 0;
    foreach($this->schedules as $schedule)
    {
      if ($schedule->equals($aSchedule))
      {
        $wasFound = true;
        break;
      }
      $index += 1;
    }
    $index = $wasFound ? $index : -1;
    return $index;
  }

  public static function minimumNumberOfSchedules()
  {
    return 0;
  }

  public function addSchedule($aSchedule)
  {
    $wasAdded = false;
    if ($this->indexOfSchedule($aSchedule) !== -1) { return false; }
    $this->schedules[] = $aSchedule;
    $wasAdded = true;
    return $wasAdded;
  }

  public function removeSchedule($aSchedule)
  {
    $wasRemoved = false;
    if ($this->indexOfSchedule($aSchedule) != -1)
    {
      unset($this->schedules[$this->indexOfSchedule($aSchedule)]);
      $this->schedules = array_values($this->schedules);
      $wasRemoved = true;
    }
    return $wasRemoved;
  }

  public function addScheduleAt($aSchedule, $index)
  {  
    $wasAdded = false;
    if($this->addSchedule($aSchedule))
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfSchedules()) { $index = $this->numberOfSchedules() - 1; }
      array_splice($this->schedules, $this->indexOfSchedule($aSchedule), 1);
      array_splice($this->schedules, $index, 0, array($aSchedule));
      $wasAdded = true;
    }
    return $wasAdded;
  }

  public function addOrMoveScheduleAt($aSchedule, $index)
  {
    $wasAdded = false;
    if($this->indexOfSchedule($aSchedule) !== -1)
    {
      if($index < 0 ) { $index = 0; }
      if($index > $this->numberOfSchedules()) { $index = $this->numberOfSchedules() - 1; }
      array_splice($this->schedules, $this->indexOfSchedule($aSchedule), 1);
      array_splice($this->schedules, $index, 0, array($aSchedule));
      $wasAdded = true;
    } 
    else 
    {
      $wasAdded = $this->addScheduleAt($aSchedule, $index);
    }
    return $wasAdded;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->schedules = array();
  }

}
?>