package com.example.clinic.dao;

import com.example.clinic.model.Doctor;

import java.util.Collection;

public interface DoctorDAO {
    Doctor findById(Integer id);

    Collection<Doctor> findAll();
    void create(Doctor doctor);

    void delete(Integer id);
}
