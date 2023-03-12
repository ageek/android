package com.example.autosilent1;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Toast;


public class AlarmReceiver extends BroadcastReceiver {

    AudioManager myAudioManager;

    int modeIDS = 0;
    int modeIDN = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        // Toast.makeText(context, "Alarm Received", Toast.LENGTH_LONG).show();
        // Switch the phone to silent mode
        myAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        Bundle extra = intent.getExtras();

        if (extra != null) {
            modeIDS = extra.getInt("modeIDS");
            modeIDN = extra.getInt("modeIDN");
        } else {
            Toast.makeText(context, "extras in Bundle is null", Toast.LENGTH_SHORT).show();;
        }
        if (modeIDS == 1) {
            //Toast.makeText(context, "modeIDS =" + modeIDS, Toast.LENGTH_SHORT).show();
            silent(context);
        } else if (modeIDN == 2) {
            //Toast.makeText(context, "modeIDN =" + modeIDN, Toast.LENGTH_SHORT).show();
            normal(context);
        }
    }

    public void silent(Context context) {
        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        Toast.makeText(context,
                //"Set to Silent Mode",
                Html.fromHtml("Switching to <big><b>Silent Mode</b></big>. "),
                Toast.LENGTH_LONG).show();
    }

    public void normal(Context context) {
        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        Toast.makeText(context,
                //"Set to Normal Mode",
                Html.fromHtml("Set to <big><b>Normal Mode</b></big>."),
                Toast.LENGTH_LONG).show();
    }

    public void vibrate(Context context) {
        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        Toast.makeText(context,
                Html.fromHtml("Set to <big><b>Vibrate Mode</b></big>."),
                Toast.LENGTH_LONG).show();
    }
}
