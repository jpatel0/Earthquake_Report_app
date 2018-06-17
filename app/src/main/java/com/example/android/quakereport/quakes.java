package com.example.android.quakereport;

import java.util.Date;

public class quakes {

    private double mag;

    private String place;

    private String date;

    public quakes(double m,String p,String d) {
        mag=m;
        place=p;
        date=d;
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
}
