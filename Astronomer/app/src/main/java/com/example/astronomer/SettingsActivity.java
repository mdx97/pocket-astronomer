package com.example.astronomer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;

import java.util.Objects;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Intent ini = getIntent();
        String eventName = ini.getStringExtra("eventName");

        setContentView(R.layout.activity_settings);
        ActionBar actionBar = Objects.requireNonNull(getSupportActionBar());
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle(getString(R.string.label_settings, eventName));


        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        final SharedPreferences.Editor edit = sp.edit();
        final Switch hourNoticeSwitch = findViewById(R.id.hourNoticeSwitch);
        hourNoticeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String strdata = ini.getStringExtra("eventName");
                strdata = strdata + "hourNotice";
                if(isChecked){
                    edit.putBoolean(strdata, true);
                    Log.d("Storage", "Applied hour status");
                }
                else {
                    edit.putBoolean(strdata, false);
                    Log.d("Storage", "Disabled hour status");

                }
                edit.apply();
            }
        });

        Switch dayNoticeSwitch = findViewById(R.id.dayNoticeSwitch);
        dayNoticeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String strdata = ini.getStringExtra("eventName");
                strdata = strdata + "dayNotice";
                if(isChecked){
                    edit.putBoolean(strdata, true);
                    Log.d("Storage", "Applied day status");
                }
                else {
                    edit.putBoolean(strdata, false);
                    Log.d("Storage", "Disabled day status");

                }
                edit.apply();
            }
        });

        Switch weekNoticeSwitch = findViewById(R.id.weekNoticeSwitch);
        weekNoticeSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String strdata = ini.getStringExtra("eventName");
                strdata = strdata + "weekNotice";
                if(isChecked){
                    edit.putBoolean(strdata, true);
                    Log.d("Storage", "Applied week status");
                }
                else {
                    edit.putBoolean(strdata, false);
                    Log.d("Storage", "Disabled week status");

                }
                edit.apply();
            }
        });

        Switch notificationsSwitch = findViewById(R.id.notificationsSwitch);
        notificationsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String strdata = ini.getStringExtra("eventName");
                strdata = strdata + "notiNotice";
                if(isChecked){
                    edit.putBoolean(strdata, true);
                    Log.d("Storage", "Applied notification status");
                }
                else {
                    edit.putBoolean(strdata, false);
                    Log.d("Storage", "Disabled notification status");

                }
                edit.apply();
            }
        });

        Switch trackSwitch = findViewById(R.id.trackVisibleOnlySwitch);
        trackSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                String strdata = ini.getStringExtra("eventName");
                strdata = strdata + "trackNotice";
                if(isChecked){
                    edit.putBoolean(strdata, true);
                    Log.d("Storage", "Applied tracker status");
                }
                else {
                    edit.putBoolean(strdata, false);
                    Log.d("Storage", "Disabled tracker status");

                }
                edit.apply();
            }
        });

        String strData = ini.getStringExtra("eventName");
        boolean hr = sp.getBoolean(strData + "hourNotice", false);
        boolean day = sp.getBoolean(strData + "dayNotice", false);
        boolean week = sp.getBoolean(strData + "weekNotice", false);
        boolean noti = sp.getBoolean(strData + "notiNotice", false);
        boolean track = sp.getBoolean(strData + "trackNotice", false);
        hourNoticeSwitch.setChecked(hr);
        dayNoticeSwitch.setChecked(day);
        weekNoticeSwitch.setChecked(week);
        notificationsSwitch.setChecked(noti);
        trackSwitch.setChecked(track);
    }

    public void noticesToggle(View v){
        Switch hourNoticeSwitch = findViewById(R.id.hourNoticeSwitch);
        Switch dayNoticeSwitch = findViewById(R.id.dayNoticeSwitch);
        Switch weekNoticeSwitch = findViewById(R.id.weekNoticeSwitch);
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        boolean hr = sp.getBoolean("hourNotice", false);
        boolean day = sp.getBoolean("dayNotice", false);
        boolean week = sp.getBoolean("weekNotice", false);
        hourNoticeSwitch.setChecked(hr);
        dayNoticeSwitch.setChecked(day);
        weekNoticeSwitch.setChecked(week);


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
        else {
            return super.onOptionsItemSelected(item);
        }
    }
}
