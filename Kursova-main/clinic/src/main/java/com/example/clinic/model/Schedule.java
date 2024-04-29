package com.example.clinic.model;

import java.util.ArrayList;
import java.util.List;

public class Schedule {
    private Integer doctorId;
    private List<Times> times;

    public Schedule(String doctorId) {
        this.doctorId = Integer.valueOf(doctorId);
        this.times = new ArrayList<>();
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = Integer.valueOf(doctorId);
    }
    public List<Times> getTimes() {
        return times;
    }

    public void setTimes(List<Times> times) {
        this.times = times;
    }

    public void addTimes(Times time) {
        times.add(time);
    }
}



