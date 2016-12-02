/*PLEASE DO NOT EDIT THIS CODE*/
/*This code was generated using the UMPLE 1.20.1.4071 modeling language!*/

package ca.mcgill.ecse321.foodtruckmanagement.model;
import java.util.*;

// line 40 "../../../../../FoodTruckManagement.ump"
// line 82 "../../../../../FoodTruckManagement.ump"
public class FoodTruckManager
{

  //------------------------
  // STATIC VARIABLES
  //------------------------

  private static FoodTruckManager theInstance = null;

  //------------------------
  // MEMBER VARIABLES
  //------------------------

  //FoodTruckManager Associations
  private List<Employee> employees;
  private List<Equipment> equipment;
  private List<FoodSupply> foodSupplies;
  private List<TransactionOrder> transactionOrders;
  private List<MenuItem> menuItems;

  //------------------------
  // CONSTRUCTOR
  //------------------------

  private FoodTruckManager()
  {
    employees = new ArrayList<Employee>();
    equipment = new ArrayList<Equipment>();
    foodSupplies = new ArrayList<FoodSupply>();
    transactionOrders = new ArrayList<TransactionOrder>();
    menuItems = new ArrayList<MenuItem>();
  }

  public static FoodTruckManager getInstance()
  {
    if(theInstance == null)
    {
      theInstance = new FoodTruckManager();
    }
    return theInstance;
  }

  //------------------------
  // INTERFACE
  //------------------------

  public Employee getEmployee(int index)
  {
    Employee aEmployee = employees.get(index);
    return aEmployee;
  }

  public List<Employee> getEmployees()
  {
    List<Employee> newEmployees = Collections.unmodifiableList(employees);
    return newEmployees;
  }

  public int numberOfEmployees()
  {
    int number = employees.size();
    return number;
  }

  public boolean hasEmployees()
  {
    boolean has = employees.size() > 0;
    return has;
  }

  public int indexOfEmployee(Employee aEmployee)
  {
    int index = employees.indexOf(aEmployee);
    return index;
  }

  public Equipment getEquipment(int index)
  {
    Equipment aEquipment = equipment.get(index);
    return aEquipment;
  }

  public List<Equipment> getEquipment()
  {
    List<Equipment> newEquipment = Collections.unmodifiableList(equipment);
    return newEquipment;
  }

  public int numberOfEquipment()
  {
    int number = equipment.size();
    return number;
  }

  public boolean hasEquipment()
  {
    boolean has = equipment.size() > 0;
    return has;
  }

  public int indexOfEquipment(Equipment aEquipment)
  {
    int index = equipment.indexOf(aEquipment);
    return index;
  }

  public FoodSupply getFoodSupply(int index)
  {
    FoodSupply aFoodSupply = foodSupplies.get(index);
    return aFoodSupply;
  }

  public List<FoodSupply> getFoodSupplies()
  {
    List<FoodSupply> newFoodSupplies = Collections.unmodifiableList(foodSupplies);
    return newFoodSupplies;
  }

  public int numberOfFoodSupplies()
  {
    int number = foodSupplies.size();
    return number;
  }

  public boolean hasFoodSupplies()
  {
    boolean has = foodSupplies.size() > 0;
    return has;
  }

  public int indexOfFoodSupply(FoodSupply aFoodSupply)
  {
    int index = foodSupplies.indexOf(aFoodSupply);
    return index;
  }

  public TransactionOrder getTransactionOrder(int index)
  {
    TransactionOrder aTransactionOrder = transactionOrders.get(index);
    return aTransactionOrder;
  }

  public List<TransactionOrder> getTransactionOrders()
  {
    List<TransactionOrder> newTransactionOrders = Collections.unmodifiableList(transactionOrders);
    return newTransactionOrders;
  }

  public int numberOfTransactionOrders()
  {
    int number = transactionOrders.size();
    return number;
  }

  public boolean hasTransactionOrders()
  {
    boolean has = transactionOrders.size() > 0;
    return has;
  }

  public int indexOfTransactionOrder(TransactionOrder aTransactionOrder)
  {
    int index = transactionOrders.indexOf(aTransactionOrder);
    return index;
  }

  public MenuItem getMenuItem(int index)
  {
    MenuItem aMenuItem = menuItems.get(index);
    return aMenuItem;
  }

  public List<MenuItem> getMenuItems()
  {
    List<MenuItem> newMenuItems = Collections.unmodifiableList(menuItems);
    return newMenuItems;
  }

  public int numberOfMenuItems()
  {
    int number = menuItems.size();
    return number;
  }

  public boolean hasMenuItems()
  {
    boolean has = menuItems.size() > 0;
    return has;
  }

  public int indexOfMenuItem(MenuItem aMenuItem)
  {
    int index = menuItems.indexOf(aMenuItem);
    return index;
  }

  public static int minimumNumberOfEmployees()
  {
    return 0;
  }

  public boolean addEmployee(Employee aEmployee)
  {
    boolean wasAdded = false;
    if (employees.contains(aEmployee)) { return false; }
    employees.add(aEmployee);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEmployee(Employee aEmployee)
  {
    boolean wasRemoved = false;
    if (employees.contains(aEmployee))
    {
      employees.remove(aEmployee);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addEmployeeAt(Employee aEmployee, int index)
  {  
    boolean wasAdded = false;
    if(addEmployee(aEmployee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployees()) { index = numberOfEmployees() - 1; }
      employees.remove(aEmployee);
      employees.add(index, aEmployee);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEmployeeAt(Employee aEmployee, int index)
  {
    boolean wasAdded = false;
    if(employees.contains(aEmployee))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEmployees()) { index = numberOfEmployees() - 1; }
      employees.remove(aEmployee);
      employees.add(index, aEmployee);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEmployeeAt(aEmployee, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfEquipment()
  {
    return 0;
  }

  public boolean addEquipment(Equipment aEquipment)
  {
    boolean wasAdded = false;
    if (equipment.contains(aEquipment)) { return false; }
    equipment.add(aEquipment);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeEquipment(Equipment aEquipment)
  {
    boolean wasRemoved = false;
    if (equipment.contains(aEquipment))
    {
      equipment.remove(aEquipment);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addEquipmentAt(Equipment aEquipment, int index)
  {  
    boolean wasAdded = false;
    if(addEquipment(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveEquipmentAt(Equipment aEquipment, int index)
  {
    boolean wasAdded = false;
    if(equipment.contains(aEquipment))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfEquipment()) { index = numberOfEquipment() - 1; }
      equipment.remove(aEquipment);
      equipment.add(index, aEquipment);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addEquipmentAt(aEquipment, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfFoodSupplies()
  {
    return 0;
  }

  public boolean addFoodSupply(FoodSupply aFoodSupply)
  {
    boolean wasAdded = false;
    if (foodSupplies.contains(aFoodSupply)) { return false; }
    foodSupplies.add(aFoodSupply);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeFoodSupply(FoodSupply aFoodSupply)
  {
    boolean wasRemoved = false;
    if (foodSupplies.contains(aFoodSupply))
    {
      foodSupplies.remove(aFoodSupply);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addFoodSupplyAt(FoodSupply aFoodSupply, int index)
  {  
    boolean wasAdded = false;
    if(addFoodSupply(aFoodSupply))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFoodSupplies()) { index = numberOfFoodSupplies() - 1; }
      foodSupplies.remove(aFoodSupply);
      foodSupplies.add(index, aFoodSupply);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveFoodSupplyAt(FoodSupply aFoodSupply, int index)
  {
    boolean wasAdded = false;
    if(foodSupplies.contains(aFoodSupply))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfFoodSupplies()) { index = numberOfFoodSupplies() - 1; }
      foodSupplies.remove(aFoodSupply);
      foodSupplies.add(index, aFoodSupply);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addFoodSupplyAt(aFoodSupply, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfTransactionOrders()
  {
    return 0;
  }

  public boolean addTransactionOrder(TransactionOrder aTransactionOrder)
  {
    boolean wasAdded = false;
    if (transactionOrders.contains(aTransactionOrder)) { return false; }
    transactionOrders.add(aTransactionOrder);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeTransactionOrder(TransactionOrder aTransactionOrder)
  {
    boolean wasRemoved = false;
    if (transactionOrders.contains(aTransactionOrder))
    {
      transactionOrders.remove(aTransactionOrder);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addTransactionOrderAt(TransactionOrder aTransactionOrder, int index)
  {  
    boolean wasAdded = false;
    if(addTransactionOrder(aTransactionOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTransactionOrders()) { index = numberOfTransactionOrders() - 1; }
      transactionOrders.remove(aTransactionOrder);
      transactionOrders.add(index, aTransactionOrder);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveTransactionOrderAt(TransactionOrder aTransactionOrder, int index)
  {
    boolean wasAdded = false;
    if(transactionOrders.contains(aTransactionOrder))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfTransactionOrders()) { index = numberOfTransactionOrders() - 1; }
      transactionOrders.remove(aTransactionOrder);
      transactionOrders.add(index, aTransactionOrder);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addTransactionOrderAt(aTransactionOrder, index);
    }
    return wasAdded;
  }

  public static int minimumNumberOfMenuItems()
  {
    return 0;
  }

  public boolean addMenuItem(MenuItem aMenuItem)
  {
    boolean wasAdded = false;
    if (menuItems.contains(aMenuItem)) { return false; }
    menuItems.add(aMenuItem);
    wasAdded = true;
    return wasAdded;
  }

  public boolean removeMenuItem(MenuItem aMenuItem)
  {
    boolean wasRemoved = false;
    if (menuItems.contains(aMenuItem))
    {
      menuItems.remove(aMenuItem);
      wasRemoved = true;
    }
    return wasRemoved;
  }

  public boolean addMenuItemAt(MenuItem aMenuItem, int index)
  {  
    boolean wasAdded = false;
    if(addMenuItem(aMenuItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenuItems()) { index = numberOfMenuItems() - 1; }
      menuItems.remove(aMenuItem);
      menuItems.add(index, aMenuItem);
      wasAdded = true;
    }
    return wasAdded;
  }

  public boolean addOrMoveMenuItemAt(MenuItem aMenuItem, int index)
  {
    boolean wasAdded = false;
    if(menuItems.contains(aMenuItem))
    {
      if(index < 0 ) { index = 0; }
      if(index > numberOfMenuItems()) { index = numberOfMenuItems() - 1; }
      menuItems.remove(aMenuItem);
      menuItems.add(index, aMenuItem);
      wasAdded = true;
    } 
    else 
    {
      wasAdded = addMenuItemAt(aMenuItem, index);
    }
    return wasAdded;
  }

  public void delete()
  {
    employees.clear();
    equipment.clear();
    foodSupplies.clear();
    transactionOrders.clear();
    menuItems.clear();
  }

}