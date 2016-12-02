/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.foodtruckmanagement.model;

// line 35 "../../../../../FoodTruckManagement.ump"
// line 76 "../../../../../FoodTruckManagement.ump"
public class TransactionOrder
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //TransactionOrder Associations
  private MenuItem menuItem;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public TransactionOrder(MenuItem aMenuItem)
  {
    if (!setMenuItem(aMenuItem))
    {
      throw new RuntimeException("Unable to create TransactionOrder due to aMenuItem");
    }
  }

  //------------------------
  // INTERFACE
  //------------------------

  public MenuItem getMenuItem()
  {
    return menuItem;
  }

  public boolean setMenuItem(MenuItem aNewMenuItem)
  {
    boolean wasSet = false;
    if (aNewMenuItem != null)
    {
      menuItem = aNewMenuItem;
      wasSet = true;
    }
    return wasSet;
  }

  public void delete()
  {
    menuItem = null;
  }

}