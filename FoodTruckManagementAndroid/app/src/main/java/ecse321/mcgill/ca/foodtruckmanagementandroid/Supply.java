package ecse321.mcgill.ca.foodtruckmanagementandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import ca.mcgill.ecse321.foodtruckmanagement.model.FoodSupply;

import static android.R.id.list;

public class Supply extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_supply);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ListView listView = (ListView) findViewById(R.id.foodSupply_list);

        String [] array = new String[MainActivity.ftm.getFoodSupplies().size()];
        for (int i = 0; i < MainActivity.ftm.getFoodSupplies().size();i++){
            String supplyname = "Item:       " + MainActivity.ftm.getFoodSupply(i).getName() + "\nAmount: " + MainActivity.ftm.getFoodSupply(i).getAmount();
            array[i] = supplyname;
        }
        if (MainActivity.ftm.getFoodSupplies().size() == 0){
            array = new String[1];
            array [0] = "No Items To display";
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                array);

        listView.setAdapter(arrayAdapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
