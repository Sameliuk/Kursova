package com.example.clinic.dao;

import com.example.clinic.model.Schedule;

public interface ScheduleDAO {

    void creat(Schedule schedule);

    void update(Schedule schedule);

    void delete(Schedule schedule);

    Schedule getSchedule(Integer doctorId);
}
