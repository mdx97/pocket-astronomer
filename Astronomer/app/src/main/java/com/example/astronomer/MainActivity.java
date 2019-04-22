package com.example.astronomer;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.widget.Toast;
import com.android.volley.toolbox.StringRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.label_main);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        viewModel.observeSunrise(this, sunrise -> {
            Log.i("MainActivity", "Observed change in sunrise");
            //This is where the sunrise widget gets updated in response to network response
        });

        viewModel.observeSunset(this, sunset -> {
            Log.i("MainActivity", "Observed change in sunset");
            //This is where the sunset widget gets updated in response to network response
        });

        if (viewModel.isDataStale()) {
            fetchSunriseSunset(new Date(/* now */), 40.3589785F, -94.883186F);
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
        SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-DD", Locale.US); //Timezone is local!
        SimpleDateFormat apiTimeFormat = new SimpleDateFormat("hh:mm:ss a", Locale.US);
        apiTimeFormat.setTimeZone(utc);

        String dateStr = ymdFormat.format(date);
        String sunriseUrl = String.format("https://pocket-astronomer-api.herokuapp.com/sunrise?date=%s&lat=%s&lon=%s", dateStr, lat, lon);
        String sunsetUrl = String.format("https://pocket-astronomer-api.herokuapp.com/sunset?date=%s&lat=%s&lon=%s", dateStr, lat, lon);

        app.getRequestQueue().add(new StringRequest(sunriseUrl,
            response -> {
                try {
                    viewModel.setSunrise(apiTimeFormat.parse(response));
                } catch (ParseException e) {
                    Toast.makeText(this, "Error parsing sunrise: " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            },
            e -> {
                Toast.makeText(this, "Error fetching sunrise: " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }));
        app.getRequestQueue().add(new StringRequest(sunsetUrl,
            response -> {
                try {
                    viewModel.setSunset(apiTimeFormat.parse(response));
                } catch (ParseException e) {
                    Toast.makeText(this, "Error parsing sunset: " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                }
            },
            e -> {
                Toast.makeText(this, "Error fetching sunset: " + e.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }));
    }
}
