package com.example.android.quakereport;

import android.util.Log;

import java.text.DecimalFormat;
import java.util.Date;

public class quakes {

    private double mag;

    private String offset;

    private String place;

    private String location;

    private String date;

    private String time;

    public quakes(double m,String p,String d,String t) {
        DecimalFormat df=new DecimalFormat("0.0");
        mag=Double.parseDouble(df.format(m));
        place=p;
        date=d;
        time=t;
        offset=getOffset();
    }

    public double getMag() {
        return mag;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }


    public String getOffset() {
        if(place.contains("km")){
            offset=place.substring(0,place.indexOf("of"));
            offset+="of";
            location=place.substring((place.indexOf("of")+3),place.length());
            Log.i("offset value:",offset);
            Log.i("place value:",location);
        }
        else {
            offset="Near to ";
            location=place;
        }

        return offset;
    }


}
