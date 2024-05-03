package com.example.clinic.services;

import com.example.clinic.model.Doctor;
import com.example.clinic.model.Schedule;

import java.util.Collection;

public interface ScheduleService {

    void create(Schedule schedule);

    void update(Schedule schedule);

    Collection<Schedule> findAll();

    void delete(Schedule schedule);

    Schedule getSchedule(Integer doctorId);
}
