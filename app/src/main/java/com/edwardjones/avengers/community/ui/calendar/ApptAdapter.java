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
/*        setTimes();*/

        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.appt_row, null);
        TextView textView = (TextView) v.findViewById(R.id.personName);
        ImageView imageView = (ImageView) v.findViewById(R.id.apptColor);
        textView.setText(availabilities.get(position).getPersonName());
        if (textView.getText().toString().matches("[0-9]+"))
            textView.setBackgroundResource(R.color.colorPrimary);
        else
            imageView.setImageResource(R.drawable.busy);
        textView.setTextSize(25);

        return v;

    }

 /*   public void setTimes() {
        Instant hour = Instant.now().truncatedTo(ChronoUnit.DAYS).plus(7, ChronoUnit.HOURS);
        times.add(hour);
        for (int i = 8; i < 17; i++)
            times.add(hour.plus(1, ChronoUnit.HOURS));
    }*/

}
