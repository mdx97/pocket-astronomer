package com.example.astronomer;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.toolbox.StringRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.label_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        long now = System.currentTimeMillis();
        TextView nextEventTV = findViewById(R.id.nextEventTV);

        viewModel.observeSunEvents(this, sunEvents -> {
            SimpleDateFormat smallTime = new SimpleDateFormat("hh:mm a", Locale.getDefault());

            if (sunEvents == null) {
                nextEventTV.setText(null);
                return;
            }
            boolean sunsetIsNext;

            if (sunEvents.sunrise != null && sunEvents.sunrise.getTime() > now) {
                sunsetIsNext = sunEvents.sunset != null && sunEvents.sunrise.getTime() > sunEvents.sunset.getTime() && sunEvents.sunset.getTime() > now;
            }
            else if (sunEvents.sunset != null && sunEvents.sunset.getTime() > now) {
                //sunset is in the future
                sunsetIsNext = true;
            }
            else {
                Log.e(TAG, "Both events returned are in the past!");
                return;
            }

            if (sunsetIsNext)
                nextEventTV.setText(getString(R.string.sunset_in, formatTimeInterval(now, sunEvents.sunset.getTime()), smallTime.format(sunEvents.sunset)));
            else
                nextEventTV.setText(getString(R.string.sunrise_in, formatTimeInterval(now, sunEvents.sunrise.getTime()), smallTime.format(sunEvents.sunrise)));
        });

        if (viewModel.isDataStale()) {
            fetchSunriseSunset(new Date(now), 40.3589785F, -94.883186F);
        }
    }

    public void swapToUpcoming(View v){
        Intent swap = new Intent(this, UpcomingActivity.class);
        startActivityForResult(swap,1 );
    }

    public void swapToEvents(View v){
        Intent swap = new Intent(this, EventsActivity.class);
        startActivityForResult(swap,1 );
    }

    public void swapToLocations(View v){
        Intent swap = new Intent(this, LocationsActivity.class);
        startActivityForResult(swap,1 );
    }

    public void onActivityResult(int resultcode, int errorcode, Intent ini) {

    }

    private void fetchSunriseSunset(Date date, float lat, float lon) {
        AstronomerApp app = (AstronomerApp) getApplication();
        TimeZone utc = TimeZone.getTimeZone("UTC");
        SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US); //Timezone is local!
        SimpleDateFormat ymdUTCFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        SimpleDateFormat apiTimeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss a", Locale.US);
        apiTimeFormat.setTimeZone(utc);
        ymdUTCFormat.setTimeZone(utc);

        String dateStr = ymdFormat.format(date);
        String utcDateStr = ymdUTCFormat.format(date);
        String sunriseUrl = AstronomerApp.buildUrl("https://pocket-astronomer-api.herokuapp.com/sunrise?date=%s&lat=%s&lon=%s", dateStr, lat, lon);
        String sunsetUrl = AstronomerApp.buildUrl("https://pocket-astronomer-api.herokuapp.com/sunset?date=%s&lat=%s&lon=%s", dateStr, lat, lon);

        app.getRequestQueue().add(new StringRequest(sunriseUrl,
            response -> {
                try {
                    viewModel.setSunrise(apiTimeFormat.parse(utcDateStr + " " + response));
                } catch (ParseException e) {
                    Toast.makeText(this, "Error parsing sunrise: " + e, Toast.LENGTH_LONG).show();
                }
            },
            e -> {
                Toast.makeText(this, "Error fetching sunrise: " + e, Toast.LENGTH_LONG).show();
            }));
        app.getRequestQueue().add(new StringRequest(sunsetUrl,
            response -> {
                try {
                    viewModel.setSunset(apiTimeFormat.parse(utcDateStr + " " + response));
                } catch (ParseException e) {
                    Toast.makeText(this, "Error parsing sunset: " + e, Toast.LENGTH_LONG).show();
                }
            },
            e -> {
                Toast.makeText(this, "Error fetching sunset: " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }));
    }
    
    private String formatTimeInterval(long initialTime, long finalTime) {
        long deltaTime = finalTime - initialTime;
        String directionalityFlag = getString(R.string.from_now);
        if (deltaTime < 0) {
            directionalityFlag = getString(R.string.ago);
            deltaTime = -deltaTime;
        }
        deltaTime /= 1000; //truncate to seconds
        int ss = (int) (deltaTime % 60);
        deltaTime /= 60; //truncate to minutes
        int mm = (int) (deltaTime % 60);
        deltaTime /= 60; //trucate to hours
        int hh = (int) deltaTime;

        return getString(R.string.interval_format, hh, mm, directionalityFlag);
    }
}
