package com.example.modesetter;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.HtmlCompat;

public class MainActivity extends AppCompatActivity {

    private AudioManager myAudioManager;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //mNotificationManager.setInterruptionFilter(NotificationManager.INTERRUPTION_FILTER_NONE);


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

    public void checkMode(View v) {
        int mod = myAudioManager.getRingerMode();

        if (mod == AudioManager.RINGER_MODE_VIBRATE) {
            Toast.makeText(this,
                    "Now in Vibrate Mode",
                    // customized Toast with bold and style etc.
                    //HtmlCompat.fromHtml("<big><b>exciting</b></big><small><i>and cool</i></small>text<br>"),
                    Toast.LENGTH_SHORT).show();
        } else if (mod == AudioManager.RINGER_MODE_NORMAL) {
            Toast.makeText(this, "Now in Ringing Mode", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Now in Silent Mode", Toast.LENGTH_SHORT).show();
        }
    }

    public void onRing(View v) {
        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
        Toast.makeText(this,
                Html.fromHtml("Set to <big><b>Ringer Mode</b></big"),
                Toast.LENGTH_LONG).show();
    }

    // invoking onSilent without DND permission will crash the application
    //
    public void onSilent(View v) {
        try {
            myAudioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(this,
                //"Set to Silent Mode",
                Html.fromHtml("Set to <big><b>Silent Mode</b></big"),
                Toast.LENGTH_LONG).show();
    }

    public void onVibrate(View v) {
        myAudioManager.setRingerMode(AudioManager.RINGER_MODE_VIBRATE);
        Toast.makeText(this,
                Html.fromHtml("Set to <big><b>Vibrate Mode</b></big"),
                Toast.LENGTH_LONG).show();
    }
}
