package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

public class QuakeAsyncLoader extends AsyncTaskLoader<List<quakes>> {

    private String[] urls;
    public QuakeAsyncLoader(Context context,String... u) {
        super(context);
        urls=u;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<quakes> loadInBackground() {
        if (urls.length < 1 || urls[0] == null) {
            return null;
        }

        List<quakes> result = QueryUtils.fetchEarthquakeData(urls[0]);
        return result;
    }
}
