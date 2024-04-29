package com.example.clinic.dao.impl.inmemory;

import com.example.clinic.dao.ScheduleDAO;
import com.example.clinic.model.Schedule;

import java.util.ArrayList;
import java.util.List;

public class InMemoryScheduleDAO implements ScheduleDAO {
    private List<Schedule> schedules = new ArrayList<>();

    public void creat(Schedule schedule) {
        schedules.add(schedule);
    }

    @Override
    public void update(Schedule schedule) {
        schedules.add(schedule.getDoctorId(), schedule);
    }

    public void delete(Schedule schedule) {
        schedules.remove(schedule);
    }

    public Schedule getSchedule(Integer doctorId) {
        for (Schedule schedule : schedules) {
            if (schedule.getDoctorId().equals(doctorId)) {
                return schedule;
            }
        }
        return null;
    }

}

