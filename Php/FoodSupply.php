<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-edef018 modeling language!*/

class FoodSupply
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FoodSupply Attributes
  private $name;
  private $amount;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aAmount)
  {
    $this->name = $aName;
    $this->amount = $aAmount;
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

  public function setAmount($aAmount)
  {
    $wasSet = false;
    $this->amount = $aAmount;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getAmount()
  {
    return $this->amount;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>