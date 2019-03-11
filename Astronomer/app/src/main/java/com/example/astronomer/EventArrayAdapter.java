package com.example.astronomer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class EventArrayAdapter<String> extends ArrayAdapter<String> {
    public EventArrayAdapter(@NonNull Context context, int resource, int textViewResourceId, @NonNull List<String> objects) {
        super(context, resource, textViewResourceId, objects);
    }

    @NonNull
    @Override
    public View getView(int position,  @Nullable View convertView,  @NonNull ViewGroup parent) {
        View v =  super.getView(position, convertView, parent);

        //TextView hoursTV = v.findViewById(android.R.id.text2);
        //hoursTV.setText(EventsActivity.toWork.get(position).toString());

        return v;
    }
}
