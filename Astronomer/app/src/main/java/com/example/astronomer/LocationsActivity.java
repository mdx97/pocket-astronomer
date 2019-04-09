package com.example.astronomer;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.Objects;

public class LocationsActivity extends AppCompatActivity implements LocationListener {

    private static Location loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locations);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        if (loc != null){
            TextView latitudeET = findViewById(R.id.latitudeET);
            TextView longitudeET = findViewById(R.id.longitudeET);
            latitudeET.setText(""+(Math.round(1000.0 * loc.getLatitude())/1000.0));
            longitudeET.setText(""+(Math.round(1000.0 * loc.getLongitude())/1000.0));
        }
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

    public static void setLocation(double lat, double lon) {
        if (loc == null){
            loc = new Location("");
        }
        loc.setLatitude(lat);
        loc.setLongitude(lon);
    }

    public static Location getLocation() {
        if (loc == null){
            setLocation(0.0, 0.0);
        }
        return loc;
    }

    public void setLocationBtn(View v){
        TextView latitudeET = findViewById(R.id.latitudeET);
        TextView longitudeET = findViewById(R.id.longitudeET);
        double newLat = new Double(latitudeET.getText().toString());
        double newLon = new Double(longitudeET.getText().toString());
        newLat = Math.round(newLat * 1000.0) / 1000.0;
        newLon = Math.round(newLon * 1000.0) / 1000.0;
        setLocation(newLat, newLon);
    }

    public void detectLocationBtn(View v){
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},0);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION},1);
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.INTERNET},1);
        LocationManager locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        try {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        } catch (SecurityException se){
            TextView latitudeET = findViewById(R.id.latitudeET);
            TextView longitudeET = findViewById(R.id.longitudeET);
            latitudeET.setText("0.0");
            longitudeET.setText("0.0");
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        loc = location;
        TextView latitudeET = findViewById(R.id.latitudeET);
        TextView longitudeET = findViewById(R.id.longitudeET);
        latitudeET.setText(""+(Math.round(1000.0 * location.getLatitude())/1000.0));
        longitudeET.setText(""+(Math.round(1000.0 * location.getLongitude())/1000.0));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderDisabled(String provider) {
    }

    @Override
    public void onProviderEnabled(String provider) {
    }

    /*
    private void askPermissions() {
        int selfPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (selfPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            // Permission has already been granted
        }
    }
    */



}
