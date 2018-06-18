package com.example.android.quakereport;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class custom_adapter extends ArrayAdapter<quakes> {


    public custom_adapter(Activity context, ArrayList<quakes> quakesArrayList) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, quakesArrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.custom_listview, parent, false);
        }

        quakes currentquakeobj = getItem(position);

        TextView magTextView = (TextView) listItemView.findViewById(R.id.magid);
        magTextView.setText(String.valueOf(currentquakeobj.getMag()));

        TextView offsetTextView = (TextView) listItemView.findViewById(R.id.offsetid);
        offsetTextView.setText(currentquakeobj.getOffset());

        TextView placeTextView = (TextView) listItemView.findViewById(R.id.placeid);
        placeTextView.setText(currentquakeobj.getLocation());

        TextView dateTextView = (TextView) listItemView.findViewById(R.id.dateid);
        dateTextView.setText(currentquakeobj.getDate());

        TextView timeTextView = (TextView) listItemView.findViewById(R.id.timeid);
        timeTextView.setText(currentquakeobj.getTime());

        return listItemView;
    }
}
