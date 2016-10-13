/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.foodtruckmanagement.model;

// line 28 "../../../../../FoodTruckManagement.ump"
// line 70 "../../../../../FoodTruckManagement.ump"
public class MenuItem
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //MenuItem Attributes
  private String name;
  private int amountSold;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public MenuItem(String aName, int aAmountSold)
  {
    name = aName;
    amountSold = aAmountSold;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public boolean setName(String aName)
  {
    boolean wasSet = false;
    name = aName;
    wasSet = true;
    return wasSet;
  }

  public boolean setAmountSold(int aAmountSold)
  {
    boolean wasSet = false;
    amountSold = aAmountSold;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getAmountSold()
  {
    return amountSold;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "amountSold" + ":" + getAmountSold()+ "]"
     + outputString;
  }
}