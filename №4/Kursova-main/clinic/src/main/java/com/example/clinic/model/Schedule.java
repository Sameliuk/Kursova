package com.example.clinic.model;

public class Schedule {
    private Integer doctorId;
    private Integer timeId;
    private String time;

    public Schedule(Integer doctorId, Integer timeId, String time) {
        this.doctorId = Integer.valueOf(doctorId);
        this.timeId = Integer.valueOf(timeId);
        this.time = time;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
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



