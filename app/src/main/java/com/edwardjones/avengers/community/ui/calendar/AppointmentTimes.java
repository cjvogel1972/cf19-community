package com.edwardjones.avengers.community.ui.calendar;

import java.time.Instant;

public class AppointmentTimes {
    Instant startTime;
    Instant endTime;

    public Instant getStartTime() {
        return startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }
}
