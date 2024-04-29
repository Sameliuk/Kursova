package com.example.clinic.services;

import com.example.clinic.model.Schedule;

public interface ScheduleService {

    void creat(Schedule schedule);

    void update(Schedule schedule);

    void delete(Schedule schedule);

    Schedule getSchedule(Integer doctorId);
}
