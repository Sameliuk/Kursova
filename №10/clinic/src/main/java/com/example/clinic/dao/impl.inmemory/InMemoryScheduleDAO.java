package com.example.clinic.dao.impl.inmemory;

import com.example.clinic.dao.ScheduleDAO;
import com.example.clinic.model.Doctor;
import com.example.clinic.model.Schedule;
import com.example.clinic.model.User;

import java.util.Collection;
import java.util.TreeMap;

public class InMemoryScheduleDAO extends InMemoryAbstractDAO<Schedule> implements ScheduleDAO {

    InMemoryScheduleDAO(InMemoryDatabase database) {
        super(database.schedules, Schedule::getTimeId, Schedule::setTimeId, database);
    }
    private TreeMap<Integer, Schedule> schedules = (TreeMap) database.schedules;

    @Override
    public void addTimes(Integer doctorId, String time) {
        Schedule schedule = new Schedule(-1, doctorId, time);

        this.insert(schedule, true);
        database.doctors.get(doctorId).getSchedules().add(schedule);
    }

    @Override
    public void updateTimes(Schedule schedule) {
        database.schedules.put(schedule.getTimeId(), schedule);
    }

    @Override
    public void deleteTimes(Integer timeId) {
        database.schedules.remove(timeId);
    }

    @Override
    public Schedule getScheduleById(Integer timeId) {
        return database.schedules.get(timeId);
    }


    @Override
    public Collection<Schedule> findByDoctorId(Integer doctorId) {
        return database.doctors.get(doctorId).getSchedules();
    }

}

