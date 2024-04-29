package com.example.clinic.model;

import java.util.List;

public class Doctor {
    private Integer doctorId;
    private String name;
    private String specialty;
    private List<String> schedule;


    public Doctor(Integer doctorId, String name, String specialty, List<String> schedule) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialty = specialty;
        this.schedule = schedule;
    }

    public Integer getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Integer doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public List<String> getSchedule() {
        return schedule;
    }

    public void setSchedule(List<String> schedule) {
        this.schedule = schedule;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", name='" + name + '\'' +
                ", specialty='" + specialty + '\'' +
                ", schedule=" + schedule +
                '}';
    }
}
