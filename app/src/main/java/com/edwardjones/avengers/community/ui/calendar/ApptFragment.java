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

        String[] randomAppts = new String[] {
                "Ha",
                "Nice",
                "Awesome"
        };

        String[] times = new String[] {
                "07:00",
                "08:00",
                "09:00"
        };

        availabilities.add(new Availability("John", startTime));
/*        availabilities.add(new Availability("Smith"));
        availabilities.add(new Availability("John"));
        availabilities.add(new Availability("Smith"));
        availabilities.add(new Availability("John"));
        availabilities.add(new Availability("Smith"));
        availabilities.add(new Availability("John"));
        availabilities.add(new Availability("Smith"));
        availabilities.add(new Availability("John"));
        availabilities.add(new Availability("Smith"));*/

        ApptAdapter apptAdapter =new ApptAdapter(getContext(),R.layout.appt_row,availabilities);
        appts.setAdapter(apptAdapter);
        return view;
    }
}
