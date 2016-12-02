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
import ca.mcgill.ecse321.foodtruckmanagement.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruckmanagement.persistence.PersistenceFoodTruckManagement;

public class MainActivity extends AppCompatActivity {

    private String error = null;
    public static boolean check = true;
    //FoodTruckManager is public to avoid double loading
    //spinners when switching activities
    public static FoodTruckManager ftm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //loading from persistence
        if (check) {
            PersistenceFoodTruckManagement.setFilename(getFilesDir().getAbsolutePath() + File.pathSeparator + "foodtruckmanagement.xml");
            System.out.println(getFilesDir().getAbsolutePath() + File.pathSeparator + "foodtruckmanagement.xml");
            PersistenceFoodTruckManagement.loadFoodTruckManagementModel();

            check = false;
        }
        ftm = FoodTruckManager.getInstance();
    }
    //Switching between activitiy menus
    public void employeeMenu(View v){
        startActivity(new Intent(MainActivity.this, EmployeeMenu.class));
    }
    public void supplyMenu(View v){
        startActivity(new Intent(MainActivity.this, SupplyMenu.class));
    }

    public void MenuItemMenu(View v) {

        startActivity(new Intent(MainActivity.this, FoodMenu.class));
    }
}
