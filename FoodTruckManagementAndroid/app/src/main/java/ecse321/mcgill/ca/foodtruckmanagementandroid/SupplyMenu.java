package ecse321.mcgill.ca.foodtruckmanagementandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.io.File;

import ca.mcgill.ecse321.foodtruckmanagement.controller.EquipmentSupplyController;
import ca.mcgill.ecse321.foodtruckmanagement.controller.FoodSupplyController;
import ca.mcgill.ecse321.foodtruckmanagement.controller.InvalidInputException;

public class SupplyMenu extends AppCompatActivity {
    //public FoodTruckManager ftm;
    private String error = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply_menu);
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
    // tries to add food supply. if no errors it adds the supply
    public void addFoodSupply(View v){
        TextView tv = (TextView) findViewById(R.id.foodSupply_name);
        TextView tv2 = (TextView) findViewById(R.id.foodSupplyAmount_name);
        FoodSupplyController ftc = new FoodSupplyController();
        int amount = 0;
        try{
            amount = Integer.parseInt(tv2.getText().toString());
        }catch (NumberFormatException e){
            amount = 0;
        }
        error = null;
        try {
            ftc.addSupply(tv.getText().toString(), amount);
        }catch (InvalidInputException e){
            error = e.getMessage();
        }
        refreshData();
    }
    // same as add food supply but this removes it
    public void removeFoodSupply(View v){
        TextView tv = (TextView) findViewById(R.id.foodSupply_name);
        TextView tv2 = (TextView) findViewById(R.id.foodSupplyAmount_name);
        FoodSupplyController ftc = new FoodSupplyController();
        int amount = 0;
        try{
            amount = Integer.parseInt(tv2.getText().toString());
        }catch (NumberFormatException e){
            amount = 0;
        }
        error = null;
        try {
            ftc.removeSupply(tv.getText().toString(), amount);
        }catch (InvalidInputException e){
            error = e.getMessage();
        }
        refreshData();
    }
    //same as add foodSupply but with equipment
    public void addEquipment(View v){
        TextView tv = (TextView) findViewById(R.id.equipmentSupply_name);
        TextView tv2 = (TextView) findViewById(R.id.equipmentSupplyAmount_name);
        EquipmentSupplyController ftc = new EquipmentSupplyController();
        int amount = 0;
        try{
            amount = Integer.parseInt(tv2.getText().toString());
        }catch (NumberFormatException e){
            amount = 0;
        }
        error = null;
        try {
            ftc.addSupply(tv.getText().toString(), amount);
        }catch (InvalidInputException e){
            error = e.getMessage();
        }
        refreshData();
    }

    public void removeEquipment(View v){
        TextView tv = (TextView) findViewById(R.id.equipmentSupply_name);
        TextView tv2 = (TextView) findViewById(R.id.equipmentSupplyAmount_name);
        EquipmentSupplyController ftc = new EquipmentSupplyController();
        int amount = 0;
        try{
            amount = Integer.parseInt(tv2.getText().toString());
        }catch (NumberFormatException e){
            amount = 0;
        }
        error = null;
        try {
            ftc.removeSupply(tv.getText().toString(), amount);
        }catch (InvalidInputException e){
            error = e.getMessage();
        }
        refreshData();
    }
    //Starts new activity to show supply in a listview
    public void viewSupply(View v){

        startActivity(new Intent(SupplyMenu.this, Supply.class));
    }

    public void viewEquipment(View c){
        startActivity(new Intent(SupplyMenu.this, EquipmentSupplyView.class));
    }



}
