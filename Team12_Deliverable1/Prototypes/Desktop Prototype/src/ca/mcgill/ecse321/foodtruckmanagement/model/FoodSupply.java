/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.foodtruckmanagement.model;

// line 23 "../../../../../FoodTruckManagement.ump"
// line 66 "../../../../../FoodTruckManagement.ump"
public class FoodSupply
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FoodSupply Attributes
  private String name;
  private int amount;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public FoodSupply(String aName, int aAmount)
  {
    name = aName;
    amount = aAmount;
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

  public boolean setAmount(int aAmount)
  {
    boolean wasSet = false;
    amount = aAmount;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getAmount()
  {
    return amount;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "amount" + ":" + getAmount()+ "]"
     + outputString;
  }
}