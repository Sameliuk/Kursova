package com.example.clinic.dao.impl.inmemory;

import com.example.clinic.dao.ScheduleDAO;
import com.example.clinic.model.Schedule;

import java.util.Collection;
import java.util.TreeMap;

public class InMemoryScheduleDAO extends InMemoryAbstractDAO<Schedule> implements ScheduleDAO {

    InMemoryScheduleDAO(InMemoryDatabase database) {
        super(database.schedules, Schedule::getDoctorId, Schedule::setDoctorId, database);
    }
    private TreeMap<Integer, Schedule> schedules = new TreeMap<>();

    @Override
    public void addTimes(Schedule schedule) {
        int id = schedules.isEmpty() ? 1 : schedules.lastKey() + 1;
        schedule.setTimeId(id);
        schedules.put(id, schedule);
    }

    @Override
    public void updateTimes(Schedule schedule) {
        schedules.put(schedule.getTimeId(), schedule);
    }

    @Override
    public void deleteTimes(Integer timeId) {
        schedules.remove(timeId);
    }

    public Schedule getScheduleById(Integer timeId) {
        return schedules.get(timeId);
    }


    @Override
    public Collection<Schedule> findByDoctorId(Integer doctorId) {
        return database.doctors.get(doctorId).getSchedules();
    }

}

