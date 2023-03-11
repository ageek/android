package com.example.autosilent1;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView alarmTime;
    Button selectAlarmButton;
    TimePickerDialog timePickerDialog;

    public AudioManager myAudioManager;

    final static int RQS_1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alarmTime = findViewById(R.id.alarmTime);
        selectAlarmButton = findViewById(R.id.selectTimeButton);

        // for automatically setting phone to silent modes etc
        myAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // for Android version 7+, you need to ask user to give DND permission
        // by opening the DND settings
        // https://stackoverflow.com/questions/39151453/in-android-7-api-level-24-my-app-is-not-allowed-to-mute-phone-set-ringer-mode
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && !mNotificationManager.isNotificationPolicyAccessGranted()) {

            Intent intent = new Intent(
                    android.provider.Settings
                            .ACTION_NOTIFICATION_POLICY_ACCESS_SETTINGS);

            startActivity(intent);
        }
    }

    public void selectTime(View view) {
        Calendar cal = Calendar.getInstance();
        timePickerDialog = new TimePickerDialog(MainActivity.this,
                onTimeSetListener, cal.get(Calendar.HOUR_OF_DAY),
                cal.get(Calendar.MINUTE), true);
        timePickerDialog.show();
    }

    TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            Calendar calNow = Calendar.getInstance();
            Calendar targetcal = (Calendar) calNow.clone();

            targetcal.set(Calendar.HOUR_OF_DAY, hourOfDay);
            targetcal.set(Calendar.MINUTE, minute);
            targetcal.set(Calendar.SECOND, 0);
            targetcal.set(Calendar.MILLISECOND, 0);

            if (targetcal.compareTo(calNow ) <= 0) {
                targetcal.add(Calendar.DATE, 1);
            }
            setAlarm(targetcal);
        }
    };

    public void setAlarm (Calendar targetCal) {
        alarmTime.setText("Alarm time set for:" +targetCal.getTime());

        Intent  intent = new  Intent(getBaseContext(), AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getBaseContext(), RQS_1, intent, 0);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                pendingIntent);
    }

//    public void silent(View v) {
//        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
//    }
}