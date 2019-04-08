package com.example.astronomer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent ini = getIntent();
        String eventName = ini.getStringExtra("eventName");

        setContentView(R.layout.activity_settings);
        ActionBar actionBar = Objects.requireNonNull(getSupportActionBar());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.label_settings, eventName));
    }

    public void noticesToggle(View v){
        Switch hourNoticeSwitch = findViewById(R.id.hourNoticeSwitch);
        Switch dayNoticeSwitch = findViewById(R.id.dayNoticeSwitch);
        Switch weekNoticeSwitch = findViewById(R.id.weekNoticeSwitch);
        hourNoticeSwitch.setChecked(false);
        dayNoticeSwitch.setChecked(false);
        weekNoticeSwitch.setChecked(false);
    }

    public void subNoticesToggle(View v){
        Switch notificationsSwitch = findViewById(R.id.notificationsSwitch);
        notificationsSwitch.setChecked(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            //Handling for back button inside ActionBar
            Intent ini = getIntent();
            setResult(0, ini);
            finish();
            return true;
        }
        else
            return super.onOptionsItemSelected(item);
    }
}
