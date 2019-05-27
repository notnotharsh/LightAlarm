package org.oconnorproject.lightalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Switch;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.Math;

public class MainActivity extends AppCompatActivity {
    private AlarmManager alarmManager;
    private Intent intent;
    private PendingIntent pendingIntent;
    private final long TIME_ZONE_CONST = 14400000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cancel(findViewById(R.id.cancel));
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        intent = new Intent(this, BR.class);
        pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        try {
            settings(findViewById(R.id.vibrate));
            settings(findViewById(R.id.rickroll));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void startAlert(android.view.View view) {
        int hour = Integer.parseInt(((EditText) findViewById(R.id.hour)).getText().toString());
        int minute = Integer.parseInt(((EditText) findViewById(R.id.minute)).getText().toString());
        long nowMillis = System.currentTimeMillis() - TIME_ZONE_CONST;
        long millis = (nowMillis % 86400000 > 3600000 * hour + 60000 * minute) ? 86400000 * ((long) (Math.ceil(nowMillis / 86400000.0))) + 3600000 * hour + 60000 * minute : 86400000 * ((long) (Math.floor(nowMillis / 86400000.0))) + 3600000 * hour + 60000 * minute + TIME_ZONE_CONST;
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, millis, 60000, pendingIntent);
    }
    public void cancel(android.view.View view) {
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
    }
    public void settings(android.view.View view) throws IOException {
        boolean iC = ((Switch) view).isChecked();
        File path = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath(), "LightAlarmLogs");
        if (!(path.exists())) {
            path.mkdir();
        }
        String filename = view.getTag().toString() + ".txt";
        File log = new File(path, filename);
        FileOutputStream out = new FileOutputStream(log, false);
        OutputStreamWriter writer = new OutputStreamWriter(out);
        writer.write(Boolean.toString(iC));
        writer.flush();
        writer.close();
    }
}
