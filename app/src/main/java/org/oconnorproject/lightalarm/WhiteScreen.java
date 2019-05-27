package org.oconnorproject.lightalarm;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.provider.Settings.System;

public class WhiteScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.white_screen);
        if ((this.getIntent() != null) && (this.getIntent().getExtras().getString("where").equals("BR"))) {
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON, WindowManager.LayoutParams.FLAG_FULLSCREEN | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD | WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
            System.putInt(getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            System.putInt(getContentResolver(), System.SCREEN_BRIGHTNESS, 255);
            WindowManager.LayoutParams layoutPars = getWindow().getAttributes();
            layoutPars.screenBrightness = 1;
            getWindow().setAttributes(layoutPars);
            scheduleChanges();
        }
    }
    public void otherActivity(android.view.View view) {
        Intent startIntent = new Intent();
        startIntent.setClassName(this.getApplicationContext(), MainActivity.class.getName());
        startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.getApplicationContext().startActivity(startIntent);
    }
    public void changeBackground() {
        View view = findViewById(R.id.mainScreen);
        if (((ColorDrawable) view.getBackground()).getColor() == getResources().getColor(R.color.colorAccent)) {
            view.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        } else {
            view.setBackgroundColor(getResources().getColor(R.color.colorAccent));
        }
    }
    public void scheduleChanges() {
        new CountDownTimer(60000, 100) {
            public void onTick(long millisUntilFinished) {
                changeBackground();
            }
            public void onFinish() {

            }
        }.start();
    }
}
