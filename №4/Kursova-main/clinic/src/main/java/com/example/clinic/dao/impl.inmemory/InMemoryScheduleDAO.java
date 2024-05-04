package com.example.clinic.dao.impl.inmemory;

import com.example.clinic.dao.ScheduleDAO;
import com.example.clinic.model.Schedule;
import com.example.clinic.model.Times;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InMemoryScheduleDAO implements ScheduleDAO {
    private List<Schedule> schedules = new ArrayList<>();

    @Override
    public void addTimes(Integer doctorId, Times time) {
        Schedule schedule = getScheduleDoctorId(doctorId);
        if (schedule != null) {
            schedule.addTimes(time);
        } else {
            schedule = new Schedule(doctorId.toString());
            schedule.addTimes(time);
            schedules.add(schedule);
        }
    }

    @Override
    public void updateTimes(Integer doctorId, Times time) {

    }

    @Override
    public void deleteTimes(Integer doctorId, Times time) {
        Schedule schedule = getScheduleDoctorId(doctorId);
        if (schedule != null) {
            schedule.getTimes().removeIf(t -> t.getId().equals(time.getId()));
        }
    }

    public Schedule getScheduleDoctorId(Integer doctorId) {
        for (Schedule schedule : schedules) {
            if (schedule.getDoctorId().equals(doctorId)) {
                return schedule;
            }
        }
        return null;
    }


    @Override
    public Collection<Schedule> findAll() {
        return schedules;
    }

}

