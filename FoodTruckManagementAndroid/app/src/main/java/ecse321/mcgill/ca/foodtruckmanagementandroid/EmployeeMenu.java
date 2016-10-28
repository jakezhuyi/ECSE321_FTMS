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

import ca.mcgill.ecse321.foodtruckmanagement.controller.FoodTruckManagementController;
import ca.mcgill.ecse321.foodtruckmanagement.controller.InvalidInputException;
import ca.mcgill.ecse321.foodtruckmanagement.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruckmanagement.persistence.PersistenceFoodTruckManagement;

public class EmployeeMenu extends AppCompatActivity {
    private FoodTruckManager ftm;
    private String error = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        PersistenceFoodTruckManagement.setFilename(getFilesDir().getAbsolutePath() + File.pathSeparator +"foodtruckmanagement.xml");
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
        TextView tv;
        tv = (TextView) findViewById(R.id.employee_name);
        tv.setText("");
        tv = (TextView) findViewById(R.id.employee_role);
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
    public void addEmployee(View v){
        TextView tv = (TextView) findViewById(R.id.employee_name);
        TextView tv2 = (TextView) findViewById(R.id.employee_role);
        FoodTruckManagementController ftc = new FoodTruckManagementController();
        error = null;
        try {
            ftc.addEmployee(tv.getText().toString(), tv2.getText().toString());
        }catch (InvalidInputException e){
            error = e.getMessage();
        }
        refreshData();
    }

}
