package com.example.android.quakereport;

/**
 * Created by Kenn Nacion on 013, 13 Aug 2017.
 */

public class Earthquake {
    /**
     * Initialize Global Variables.
     */
    private String place;
    private double magnitude;
    private String date;
    private String time;
    private String url;

    /**
     * Constructs a new {@link Earthquake} object.
     *
     * @param magnitude magnitude an integer value that represents how strong the earthquake was.
     * @param place place is the location of that said earthquake.
     * @param date date is the time provided that earthquake occur.
     */
    public Earthquake(double magnitude, String place, String date, String time, String url) {
        this.place = place;
        this.magnitude = magnitude;
        this.date = date;
        this.time = time;
        this.url = url;

    }

    public String getUrl() {
        return url;
    }

    /**
     * @return returns a string of place.
     */
    public String getPlace() {
        return place;
    }

    /**
     * @return returns the time as a String (i.e. 1:30 pm)
     */
    public String getTime() {
        return time;
    }

    /**
     * @return returns a double value of the magnitude
     */
    public double getMagnitude() {
        return magnitude;
    }

    /**
     * @return returns a String of date.
     */
    public String getDate() {
        return date;
    }
}
