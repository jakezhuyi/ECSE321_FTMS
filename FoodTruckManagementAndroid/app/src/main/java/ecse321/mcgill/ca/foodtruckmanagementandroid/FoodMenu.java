package ecse321.mcgill.ca.foodtruckmanagementandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Iterator;

import ca.mcgill.ecse321.foodtruckmanagement.controller.InvalidInputException;
import ca.mcgill.ecse321.foodtruckmanagement.controller.MenuItemController;
import ca.mcgill.ecse321.foodtruckmanagement.model.Employee;
import ca.mcgill.ecse321.foodtruckmanagement.model.MenuItem;


public class FoodMenu extends AppCompatActivity {
    private String error = null;
    private HashMap<Integer, MenuItem> menuItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        refreshData();
    }
    private void refreshData(){
        TextView tv = (TextView) findViewById(R.id.MenuItem);
        tv.setText("");
        tv = (TextView) findViewById(R.id.MenuItemAmount);
        displayError(tv);

        Spinner spinner = (Spinner) findViewById(R.id.menuspinner);
        ArrayAdapter<CharSequence> menuItemAdapter = new
                ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
       menuItemAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        this.menuItems = new HashMap<Integer, MenuItem>();
        int i = 0;
        for (Iterator<MenuItem> menuItems = MainActivity.ftm.getMenuItems().iterator();
             menuItems.hasNext(); i++) {
            MenuItem e = menuItems.next();
            menuItemAdapter.add(e.getName());
            this.menuItems.put(i, e);
        }

        spinner.setAdapter(menuItemAdapter);
    }

    private void displayError(TextView tv){
        TextView e = (TextView) findViewById(R.id.error_name);
        tv.setText("");
        if (error != null){
            //message_error
            e.setText(error);
        }else {
            e.setText("");
        }
    }
    public void addMenuItem(View v){
        TextView tv = (TextView) findViewById(R.id.MenuItem);
        MenuItemController ftc = new MenuItemController();
        error = null;
        try {
            ftc.addMenuItem(tv.getText().toString());
        }catch (InvalidInputException e){
            error = e.getMessage();
        }
        refreshData();
    }

    public void claimOrder(View v){
        Spinner eSpinner=(Spinner) findViewById(R.id.menuspinner);
        MenuItemController ftc = new MenuItemController();
        TextView tv = (TextView) findViewById(R.id.MenuItemAmount);
        int amount = 0;
        try{
            amount = Integer.parseInt(tv.getText().toString());
        }catch (NumberFormatException e){
            amount = 0;
        }
        error = null;
        try{
            ftc.claimOrder(menuItems.get(eSpinner.getSelectedItemPosition()),amount);
        } catch (InvalidInputException e) {
            error = e.getMessage();
        }
        // update visuals

        refreshData();
    }
    public void popReport(View v){
        String [] array = new String[1];
        String mostPopular = "";
        int amountSold = 0;
        if (MainActivity.ftm.getMenuItems().size() == 0){
            array[0] = "No menu items entered";
        }else{
            for (int i = 0; i < MainActivity.ftm.getMenuItems().size();i++){
                if (amountSold < MainActivity.ftm.getMenuItem(i).getAmountSold()){
                    amountSold = MainActivity.ftm.getMenuItem(i).getAmountSold();
                    mostPopular = MainActivity.ftm.getMenuItem(i).getName();
                }
            }
            String supplyname = "Item:       " + mostPopular + "\nAmount: "
                    + amountSold;
            array[0] = supplyname;
        }
        ListView listView = (ListView) findViewById(R.id.FoodItemMenu_list);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                array);

        listView.setAdapter(arrayAdapter);
    }

}
