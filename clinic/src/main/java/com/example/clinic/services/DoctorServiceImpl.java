package com.example.clinic.services;

import com.example.clinic.dao.ClinicDAO;
import com.example.clinic.model.Doctor;

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
    public void delete(Integer id) {
        clinicDAO.getDoctorDAO().delete(id);
    }
}
