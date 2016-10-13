/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.foodtruckmanagement.model;

// line 17 "../../../../../FoodTruckManagement.ump"
// line 60 "../../../../../FoodTruckManagement.ump"
public class Equipment
{

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //Equipment Attributes
  private String name;
  private int supply;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  public Equipment(String aName, int aSupply)
  {
    name = aName;
    supply = aSupply;
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

  public boolean setSupply(int aSupply)
  {
    boolean wasSet = false;
    supply = aSupply;
    wasSet = true;
    return wasSet;
  }

  public String getName()
  {
    return name;
  }

  public int getSupply()
  {
    return supply;
  }

  public void delete()
  {}


  public String toString()
  {
	  String outputString = "";
    return super.toString() + "["+
            "name" + ":" + getName()+ "," +
            "supply" + ":" + getSupply()+ "]"
     + outputString;
  }
}