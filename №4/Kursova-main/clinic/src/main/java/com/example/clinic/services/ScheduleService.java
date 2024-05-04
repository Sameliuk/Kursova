package com.example.clinic.services;

import com.example.clinic.model.Doctor;
import com.example.clinic.model.Schedule;
import com.example.clinic.model.Times;

import java.util.Collection;

public interface ScheduleService {

    void addTimes(Integer doctorId, Times time);

    void updateTimes(Integer doctorId, Times time);

    Collection<Schedule> findAll();

    void deleteTimes(Integer doctorId, Times time);

    Schedule getScheduleDoctorId(Integer doctorId);
}
