/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        Date d= new Date();
        DateFormat df = new SimpleDateFormat("dd-mm-yyyy");
        String s=(String) df.format(d);


        // Create a fake list of earthquake locations.
        ArrayList<quakes> earthquakes = new ArrayList<quakes>();
        earthquakes.add(new quakes(3.4,"San Francisco", s));
        earthquakes.add(new quakes(4.4,"London",s));
        earthquakes.add(new quakes(5.5,"Tokyo",s));
        earthquakes.add(new quakes(6.6,"Mexico City",s));
        earthquakes.add(new quakes(4.7,"Moscow",s));
        earthquakes.add(new quakes(2.62,"Rio de Janeiro",s));
        earthquakes.add(new quakes(3.8,"Paris",s));

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        custom_adapter adapter = new custom_adapter(this, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);
    }
}
