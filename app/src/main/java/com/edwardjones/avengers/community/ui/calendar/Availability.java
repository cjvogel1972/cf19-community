package com.edwardjones.avengers.community.ui.calendar;

import java.time.Instant;

public class Availability {

    String personName;
/*    List<AppointmentTimes> appointmentTimes;
    List<Instant> hoursOfDay;*/
    Instant time;

    public Availability(String personName, Instant time)
    {
        this.personName=personName;
/*        this.appointmentTimes = appointmentTimes;*/
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

/*    public List<AppointmentTimes> getAppointmentTimes() {
        return this.appointmentTimes;
    }

    public void setAppointmentTimes(List<AppointmentTimes> appointmentTimes) {
        this.appointmentTimes = appointmentTimes;
    }

    public List<Instant> getHoursOfDay() {
        return this.hoursOfDay;
    }

    public void setHoursOfDay() {
        Instant hour = Instant.now().truncatedTo(ChronoUnit.DAYS).plus(7, ChronoUnit.HOURS);
        this.hoursOfDay.add(hour);
        for (int i = 8; i < 17; i++)
            this.hoursOfDay.add(hour.plus(1, ChronoUnit.HOURS));
    }*/
}
