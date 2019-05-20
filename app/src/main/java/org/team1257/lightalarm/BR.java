package org.team1257.lightalarm;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.widget.Switch;

public class BR extends BroadcastReceiver {
    private MediaPlayer mp;
    @Override
    public void onReceive(Context context, Intent intent) {
        mp = MediaPlayer.create(context, R.raw.alarm);
        if (((Switch) ((Activity) context).findViewById(R.id.rickroll)).isChecked()) {
            mp.start();
        }
        if (((Switch) ((Activity) context).findViewById(R.id.vibrate)).isChecked()) {
            Vibrator v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
            v.vibrate(new long[] {0, 500, 110, 500, 110, 450, 110, 200, 110, 170, 40, 450, 110, 200, 110, 170, 40, 500}, -1);
        }
        Intent startIntent = new Intent();
        startIntent.setClassName(context, WhiteScreen.class.getName());
        startIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        context.startActivity(startIntent);
    }
}
