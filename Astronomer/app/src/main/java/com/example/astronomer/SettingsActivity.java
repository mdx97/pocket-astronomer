package com.example.astronomer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Intent ini = getIntent();
        String eventName = ini.getStringExtra("eventName");
        TextView titleTV = findViewById(R.id.titleTV);
        titleTV.setText("Settings: " + eventName);
    }

    public void returnAction(View v) {
        Intent ini = getIntent();
        setResult(0, ini);
        finish();
    }
}
