<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-edef018 modeling language!*/

class MenuItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MenuItem Attributes
  private $name;
  private $amountSold;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aName, $aAmountSold)
  {
    $this->name = $aName;
    $this->amountSold = $aAmountSold;
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

  public function setAmountSold($aAmountSold)
  {
    $wasSet = false;
    $this->amountSold = $aAmountSold;
    $wasSet = true;
    return $wasSet;
  }

  public function getName()
  {
    return $this->name;
  }

  public function getAmountSold()
  {
    return $this->amountSold;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {}

}
?>