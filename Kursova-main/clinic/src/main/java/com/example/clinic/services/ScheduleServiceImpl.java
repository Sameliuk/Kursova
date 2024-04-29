package com.example.clinic.services;

import com.example.clinic.dao.ClinicDAO;
import com.example.clinic.model.Schedule;
import com.example.clinic.model.Times;

public class ScheduleServiceImpl implements ScheduleService{

    ClinicDAO clinicDAO;

    public ScheduleServiceImpl(ClinicDAO clinicDAO) {
        this.clinicDAO = clinicDAO;
    }
    @Override
    public void creat(Schedule schedule) {
        clinicDAO.getScheduleDAO().creat(schedule);
    }

    @Override
    public void update(Schedule schedule) {
        clinicDAO.getScheduleDAO().update(schedule);
    }

    @Override
    public void delete(Schedule schedule) {
        clinicDAO.getScheduleDAO().delete(schedule);
    }

    @Override
    public Schedule getSchedule(Integer doctorId) {
        return clinicDAO.getScheduleDAO().getSchedule(doctorId);
    }
}
