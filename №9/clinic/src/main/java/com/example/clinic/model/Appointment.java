package com.example.clinic.model;

import java.time.LocalDateTime;

public class Appointment {

    private User user;
    private Doctor doctor;
    private Integer appointmentId;
    private String time;



    public Appointment(Integer appointmentId, User user, Doctor doctor, String time ) {
        this.appointmentId = appointmentId;
        this.user = user;
        this.doctor = doctor;
        this.time = time;

    }


    public Integer getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(Integer appointmentId) {
        this.appointmentId = appointmentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
