package com.edwardjones.avengers.community.ui.calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.edwardjones.avengers.community.R;

import java.time.Instant;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class ApptAdapter extends ArrayAdapter<Availability> {

    ArrayList<Instant> times = new ArrayList<>();
    ArrayList<Availability> availabilities = new ArrayList<>();

    public ApptAdapter(Context context, int textViewResourceId, ArrayList<Availability> objects) {
        super(context, textViewResourceId, objects);
        availabilities = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.appt_row, null);
        TextView textView = (TextView) v.findViewById(R.id.personName);
        ImageView imageView = (ImageView) v.findViewById(R.id.apptColor);

        if (availabilities.get(position).getPersonName() != null) {
            textView.setText(availabilities.get(position).getPersonName());
            imageView.setImageResource(R.drawable.busy);
        } else if (availabilities.get(position).getTime() != null) {
            if (availabilities.get(position).getTime().atZone(ZoneOffset.UTC).getHour() < 12)
                textView.setText(Integer.toString(availabilities.get(position).getTime().atZone(ZoneOffset.UTC).getHour()) + "AM");
            else if (availabilities.get(position).getTime().atZone(ZoneOffset.UTC).getHour() == 12) {
                textView.setText("12PM");
            } else
                textView.setText(Integer.toString(availabilities.get(position).getTime().minus(12, ChronoUnit.HOURS).atZone(ZoneOffset.UTC).getHour()) + "PM");
            textView.setBackgroundResource(R.color.colorPrimary);
        } else if (availabilities.get(position).getFree()) {
            textView.setText("Everyone's Free!");
            imageView.setImageResource(R.drawable.available);
        }

        textView.setTextSize(25);

        return v;

    }

}
