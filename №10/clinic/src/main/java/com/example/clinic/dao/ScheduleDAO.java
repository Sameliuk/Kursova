package com.example.clinic.dao;

import com.example.clinic.model.Doctor;
import com.example.clinic.model.Schedule;

import java.util.Collection;

public interface ScheduleDAO {

    void addTimes(Integer doctorId, String time);

    void updateTimes(Schedule schedule);

    Collection<Schedule> findByDoctorId(Integer doctorId);

    void deleteTimes(Integer timeId);

    Schedule getScheduleById(Integer timeId);

}
