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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    TextView alarmTime;
    Button selectAlarmButton;
    TextView normalizeTime;
    TimePickerDialog timePickerDialog;
    public AudioManager myAudioManager;
    final static int RQS_1 = 1;
    // pick the user selected silent duration, default 30
    EditText silentDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        silentDuration = findViewById(R.id.duration);

        alarmTime = findViewById(R.id.silentMode);
        selectAlarmButton = findViewById(R.id.selectTimeButton);

        normalizeTime = findViewById(R.id.normalMode);


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

            if (targetcal.compareTo(calNow) <= 0) {
                targetcal.add(Calendar.DATE, 1);
            }
            setAlarm(targetcal);
        }
    };

    public void setAlarm(Calendar targetCal) {
        alarmTime.setText("AutoSilenced at:" + targetCal.getTime());

        Intent intentS = new Intent(getBaseContext(), AlarmReceiver.class);
        intentS.putExtra("modeIDS", 1);
        PendingIntent pendingIntentS = PendingIntent.getBroadcast(
                getBaseContext(), RQS_1, intentS, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, targetCal.getTimeInMillis(),
                pendingIntentS);

        // - - - - - - - - - - -
        // Add another PendingIntent for  switching to Normal mode after 'silentDuration'

        Intent intentN = new Intent(getBaseContext(), AlarmReceiver.class);
        intentN.putExtra("modeIDN", 2);
        String text = silentDuration.getText().toString();
        // default duration
        int durationInMin = 2;
        if (!silentDuration.getText().toString().isEmpty()) {
            durationInMin = Integer.parseInt(text);
        }
        ;
        PendingIntent pendingIntentN = PendingIntent.getBroadcast(
                getBaseContext(), RQS_1, intentN, PendingIntent.FLAG_UPDATE_CURRENT);
        alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP,
                targetCal.getTimeInMillis() + (durationInMin * 60 * 1000),
                pendingIntentN);
//        Toast.makeText(getBaseContext(), "Will switch back to normal mode at "
//                        + new Date(targetCal.getTimeInMillis() + (durationInMin * 60 * 1000)),
//                Toast.LENGTH_LONG).show();
        normalizeTime.setText("Normalized at: " + new Date(targetCal.getTimeInMillis() + (durationInMin * 60 * 1000)));
    }

}