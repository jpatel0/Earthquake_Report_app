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

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    ArrayList<quakes> earthquakes;
    ListView earthquakeListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        String url="https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=2016-01-01&endtime=2016-05-02&minfelt=50&minmagnitude=5";
        DownloadTask downloadTask=new DownloadTask();
        downloadTask.execute(url);

    }

    public void updateUI(ArrayList<quakes> earthq){
        custom_adapter adapter = new custom_adapter(this, earthq);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        earthquakeListView.setAdapter(adapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                quakes selectedItem=(quakes) adapterView.getItemAtPosition(i);
                Intent openBrowser=new Intent(Intent.ACTION_VIEW, Uri.parse(selectedItem.getUrl()));
                startActivity(openBrowser);
            }
        });
    }

    private class DownloadTask extends AsyncTask<String,Void,ArrayList<quakes>> {

        @Override
        protected ArrayList<quakes> doInBackground(String... u) {
            URL url=createURL(u[0]);
            String jsonResponse="";
            try {
                jsonResponse = makeHttpRequest(url);
                Log.i("JSON String:",jsonResponse);
            }catch (IOException e){
                e.printStackTrace();
            }
            // Create a fake list of earthquake locations.
            earthquakes = QueryUtils.extractEarthquakes(jsonResponse);


            // Find a reference to the {@link ListView} in the layout
            earthquakeListView = (ListView) findViewById(R.id.list);
            return earthquakes;
            // Create a new {@link ArrayAdapter} of earthquakes
        }

        @Override
        protected void onPostExecute(ArrayList<quakes> earthq) {
           updateUI(earthq);

        }

        private URL createURL(String u){
            URL url=null;
            try {
                url=new URL(u);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return url;
        }

        private String makeHttpRequest(URL url)throws IOException{
            HttpURLConnection httpURLConnection;
            InputStream inputStream;

            try {
                httpURLConnection=(HttpURLConnection) url.openConnection();
                httpURLConnection.setReadTimeout(10000);
                httpURLConnection.setConnectTimeout(15000);
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();

                inputStream=httpURLConnection.getInputStream();
                String jsonResponse=readFromStream(inputStream);
                Log.i("JSON:",jsonResponse);
                return jsonResponse;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        private String readFromStream(InputStream inputStream)throws IOException{
            InputStreamReader inputStreamReader=new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder=new StringBuilder();
            String readLine="";
            try {
                readLine=bufferedReader.readLine();
                while (readLine!=null){
                    stringBuilder.append(readLine);
                    readLine=bufferedReader.readLine();
                }
                return stringBuilder.toString();
            }catch (NullPointerException e){
                e.printStackTrace();
            }
            return null;
        }

    }
}
