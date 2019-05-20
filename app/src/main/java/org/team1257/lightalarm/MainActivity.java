package org.team1257.lightalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import java.lang.Math;

public class MainActivity extends AppCompatActivity {
    private AlarmManager alarmManager;
    private Intent intent;
    private PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        intent = new Intent(this, BR.class);
        pendingIntent = PendingIntent.getBroadcast(this.getApplicationContext(), 234324243, intent, 0);
    }
    public void startAlert(android.view.View view) {
        int hour = Integer.parseInt(((EditText) findViewById(R.id.hour)).getText().toString());
        int minute = Integer.parseInt(((EditText) findViewById(R.id.minute)).getText().toString());
        long millis = 86400000 * ((long) (Math.ceil(System.currentTimeMillis() / 86400000))) + 3600000 * hour + 60000 * minute;
        alarmManager.set(AlarmManager.RTC_WAKEUP, millis, pendingIntent);
    }
    public void cancel(android.view.View view) {
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
    }
}
