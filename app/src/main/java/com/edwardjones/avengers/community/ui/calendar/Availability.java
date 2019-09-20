package com.edwardjones.avengers.community.ui.calendar;

import java.time.Instant;

public class Availability {

    String personName;
    Instant time;
    boolean free;

    public Availability(String personName, Instant time, boolean free)
    {
        this.personName=personName;
        this.time = time;
        this.free = free;
    }
    public String getPersonName()
    {
        return personName;
    }
    public void setPersonName(String personName)
    {
        this.personName =  personName;
    }

    public Instant getTime() {
        return this.time;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public boolean getFree() {
        return free;
    }

    public void setFree(boolean free) {
        this.free = free;
    }
}
