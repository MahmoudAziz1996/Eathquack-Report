package com.example.aziz.earth;

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

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    List<Earthquake> mEarthquakes;

    public EarthquakeAdapter(@NonNull Context context, @NonNull List<Earthquake> earthquakes) {
        super(context, 0, earthquakes);

        mEarthquakes = earthquakes;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View vv=convertView;
        Earthquake current = mEarthquakes.get(position);

        if(vv == null) {
            vv = LayoutInflater.from(getContext()).inflate(R.layout.custom, parent, false);
        }

        TextView tvMag = (TextView) vv.findViewById(R.id.magnitude);
        tvMag.setText(current.getMagnitude());

        String []fullLocation = split(current.getLocation());


        TextView tvPlace = (TextView) vv.findViewById(R.id.location);
        tvPlace.setText(fullLocation[1]);

        TextView tvOffset = (TextView) vv.findViewById(R.id.textViewoffset);
        tvOffset.setText(fullLocation[0]+" of");

        Date date = new Date(current.getTimeInMilliSeconds());

        TextView tvDate = (TextView) vv.findViewById(R.id.date);
        tvDate.setText(formatDate(date));

        TextView tvTime = (TextView) vv.findViewById(R.id.time);
        tvTime.setText(formatTime(date));


        GradientDrawable magnitudeCircle = (GradientDrawable) tvMag.getBackground();
        magnitudeCircle.setColor(getMagnitudeColor(current.getMagnitude()));


       return vv;
    }
    public String formatTime(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("h: mm a");
        return  dateFormat.format(date);
    }

    private String formatDate(Date date ) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM DD, yyyy");
        return dateFormat.format(date);
    }
    private String [] split(String string) {
        String [] array = {};
        if(string.contains("of")) {
            array = string.split("of");
            return array;
        } else {
            array = new String[]{"Near The", string};
            return array;
        }
    }
    private int getMagnitudeColor(String magnitude) {

            int magnitudeColorResourceId;
            int magnitudeFloor =(int) Double.parseDouble(magnitude);
            switch (magnitudeFloor) {
                case 0:
                case 1:
                    magnitudeColorResourceId = R.color.magnitude1;
                    break;
                case 2:
                    magnitudeColorResourceId = R.color.magnitude2;
                    break;
                case 3:
                    magnitudeColorResourceId = R.color.magnitude3;
                    break;
                case 4:
                    magnitudeColorResourceId = R.color.magnitude4;
                    break;
                case 5:
                    magnitudeColorResourceId = R.color.magnitude5;
                    break;
                case 6:
                    magnitudeColorResourceId = R.color.magnitude6;
                    break;
                case 7:
                    magnitudeColorResourceId = R.color.magnitude7;
                    break;
                case 8:
                    magnitudeColorResourceId = R.color.magnitude8;
                    break;
                case 9:
                    magnitudeColorResourceId = R.color.magnitude9;
                    break;
                default:
                    magnitudeColorResourceId = R.color.magnitude10plus;
                    break;
            }
            return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
        }
}
