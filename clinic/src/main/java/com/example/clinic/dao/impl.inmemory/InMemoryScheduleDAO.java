package com.example.clinic.dao.impl.inmemory;

import com.example.clinic.dao.ScheduleDAO;
import com.example.clinic.model.Schedule;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InMemoryScheduleDAO implements ScheduleDAO {
    private List<Schedule> schedules = new ArrayList<>();

    public void create(Schedule schedule) {
        schedules.add(schedule);
    }

    @Override
    public void update(Schedule schedule) {
        schedules.add(schedule.getDoctorId(), schedule);
    }

    public void delete(Schedule schedule) {
        schedules.remove(schedule);
    }

    public Collection<Schedule> findAll() {
        return schedules;
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

