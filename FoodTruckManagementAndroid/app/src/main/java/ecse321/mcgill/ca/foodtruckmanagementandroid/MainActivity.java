package ecse321.mcgill.ca.foodtruckmanagementandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.File;

import ca.mcgill.ecse321.foodtruckmanagement.controller.FoodTruckManagementController;
import ca.mcgill.ecse321.foodtruckmanagement.controller.InvalidInputException;
import ca.mcgill.ecse321.foodtruckmanagement.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruckmanagement.persistence.PersistenceFoodTruckManagement;

public class MainActivity extends AppCompatActivity {

    private String error = null;
    public static boolean check = true;
    public static FoodTruckManager ftm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (check) {
            PersistenceFoodTruckManagement.setFilename(getFilesDir().getAbsolutePath() + File.pathSeparator + "foodtruckmanagement.xml");
            System.out.println(getFilesDir().getAbsolutePath() + File.pathSeparator + "foodtruckmanagement.xml");
            PersistenceFoodTruckManagement.loadFoodTruckManagementModel();

            check = false;
        }
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

    public void employeeMenu(View v){
        startActivity(new Intent(MainActivity.this, EmployeeMenu.class));
    }
    public void supplyMenu(View v){
        startActivity(new Intent(MainActivity.this, SupplyMenu.class));
    }

    public void MenuItemMenu(View v){

        startActivity(new Intent(MainActivity.this, FoodMenu.class));
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setTime(int id, int h, int m) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d:%02d", h, m));
    }
    public void setDate(int id, int d, int m, int y) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d-%02d-%04d", d, m + 1, y));
    }
}
