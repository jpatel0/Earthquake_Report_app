package com.example.android.quakereport;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import android.widget.TextView;


import java.util.ArrayList;


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


        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagColor(currentquakeobj.getMag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


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

    private int getMagColor(double mag) {
        switch ((int)Math.floor(mag)) {
            case 0: 
            case 1:
                return ContextCompat.getColor(getContext(), R.color.magnitude1);
                
            case 2:
                return ContextCompat.getColor(getContext(), R.color.magnitude2);
                
            case 3:
                return ContextCompat.getColor(getContext(), R.color.magnitude3);
                
            case 4:
                return ContextCompat.getColor(getContext(), R.color.magnitude4);
                
            case 5:
                return ContextCompat.getColor(getContext(), R.color.magnitude5);
                
            case 6:
                return ContextCompat.getColor(getContext(), R.color.magnitude6);
                
            case 7:
                return ContextCompat.getColor(getContext(), R.color.magnitude7);
                
            case 8:
                return ContextCompat.getColor(getContext(), R.color.magnitude8);
                
            case 9:
                return ContextCompat.getColor(getContext(), R.color.magnitude9);
                
                
            default:
                return ContextCompat.getColor(getContext(), R.color.magnitude10plus);
        }


    }
}
