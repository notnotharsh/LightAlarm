package org.oconnorproject.lightalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Environment;
import android.os.Vibrator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BR extends BroadcastReceiver {
    private MediaPlayer mp;
    @Override
    public void onReceive(Context context, Intent intent) {
        mp = MediaPlayer.create(context, R.raw.alarm);
        try {
            File path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath(), "LightAlarmLogs");
            if (!(path.exists())) {
                path.mkdir();
            }
            File log = new File(path, "rickroll.txt");
            String stringUnmodified = (new BufferedReader(new FileReader(log))).readLine();
            if (stringUnmodified.equals("true")) {
                mp.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            File path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath(), "LightAlarmLogs");
            if (!(path.exists())) {
                path.mkdir();
            }
            File log = new File(path, "vibrate.txt");
            String stringUnmodified = (new BufferedReader(new FileReader(log))).readLine();
            if (stringUnmodified.equals("true")) {
                Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
                v.vibrate(new long[] {0, 500, 110, 500, 110, 450, 110, 200, 110, 170, 40, 450, 110, 200, 110, 170, 40, 500}, 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent startIntent = new Intent();
        startIntent.setClassName(context, WhiteScreen.class.getName());
        startIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        context.startActivity(startIntent);
    }
}
