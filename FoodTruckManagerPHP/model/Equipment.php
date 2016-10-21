<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-edef018 modeling language!*/

class Equipment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Equipment Attributes
  private $name;
  private $supply;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aSupply)
  {
    $this->name = $aName;
    $this->supply = $aSupply;
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

  public function setSupply($aSupply)
  {
    $wasSet = false;
    $this->supply = $aSupply;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getSupply()
  {
    return $this->supply;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>