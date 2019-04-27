package com.example.astronomer;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

public class EventsActivity extends AppCompatActivity {

    protected static ArrayList<String> events = new ArrayList<String>();
    private void initModel(){
        events.clear();
        events.add("Lunar Eclipses");
        events.add("Solar Eclipses");
        events.add("Solar Transits");
        events.add("Comets");
        events.add("Moon Phases");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        initModel();

        EventArrayAdapter<String> dayAdapter =
                new EventArrayAdapter<>(this,
                        android.R.layout.simple_list_item_1,
                        android.R.id.text1,
                        events);

        final ListView eventLV = findViewById(R.id.eventLV);
        eventLV.setAdapter(dayAdapter);

        eventLV.setOnItemClickListener(new ListView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                swapToSettings(view, position);
            }
        });
    }

    public void swapToSettings(View v, int position){
        Intent swap = new Intent(this, SettingsActivity.class);
        swap.putExtra("eventName", events.get(position));
        startActivityForResult(swap,1 );
    }

    public void onActivityResult(int resultcode, int errorcode, Intent ini){

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
