package com.example.autosilent1;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.text.Html;
import android.view.View;
import android.widget.Toast;


public class AlarmReceiver extends BroadcastReceiver {

    AudioManager myAudioManager;

    // to remain silent for X minutes
    int silentDuration = 10;

    @Override
    public void onReceive(Context context, Intent intent) {
        // Toast.makeText(context, "Alarm Received", Toast.LENGTH_LONG).show();
        // Switch the phone to silent mode
        myAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        silent(context);
    }

    public void silent(Context context) {
        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        Toast.makeText(context,
                //"Set to Silent Mode",
                Html.fromHtml("Switching to <big><b>Silent Mode</b></big> for "),
                Toast.LENGTH_LONG).show();
    }

    public void normal(Context context) {
        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        Toast.makeText(context,
                //"Set to Normal Mode",
                Html.fromHtml("Set to <big><b>Normal Mode</b></big"),
                Toast.LENGTH_LONG).show();
    }

    public void vibrate(Context context) {
        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        Toast.makeText(context,
                Html.fromHtml("Set to <big><b>Vibrate Mode</b></big"),
                Toast.LENGTH_LONG).show();
    }
}
