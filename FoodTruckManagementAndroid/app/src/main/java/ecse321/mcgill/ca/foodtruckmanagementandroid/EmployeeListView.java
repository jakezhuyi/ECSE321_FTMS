package ecse321.mcgill.ca.foodtruckmanagementandroid;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class EmployeeListView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ListView listView = (ListView) findViewById(R.id.Employee_List);

        String [] array = new String[MainActivity.ftm.getEmployees().size()];
        for (int i = 0; i < MainActivity.ftm.getEmployees().size();i++){
            String employee = "Name:   " + MainActivity.ftm.getEmployee(i).getName() +
                    "\nRole:      " + MainActivity.ftm.getEmployee(i).getRole();
            array[i] = employee;
        }
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                array);

        listView.setAdapter(arrayAdapter);
    }

}
