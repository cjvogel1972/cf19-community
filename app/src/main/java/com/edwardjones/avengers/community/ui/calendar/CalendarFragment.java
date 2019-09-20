package com.edwardjones.avengers.community.ui.calendar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.edwardjones.avengers.community.R;

public class CalendarFragment extends Fragment {

    CalendarView calendar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_calendar, container, false);

        calendar = (CalendarView)
                view.findViewById(R.id.calendar);

        // Listener for calendar
        /*calendar
                .setOnDateChangeListener(
                        new CalendarView
                                .OnDateChangeListener() {
                            @Override

                            // TODO when day is clicked
                            public void onSelectDay(
                                    @NonNull CalendarView view)
                            {

                            }
                        });*/
        return view;
    }
}
