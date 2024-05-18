package com.example.clinic.services;

import com.example.clinic.dao.ClinicDAO;
import com.example.clinic.model.Doctor;
import com.example.clinic.model.Schedule;

import java.util.Collection;

public class DoctorServiceImpl implements DoctorService {

    ClinicDAO clinicDAO;

    public DoctorServiceImpl(ClinicDAO clinicDAO) {
        this.clinicDAO = clinicDAO;
    }
    @Override
    public Doctor findById(Integer id) {
        return clinicDAO.getDoctorDAO().findById(id);
    }

    @Override
    public Collection<Doctor> findAll() {
        return clinicDAO.getDoctorDAO().findAll();
    }

    @Override
    public void create(Doctor doctor) {
       clinicDAO.getDoctorDAO().create(doctor);
    }

    @Override
    public void addSchedule(Integer doctorId, String time) {
        clinicDAO.getScheduleDAO().addTimes(doctorId, time);
    }

    @Override
    public void updateSchedule(Schedule schedule) {
        clinicDAO.getScheduleDAO().updateTimes(schedule);
    }

    @Override
    public void deleteSchedule(Integer timeId) {
        clinicDAO.getScheduleDAO().deleteTimes(timeId);
    }

    @Override
    public void getScheduleById(Integer timeId) {
        clinicDAO.getScheduleDAO().getScheduleById(timeId);
    }

    @Override
    public void delete(Integer id) {
        clinicDAO.getDoctorDAO().delete(id);
    }
}
