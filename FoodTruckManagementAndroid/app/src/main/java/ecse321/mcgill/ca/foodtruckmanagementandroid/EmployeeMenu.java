package ecse321.mcgill.ca.foodtruckmanagementandroid;

import android.content.Intent;
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

import java.io.File;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;

import ca.mcgill.ecse321.foodtruckmanagement.controller.FoodTruckManagementController;
import ca.mcgill.ecse321.foodtruckmanagement.controller.InvalidInputException;
import ca.mcgill.ecse321.foodtruckmanagement.model.Employee;
import ca.mcgill.ecse321.foodtruckmanagement.model.FoodTruckManager;
import ca.mcgill.ecse321.foodtruckmanagement.persistence.PersistenceFoodTruckManagement;

public class EmployeeMenu extends AppCompatActivity{
    private String error = null;
    private HashMap<Integer, Employee> employees;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_menu);
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
        //ftm = FoodTruckManager.getInstance();
        refreshData();
    }

    private void refreshData(){
        TextView tv;
        tv = (TextView) findViewById(R.id.employee_name);
        tv.setText("");
        tv = (TextView) findViewById(R.id.employee_role);
        displayError(tv);

        // Initialize the data in the employee spinner
        Spinner spinner = (Spinner) findViewById(R.id.employeespinner);
        ArrayAdapter<CharSequence> employeeAdapter = new
                ArrayAdapter<CharSequence>(this, android.R.layout.simple_spinner_item);
        employeeAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item);
        this.employees = new HashMap<Integer, Employee>();
        int i = 0;
        for (Iterator<Employee> employees = MainActivity.ftm.getEmployees().iterator();
             employees.hasNext();i++) {
            Employee e = employees.next();
            employeeAdapter.add(e.getName());
            this.employees.put(i, e);
        }

        spinner.setAdapter(employeeAdapter);

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

    public void removeEmployee(View v){
        Spinner eSpinner=(Spinner) findViewById(R.id.employeespinner);
        FoodTruckManagementController ftc = new FoodTruckManagementController();

        try {
            ftc.removeEmployee(employees.get(eSpinner.getSelectedItemPosition()));
        } catch (InvalidInputException e) {

            error = e.getMessage();
        }
        // update visuals

        refreshData();
    }
    public void viewEmployeeScedule(View v){
        Spinner eSpinner=(Spinner) findViewById(R.id.employeespinner);
        ListView listView = (ListView) findViewById(R.id.EmployeeScheduleViewer_list);
        if (MainActivity.ftm.getEmployees().size() != 0){
            String [] array = new String[MainActivity.ftm.getEmployee(eSpinner.getSelectedItemPosition()).getSchedules().size()];

            for (int i = 0; i < MainActivity.ftm.getEmployee(eSpinner.getSelectedItemPosition()).getSchedules().size();i++){
              String day = "Day:                  " + MainActivity.ftm.getEmployee(eSpinner.getSelectedItemPosition()).getSchedule(i).getWorkDay().toString() +
                        "\nStart Time:      " + MainActivity.ftm.getEmployee(eSpinner.getSelectedItemPosition()).getSchedule(i).getStartTime().toString()
                        +"\nEnd Time:        " + MainActivity.ftm.getEmployee(eSpinner.getSelectedItemPosition()).getSchedule(i).getEndTime().toString();
                array[i] = day;
            }
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this,
                android.R.layout.simple_list_item_1,
                array);

            listView.setAdapter(arrayAdapter);
        } else {
            String [] array = new String[1];
            array[0] = "No employee selected";
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    array);

            listView.setAdapter(arrayAdapter);
        }

    }
    public void viewEmployeeList(View v){
        startActivity(new Intent(EmployeeMenu.this, EmployeeListView.class));
    }

    public void assignSchedule(View v) throws ParseException {
        Spinner eSpinner=(Spinner) findViewById(R.id.employeespinner);
        TextView tv = (TextView)eSpinner.getSelectedView();
        TextView eventDate = (TextView) findViewById(R.id.newevent_date);
        TextView startTime = (TextView) findViewById(R.id.newstartTime_date);
        TextView endTime = (TextView) findViewById(R.id.newendTime_date);
        FoodTruckManagementController ftc = new FoodTruckManagementController();

        String date =  Integer.toString(getDateFromLabel(eventDate.getText().toString()).getInt("year", 2))+ "-" +
                String.format("%02d",(getDateFromLabel(eventDate.getText().toString()).getInt("month", 1) + 1))  + "-" +
                String.format("%02d",(getDateFromLabel(eventDate.getText().toString()).getInt("day", 0)));
        String start = String.format("%02d",(getTimeFromLabel(startTime.getText().toString()).getInt("hour", 0))) + ":" +
                String.format("%02d",(getTimeFromLabel(startTime.getText().toString()).getInt("minute", 1))) + ":00";
        String end = String.format("%02d",(getTimeFromLabel(endTime.getText().toString()).getInt("hour", 0))) + ":" +
                String.format("%02d",(getTimeFromLabel(endTime.getText().toString()).getInt("minute", 1))) + ":00";

        SimpleDateFormat formatter = new SimpleDateFormat("EEE d MMM yy hh:mm:ss");
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);
        Time s = Time.valueOf(start);
        Time en =  Time.valueOf(end);
        error = null;
        try {
            ftc.assignSchedule(employees.get(eSpinner.getSelectedItemPosition()), sqlDate, s, en);
        } catch (InvalidInputException e) {

            error = e.getMessage();
        }
        // update visuals

        refreshData();
    }
    public void showDatePickerDialog(View v) {
        TextView tf = (TextView) v;
        Bundle args = getDateFromLabel(tf.getText());
        args.putInt("id", v.getId());
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }

    public void showTimePickerDialog(View v) {
        TextView tf = (TextView) v;
        Bundle args = getTimeFromLabel(tf.getText());
        args.putInt("id", v.getId());
        TimePickerFragment newFragment = new TimePickerFragment();
        newFragment.setArguments(args);
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    private Bundle getTimeFromLabel(CharSequence text) {
        Bundle rtn = new Bundle();
        String comps[] = text.toString().split(":");
        int hour = 12;
        int minute = 0;
        if (comps.length == 2) {
            hour = Integer.parseInt(comps[0]);
            minute = Integer.parseInt(comps[1]);
        }
        rtn.putInt("hour", hour);
        rtn.putInt("minute", minute);
        return rtn;
    }
    private Bundle getDateFromLabel(CharSequence text) {
        Bundle rtn = new Bundle();
        String comps[] = text.toString().split("-");
        int day = 1;
        int month = 1;
        int year = 1;
        if (comps.length == 3) {
            day = Integer.parseInt(comps[0]);
            month = Integer.parseInt(comps[1]);
            year = Integer.parseInt(comps[2]);
        }
        rtn.putInt("day", day);
        rtn.putInt("month", month-1);
        rtn.putInt("year", year);
        return rtn;
    }
    public void setDate(int id, int d, int m, int y) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d-%02d-%04d", d, m + 1, y));
    }
    public void setTime(int id, int h, int m) {
        TextView tv = (TextView) findViewById(id);
        tv.setText(String.format("%02d:%02d", h, m));
    }


}
