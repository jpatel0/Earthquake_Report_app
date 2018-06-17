package com.example.android.quakereport;

import java.util.Date;

public class quakes {

    private double mag;

    private String place;

    private String date;

    private String time;

    public quakes(double m,String p,String d,String t) {
        mag=m;
        place=p;
        date=d;
        time=t;
    }

    public double getMag() {
        return mag;
    }

    public String getPlace() {
        return place;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
