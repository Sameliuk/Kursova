package com.example.clinic.services;

import com.example.clinic.dao.ClinicDAO;
import com.example.clinic.model.Doctor;
import com.example.clinic.model.Schedule;

import java.util.Collection;

public class ScheduleServiceImpl implements ScheduleService{

    ClinicDAO clinicDAO;

    public ScheduleServiceImpl(ClinicDAO clinicDAO) {
        this.clinicDAO = clinicDAO;
    }
    @Override
    public void addTimes(Integer doctorId, String time) {
        clinicDAO.getScheduleDAO().addTimes(doctorId, time);
    }

    @Override
    public void updateTimes(Integer doctorId, Integer timeId, String time) {
        clinicDAO.getScheduleDAO().updateTimes(doctorId, timeId, time);
    }

    @Override
    public Collection<Schedule> findAll(Integer doctorId) {
        return clinicDAO.getScheduleDAO().findByDoctorId(doctorId);
    }

    @Override
    public void deleteTimes(Integer doctorId, Integer timeId) {
        clinicDAO.getScheduleDAO().deleteTimes(doctorId, timeId);
    }

    @Override
    public Schedule getScheduleById(Integer timeId) {
        return clinicDAO.getScheduleDAO().getScheduleById(timeId);
    }
}
