package com.example.clinic.services;

import com.example.clinic.dao.DoctorDAO;
import com.example.clinic.model.Doctor;

import java.util.List;

public class DoctorServiceImpl extends DoctorService {
    private DoctorDAO doctorDAO;

    public DoctorServiceImpl(DoctorDAO doctorDAO) {
        this.doctorDAO = doctorDAO;
    }

    @Override
    public List<Doctor> getAllDoctors() {
        return (List<Doctor>) doctorDAO.findAll();
    }

    @Override
    public Doctor getDoctorById(int id) {
        return doctorDAO.get(id);
    }

    @Override
    public void addDoctor(Doctor doctor) {
        doctorDAO.insert(doctor);
    }

    @Override
    public void updateDoctor(Doctor updatedDoctor) {
        doctorDAO.update(updatedDoctor);
    }

    @Override
    public void deleteDoctor(int id) {
        doctorDAO.delete(id);
    }
}
