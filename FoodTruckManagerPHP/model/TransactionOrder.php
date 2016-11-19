<?php
/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.24.0-edef018 modeling language!*/

class TransactionOrder
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TransactionOrder Associations
  private $menuItem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public function __construct($aMenuItem)
  {
    if (!$this->setMenuItem($aMenuItem))
    {
      throw new Exception("Unable to create TransactionOrder due to aMenuItem");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public function getMenuItem()
  {
    return $this->menuItem;
  }

  public function setMenuItem($aNewMenuItem)
  {
    $wasSet = false;
    if ($aNewMenuItem != null)
    {
      $this->menuItem = $aNewMenuItem;
      $wasSet = true;
    }
    return $wasSet;
  }

  public function equals($compareTo)
  {
    return $this == $compareTo;
  }

  public function delete()
  {
    $this->menuItem = null;
  }

}
?>