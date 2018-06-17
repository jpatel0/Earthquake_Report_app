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

import java.util.ArrayList;
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

        // Get the {@link AndroidFlavor} object located at this position in the list
        quakes currentquakeobj = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView magTextView = (TextView) listItemView.findViewById(R.id.magid);
        // Get the version name from the current AndroidFlavor object and
        // set this text on the name TextView
        magTextView.setText(String.valueOf(currentquakeobj.getMag()));

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView placeTextView = (TextView) listItemView.findViewById(R.id.placeid);
        // Get the version number from the current AndroidFlavor object and
        // set this text on the number TextView
        placeTextView.setText(currentquakeobj.getPlace());

        // Find the ImageView in the list_item.xml layout with the ID list_item_icon
        TextView dateTextView = (TextView) listItemView.findViewById(R.id.dateid);
        // Get the image resource ID from the current AndroidFlavor object and
        // set the image to iconView
        dateTextView.setText(currentquakeobj.getDate());

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
