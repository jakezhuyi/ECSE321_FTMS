package ecse321.mcgill.ca.foodtruckmanagementandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EquipmentSupplyView extends AppCompatActivity {
    //View for the supply using a list view
    // Saves the values of the supply from the ftm
    //to an array and adds them to the listview
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_supply_view);
        ListView listView = (ListView) findViewById(R.id.equipmentSupply_list);
        String [] array = new String[MainActivity.ftm.getEquipment().size()];
        for (int i = 0; i < MainActivity.ftm.getEquipment().size();i++){
            String supplyname = "Item:       " + MainActivity.ftm.getEquipment(i).getName() + "\nAmount: " + MainActivity.ftm.getEquipment(i).getAmount();
            array[i] = supplyname;
        }
        //If no menu items, return error
        if (MainActivity.ftm.getEquipment().size() == 0){
            array = new String[1];
            array [0] = "No Items To display";
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                array);

        listView.setAdapter(arrayAdapter);
    }
}
