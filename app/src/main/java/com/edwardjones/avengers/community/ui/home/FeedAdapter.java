package com.edwardjones.avengers.community.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.edwardjones.avengers.community.R;

import java.util.ArrayList;

public class FeedAdapter extends ArrayAdapter<Feed> {

    ArrayList<Feed> feeds = new ArrayList<>();

    public FeedAdapter(Context context, int textViewResourceId, ArrayList<Feed> objects) {
        super(context, textViewResourceId, objects);
        feeds = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        v = inflater.inflate(R.layout.feed_row, null);
        TextView authorView = (TextView) v.findViewById(R.id.author);
        TextView contentView = (TextView) v.findViewById(R.id.content);

        if (feeds.get(position).getAuthor() != null) {
            authorView.setText(feeds.get(position).getAuthor());
            authorView.setTextSize(20);
        }

        if (feeds.get(position).getContent() != null) {
            contentView.setText(feeds.get(position).getContent());
            contentView.setTextSize(20);
        }

        return v;

    }

}
