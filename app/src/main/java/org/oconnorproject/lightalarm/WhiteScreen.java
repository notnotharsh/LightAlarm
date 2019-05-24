package org.oconnorproject.lightalarm;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class WhiteScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.white_screen);
    }
    public void otherActivity(android.view.View view) {
        Intent startIntent = new Intent();
        startIntent.setClassName(this.getApplicationContext(), MainActivity.class.getName());
        startIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.getApplicationContext().startActivity(startIntent);
    }
}
