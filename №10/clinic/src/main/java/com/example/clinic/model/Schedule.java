package com.example.clinic.model;

import java.time.LocalDateTime;

public class Schedule {
    private Integer doctorId;
    private Integer timeId;
    private String time;

    public Schedule(Integer timeId, Integer doctorId, String time) {
        this.doctorId = Integer.valueOf(doctorId);
        this.timeId = Integer.valueOf(timeId);
        this.time = time;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctor(Integer doctorId) {
        this.doctorId = Integer.valueOf(doctorId);
    }

    public Integer getTimeId() {
        return timeId;
    }

    public void setTimeId(Integer timeId) {
        this.timeId = Integer.valueOf(timeId);
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}



