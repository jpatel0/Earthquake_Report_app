package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Intent;
import android.content.Loader;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<quakes>>{

    public static final String LOG_TAG = EarthquakeActivity.class.getName();
    ArrayList<quakes> earthquakes;
    ListView earthquakeListView;
    custom_adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        getLoaderManager().initLoader(0,null,this);

        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        mAdapter = new custom_adapter(this, new ArrayList<quakes>());

        earthquakeListView.setAdapter(mAdapter);

        earthquakeListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                quakes currItem=(quakes) adapterView.getItemAtPosition(i);
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse(currItem.getUrl()));
                startActivity(intent);
            }
        });

    }


    @Override
    public Loader<List<quakes>> onCreateLoader(int i, Bundle bundle) {
        QuakeAsyncLoader backg=new QuakeAsyncLoader(getApplicationContext(), QueryUtils.USGS_URL);
        return backg;
    }

    @Override
    public void onLoadFinished(Loader<List<quakes>> loader, List<quakes> data) {
        mAdapter.clear();

        if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<quakes>> loader) {
            loader.reset();
    }

}
