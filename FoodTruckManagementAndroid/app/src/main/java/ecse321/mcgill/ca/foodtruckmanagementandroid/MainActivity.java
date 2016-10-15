package ecse321.mcgill.ca.foodtruckmanagementandroid;

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

    public FoodTruckManager ftm;
    public String error = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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

        refreshData();
    }

    private void refreshData(){

        TextView tv = (TextView) findViewById(R.id.foodSupply_name);
        TextView tv2 = (TextView) findViewById(R.id.foodSupplyAmount_name);
        displayError(tv2);
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
            //todo handle error
            error = e.getMessage();
        }

        refreshData();

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
}
