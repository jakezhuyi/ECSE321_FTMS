package ecse321.mcgill.ca.foodtruckmanagementandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.io.File;

import ca.mcgill.ecse321.foodtruckmanagement.controller.FoodTruckManagementController;
import ca.mcgill.ecse321.foodtruckmanagement.controller.InvalidInputException;
import ca.mcgill.ecse321.foodtruckmanagement.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruckmanagement.persistence.PersistenceFoodTruckManagement;

public class SupplyMenu extends AppCompatActivity {
    public FoodTruckManager ftm;
    private String error = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PersistenceFoodTruckManagement.setFilename(getFilesDir().getAbsolutePath() + File.pathSeparator +"foodtruckmanagement.xml");
        System.out.println(getFilesDir().getAbsolutePath() + "foodtruckmanagement.xml");
        PersistenceFoodTruckManagement.loadFoodTruckManagementModel();

        ftm = FoodTruckManager.getInstance();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void refreshData(){
        TextView tv = (TextView) findViewById(R.id.foodSupply_name);
        tv.setText("");
        tv = (TextView) findViewById(R.id.foodSupplyAmount_name);
        tv.setText("");
        tv = (TextView) findViewById(R.id.equipmentSupply_name);
        tv.setText("");
        tv = (TextView) findViewById(R.id.equipmentSupplyAmount_name);
        tv.setText("");
        displayError(tv);

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

    public void addFoodSupply(View v){
        TextView tv = (TextView) findViewById(R.id.foodSupply_name);
        TextView tv2 = (TextView) findViewById(R.id.foodSupplyAmount_name);
        FoodTruckManagementController ftc = new FoodTruckManagementController();
        int amount = 0;
        try{
            amount = Integer.parseInt(tv2.getText().toString());
        }catch (NumberFormatException e){
            amount = 0;
        }
        error = null;
        try {
            ftc.addFoodSupply(tv.getText().toString(), amount);
        }catch (InvalidInputException e){
            error = e.getMessage();
        }
        refreshData();
    }

    public void removeFoodSupply(View v){
        TextView tv = (TextView) findViewById(R.id.foodSupply_name);
        TextView tv2 = (TextView) findViewById(R.id.foodSupplyAmount_name);
        FoodTruckManagementController ftc = new FoodTruckManagementController();
        int amount = 0;
        try{
            amount = Integer.parseInt(tv2.getText().toString());
        }catch (NumberFormatException e){
            amount = 0;
        }
        error = null;
        try {
            ftc.removeFoodSupply(tv.getText().toString(), amount);
        }catch (InvalidInputException e){
            error = e.getMessage();
        }
        refreshData();
    }
    public void addEquipment(View v){
        TextView tv = (TextView) findViewById(R.id.equipmentSupply_name);
        TextView tv2 = (TextView) findViewById(R.id.equipmentSupplyAmount_name);
        FoodTruckManagementController ftc = new FoodTruckManagementController();
        int amount = 0;
        try{
            amount = Integer.parseInt(tv2.getText().toString());
        }catch (NumberFormatException e){
            amount = 0;
        }
        error = null;
        try {
            ftc.addEquipment(tv.getText().toString(), amount);
        }catch (InvalidInputException e){
            error = e.getMessage();
        }
        refreshData();
    }

    public void removeEquipment(View v){
        TextView tv = (TextView) findViewById(R.id.equipmentSupply_name);
        TextView tv2 = (TextView) findViewById(R.id.equipmentSupplyAmount_name);
        FoodTruckManagementController ftc = new FoodTruckManagementController();
        int amount = 0;
        try{
            amount = Integer.parseInt(tv2.getText().toString());
        }catch (NumberFormatException e){
            amount = 0;
        }
        error = null;
        try {
            ftc.removeEquipment(tv.getText().toString(), amount);
        }catch (InvalidInputException e){
            error = e.getMessage();
        }
        refreshData();
    }

}
