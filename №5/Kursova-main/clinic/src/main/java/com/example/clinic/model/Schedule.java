package com.example.clinic.model;

import java.time.LocalDateTime;

public class Schedule {
    private Integer doctorId;
    private Integer timeId;
    private LocalDateTime time;

    public Schedule(Integer doctorId, Integer timeId, LocalDateTime time) {
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

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

}



