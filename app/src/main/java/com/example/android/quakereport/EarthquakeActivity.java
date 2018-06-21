package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<quakes>>{

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    private custom_adapter mAdapter;
    private TextView mEmpty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        View pb= findViewById(R.id.progressBar);
        mEmpty=(TextView) findViewById(R.id.textView);
        ListView earthquakeListView = (ListView) findViewById(R.id.list);
        mAdapter = new custom_adapter(this, new ArrayList<quakes>());

        earthquakeListView.setEmptyView(mEmpty);
        earthquakeListView.setAdapter(mAdapter);



        ConnectivityManager connectivityMananager=(ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netStat=connectivityMananager.getActiveNetworkInfo();

        if(netStat!=null && netStat.isConnected()){
            LoaderManager ldmanager=getLoaderManager();
            ldmanager.initLoader(0, null, this);
        }

        else {
            pb.setVisibility(View.GONE);
            mEmpty.setText("No Internet Connection");
        }
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
        QuakeAsyncLoader getData=new QuakeAsyncLoader(getApplicationContext(), QueryUtils.USGS_URL);
        return getData;
    }

    @Override
    public void onLoadFinished(Loader<List<quakes>> loader, List<quakes> data) {
        //mAdapter.clear();
        View pb= findViewById(R.id.progressBar);
        pb.setVisibility(View.GONE);
        mEmpty.setText("No earthquakes");

       if (data != null && !data.isEmpty()) {
            mAdapter.addAll(data);
        }

    }

    @Override
    public void onLoaderReset(Loader<List<quakes>> loader) {
            mAdapter.clear();
    }

}
