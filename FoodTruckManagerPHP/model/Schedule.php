<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-edef018 modeling language!*/

class Schedule
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Schedule Attributes
  private $workDay;
  private $startTime;
  private $endTime;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aWorkDay, $aStartTime, $aEndTime)
  {
    $this->workDay = $aWorkDay;
    $this->startTime = $aStartTime;
    $this->endTime = $aEndTime;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function setWorkDay($aWorkDay)
  {
    $wasSet = false;
    $this->workDay = $aWorkDay;
    $wasSet = true;
    return $wasSet;
  }

  public function setStartTime($aStartTime)
  {
    $wasSet = false;
    $this->startTime = $aStartTime;
    $wasSet = true;
    return $wasSet;
  }

  public function setEndTime($aEndTime)
  {
    $wasSet = false;
    $this->endTime = $aEndTime;
    $wasSet = true;
    return $wasSet;
  }

  public function getWorkDay()
  {
    return $this->workDay;
  }

  public function getStartTime()
  {
    return $this->startTime;
  }

  public function getEndTime()
  {
    return $this->endTime;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>