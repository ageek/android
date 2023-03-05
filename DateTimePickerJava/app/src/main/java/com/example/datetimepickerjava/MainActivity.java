package com.example.datetimepickerjava;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity /*implements View.OnClickListener*/ {
    Button datePicker;
    Button timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // findViewById() should follow setContentView, else will throw exception
        // as layout would not have been initialized properly
        datePicker = findViewById(R.id.select_date_button);
        timePicker = findViewById(R.id.select_time_button);

        // either set onClick method(option 1) in activity_main or implement View.OnClickListener (Option 2)
        // and override onClick as shown below and use the following code
        //datePicker.setOnClickListener(this);
        //timePicker.setOnClickListener(this);

    }

//    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.select_date_button:
//                selectDate(v);
//                break;
//            case R.id.select_time_button:
//                selectTime(v);
//                break;
//
//        }
//
//    }

    public void selectDate(View view) {
        // get current date
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int yr, int mn, int dy) {
                        EditText date = (EditText) findViewById(R.id.select_date_text);
                        date.setText(yr + " - " + (mn+1) + "- " + dy);
                    }
                }, year, month, day
        );
        datePickerDialog.show();

    }

    public void selectTime(View view) {
        // get current time
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hr, int min) {
                        EditText time = (EditText) findViewById(R.id.select_time_text);
                        time.setText(hr + ":" + min);
                    }
                }, hour, minute, true);
        timePickerDialog.show();
    }
}