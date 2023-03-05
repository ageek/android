package com.example.timepickerjava2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView timeTextView;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timePicker = (TimePicker) findViewById(R.id.timepicker);
        timePicker.setIs24HourView(false);
        timeTextView = (TextView) findViewById(R.id.time_textView);

        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);

        // show the current time even when the time is not yet selected
        showTime(hour, minute);

    }

    public void setTime(View v) {
        int hour = timePicker.getCurrentHour();
        int min = timePicker.getCurrentMinute();
        showTime(hour, min);

    }

    public void showTime(int hr, int min) {
        String timeString = "Selected time: <b> <i> " + hr + ":" + min + "</b></i>";
        timeTextView.setText(Html.fromHtml(timeString));
    }
}