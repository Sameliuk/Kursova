package com.example.clinic.services;

import com.example.clinic.model.Doctor;
import com.example.clinic.model.Schedule;

import java.util.Collection;

public interface ScheduleService {

    void addTimes(Integer doctorId, String time);

    void updateTimes(Schedule schedule);

    Collection<Schedule> findAll(Integer doctorId);

    void deleteTimes(Integer timeId);

    Schedule getScheduleById(Integer timeId);
}
