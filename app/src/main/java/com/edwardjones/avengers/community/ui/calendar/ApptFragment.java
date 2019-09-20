package com.edwardjones.avengers.community.ui.calendar;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.edwardjones.avengers.community.R;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ApptFragment extends Fragment {

    ListView appts;
    ArrayList<Availability> availabilities=new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = this.getActivity().getPreferences(Context.MODE_PRIVATE);
        String personId = sharedPreferences.getString("personId", "1234");

        // got list of appts back from person Id
        List<AppointmentTimes> appointmentTimesList = new ArrayList<>();
        Instant startTime = Instant.now();
        Instant endTime = Instant.now().plus(1, ChronoUnit.HOURS);
        AppointmentTimes appointmentTimes = new AppointmentTimes();
        appointmentTimes.setStartTime(startTime);
        appointmentTimes.setEndTime(endTime);

        View view = inflater.inflate(R.layout.fragment_appt, container, false);

        appts = (ListView)
                view.findViewById(R.id.apptList);

        Instant hour = Instant.now().truncatedTo(ChronoUnit.DAYS).plus(7, ChronoUnit.HOURS);
        availabilities.add(new Availability(null, hour, true));
        availabilities.add(new Availability(null, null, true));
        for (int i = 8; i < 17; i++) {
            hour = hour.plus(1, ChronoUnit.HOURS);
            availabilities.add(new Availability(null, hour, false));
            if (i == 10) {
                availabilities.add(new Availability("John Smith", startTime, false));
                availabilities.add(new Availability("Abby Gail", startTime, false));
            } else if (i == 14) {
                availabilities.add(new Availability("Will Bot", startTime, false));
                availabilities.add(new Availability("Sara Mitchell", startTime, false));
            } else if (i == 16) {
                availabilities.add(new Availability("Jacob Williams", startTime, false));
            } else {
                availabilities.add(new Availability(null, null, true));
            }
        }

        ApptAdapter apptAdapter =new ApptAdapter(getContext(),R.layout.appt_row,availabilities);
        appts.setAdapter(apptAdapter);
        return view;
    }
}
