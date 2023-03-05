package com.example.alarmmanagersimple;

import android.content.BroadcastReceiver;
import android.widget.Toast;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(final android.content.Context context, final android.content.Intent intent) {
        Toast.makeText(context,"Received Alarm !!!", Toast.LENGTH_LONG).show();
    }
}
