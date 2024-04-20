package com.example.clinic.model;

import java.time.LocalDateTime;

public class Appointment {

    private Integer userId;
    private Integer doctorId;
    private Integer appointmentId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String description;
    private User user;
    private Doctor doctor;

    public Appointment(Integer appointmentId, int user, int doctor, LocalDateTime startTime, LocalDateTime endTime, String description) {
        this.appointmentId = appointmentId;
        this.userId = user;
        this.doctorId = doctor;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
