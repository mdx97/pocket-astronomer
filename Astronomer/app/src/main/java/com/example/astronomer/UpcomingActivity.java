package com.example.astronomer;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.toolbox.JsonArrayRequest;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UpcomingActivity extends AppCompatActivity {
    private static final String[] CLASSIFICATIONS = {"Lunar Eclipse", "Solar Eclipse", "Transit", "Comet"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upcoming);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);

        ListView upcomingLV = findViewById(R.id.upcomingLV);
        List<AstroEvent> events = new ArrayList<>();
        ArrayAdapter<AstroEvent> adapter = new AstroAdapter(this, android.R.layout.simple_list_item_2, android.R.id.text1, events);
        upcomingLV.setAdapter(adapter);

        fetchUpcomingEvents(adapter, events);
    }

    public void fetchUpcomingEvents(ArrayAdapter<AstroEvent> adapter, List<AstroEvent> events) {
        AstronomerApp app = (AstronomerApp) getApplicationContext();
        SimpleDateFormat apiDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
        SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        apiDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        ymdFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String startDate = ymdFormat.format(new Date(/* now */));


        for (String classification : CLASSIFICATIONS) {
            String url = AstronomerApp.buildUrl("https://pocket-astronomer-api.herokuapp.com/events?start_date=%s&end_date=2099-01-01&classification=%s",
                    startDate, classification);
            app.getRequestQueue().add(new JsonArrayRequest(url,
                jsonArray -> {
                    try {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            AstroEvent event = new AstroEvent();
                            event.datetime = apiDateFormat.parse(jsonObject.getString("datetime"));
                            event.name = jsonObject.getString("name");
                            events.add(event);
                        }
                        Collections.sort(events);
                        adapter.notifyDataSetChanged();
                    }
                    catch (ParseException | JSONException e) {
                        Toast.makeText(this, "Error parsing upcoming " + classification + ": " + e, Toast.LENGTH_LONG).show();
                    }
                },
                e -> {
                    Toast.makeText(this, "Error fetching upcoming " + classification + ": " + e, Toast.LENGTH_LONG).show();
                }
            ));
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

    private static class AstroEvent implements Comparable<AstroEvent> {
        String name;
        Date datetime;

        @Override
        public int compareTo(AstroEvent o) {
            return datetime.compareTo(o.datetime);
        }

        @Override
        public String toString() {
            return name;
        }
    }

    private static class AstroAdapter extends ArrayAdapter<AstroEvent> {
        private final DateFormat dateFormat = DateFormat.getDateInstance(); //NOTE; local timezone
        public AstroAdapter(Context context, int resource, int textViewResourceId, List<AstroEvent> objects) {
            super(context, resource, textViewResourceId, objects);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            AstroEvent event = getItem(position);
            View root = super.getView(position, convertView, parent);

            if (event != null) {
                TextView text2 = root.findViewById(android.R.id.text2);
                text2.setText(dateFormat.format(event.datetime));
            }

            return root;
        }
    }
}
