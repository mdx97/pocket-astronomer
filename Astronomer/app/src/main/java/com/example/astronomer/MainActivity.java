package com.example.astronomer;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.label_main);
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

    public void onActivityResult(int resultcode, int errorcode, Intent ini){

    }
}
