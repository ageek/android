package com.example.alarmmanagersimple;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    TimePicker timePicker;
    TextView textView;
    Button timeButton;
    Calendar cal;

    final static int RQS_1 = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        timePicker = findViewById(R.id.timepicker);
        textView = findViewById(R.id.alarmTime);
        timeButton = findViewById(R.id.timePickerButton);

        cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR_OF_DAY);
        int minute = cal.get(Calendar.MINUTE);
        showTime(hour, minute);


    }

    public void setTime(View v) {
        int hour = timePicker.getCurrentHour();
        int min = timePicker.getCurrentMinute();
        showTime(hour, min);

        cal.set(Calendar.HOUR, hour);
        cal.set(Calendar.MINUTE, min);
        cal.set(Calendar.SECOND, 0);
        setAlarm(cal);
    }

    public void showTime(int hr, int min) {
        String timeString = "Selected time: <b> <i> " + hr + ":" + min + "</b></i>";
        textView.setText(Html.fromHtml(timeString));
    }

    public void setAlarm(Calendar cal) {
        Intent intent = new Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getBaseContext(), RQS_1,
                intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(), pendingIntent);
        Toast.makeText(getBaseContext(), "Successfully set Alarm", Toast.LENGTH_LONG).show();
    }
}