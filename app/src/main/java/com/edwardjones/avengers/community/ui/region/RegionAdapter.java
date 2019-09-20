package com.edwardjones.avengers.community.ui.region;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.edwardjones.avengers.community.R;

import java.util.ArrayList;

public class RegionAdapter extends ArrayAdapter<Region> {

    ArrayList<Region> regions = new ArrayList<>();

    public RegionAdapter(Context context, int textViewResourceId, ArrayList<Region> objects) {
        super(context, textViewResourceId, objects);
        regions = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.region_row, null);
        TextView regionPersonNameView = (TextView) v.findViewById(R.id.regionPersonName);
        TextView typeView = (TextView) v.findViewById(R.id.type);



        if (regions.get(position).getName() != null) {
            regionPersonNameView.setText(regions.get(position).getName().toString());
            regionPersonNameView.setTextSize(30);
        }

        if (regions.get(position).getPersonType() != null) {
            typeView.setText(regions.get(position).getPersonType());
            typeView.setTextSize(30);
        }

        return v;

    }

}
