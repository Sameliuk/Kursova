package com.example.clinic.model;

import java.time.LocalDateTime;

public class Appointment {

    private Integer userId;
    private Integer doctorId;
    private Integer appointmentId;
    private LocalDateTime time;



    public Appointment(Integer appointmentId, int user, int doctor, LocalDateTime time ) {
        this.appointmentId = appointmentId;
        this.userId = user;
        this.doctorId = doctor;
        this.time = time;

    }

    public Appointment(LocalDateTime time) {
        this.time = time;

    }

    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer user) {
        this.userId = userId;
    }

    public Integer getDoctor() {
        return doctorId;
    }

    public void setDoctor(Doctor doctor) {
        this.doctorId = doctorId;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime startTime) {
        this.time = time;
    }


}
