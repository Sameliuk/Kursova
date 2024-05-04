package com.example.clinic.services;

import com.example.clinic.dao.ClinicDAO;
import com.example.clinic.model.Schedule;
import com.example.clinic.model.Times;

import java.util.Collection;

public class ScheduleServiceImpl implements ScheduleService{

    ClinicDAO clinicDAO;

    public ScheduleServiceImpl(ClinicDAO clinicDAO) {
        this.clinicDAO = clinicDAO;
    }
    @Override
    public void addTimes(Integer doctorId, Times time) {
        clinicDAO.getScheduleDAO().addTimes(doctorId, time);
    }

    @Override
    public void updateTimes(Integer doctorId, Times time) {
        clinicDAO.getScheduleDAO().updateTimes(doctorId, time);
    }

    @Override
    public Collection<Schedule> findAll() {
        return clinicDAO.getScheduleDAO().findAll();
    }

    @Override
    public void deleteTimes(Integer doctorId, Times time) {
        clinicDAO.getScheduleDAO().deleteTimes(doctorId, time);
    }

    @Override
    public Schedule getScheduleDoctorId(Integer doctorId) {
        return clinicDAO.getScheduleDAO().getScheduleDoctorId(doctorId);
    }
}
