package com.example.clinic.dao;

import com.example.clinic.model.Schedule;
import com.example.clinic.model.Times;

import java.util.Collection;

public interface ScheduleDAO {

    void addTimes(Integer doctorId, Times time);

    void updateTimes(Integer doctorId, Times time);

    Collection<Schedule> findAll();

    void deleteTimes(Integer doctorId, Times time);

    Schedule getScheduleDoctorId(Integer doctorId);

}
