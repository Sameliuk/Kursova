package com.example.clinic.dao;

import com.example.clinic.model.Schedule;

import java.util.Collection;

public interface ScheduleDAO {

    void create(Schedule schedule);

    void update(Schedule schedule);

    Collection<Schedule> findAll();
    void delete(Schedule schedule);

    Schedule getSchedule(Integer doctorId);
}
