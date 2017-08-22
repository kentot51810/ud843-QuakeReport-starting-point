package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Kenn Nacion on 013, 13 Aug 2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {

    private final String TAG = EarthquakeAdapter.class.getSimpleName();
    private static final String LOCATION_SEPARATOR = " of ";

    public EarthquakeAdapter(Context context, ArrayList<Earthquake> word) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, word);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        // Check if the view is being reused. if not then inflate it.
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        Earthquake currentEarthquake = getItem(position);
        TextView magnitude = (TextView) view.findViewById(R.id.magnitude);
        magnitude.setText(formatMagnitude(currentEarthquake.getMagnitude()));

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) magnitude.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getMagnitude());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);


        //Split the String into two different Strings.
        String originalLocation = currentEarthquake.getPlace();

        String primaryLocation;
        String locationOffset;

        if (originalLocation.contains(LOCATION_SEPARATOR)) {
            String[] parts = originalLocation.split(LOCATION_SEPARATOR);
            locationOffset = parts[0] + LOCATION_SEPARATOR;
            primaryLocation = parts[1];
        } else {
            locationOffset = getContext().getString(R.string.near_the);
            primaryLocation = originalLocation;
        }
        TextView offset = (TextView) view.findViewById(R.id.location_offset);
        offset.setText(locationOffset);
        TextView primary = (TextView) view.findViewById(R.id.primary_location);
        primary.setText(primaryLocation);

        TextView date = (TextView) view.findViewById(R.id.date);
        date.setText(currentEarthquake.getDate());

        TextView time = (TextView) view.findViewById(R.id.time);
        time.setText(currentEarthquake.getTime());
        return view;
    }

    private String formatMagnitude(double magnitude) {
        // Format the magnitude value to only exceeds to tenths place digit.
        DecimalFormat decimalFormat = new DecimalFormat("0.0");
        return decimalFormat.format(magnitude);
    }

    private int getMagnitudeColor(double magnitude) {
        int index = 0;
        int[] colorBackground = {R.color.magnitude1, R.color.magnitude2, R.color.magnitude3
                , R.color.magnitude4, R.color.magnitude5, R.color.magnitude6
                , R.color.magnitude7, R.color.magnitude8, R.color.magnitude9, R.color.magnitude10plus};

        int magnitudeFloor = (int) Math.floor(magnitude);

        int i = 1;
        while(true){

            if (magnitudeFloor <= i) {
                index = i;
                break;
            } else {
                i++;
            }
        }

        return ContextCompat.getColor(getContext(), colorBackground[index]);

    }
}
