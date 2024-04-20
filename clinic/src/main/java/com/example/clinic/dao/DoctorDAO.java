package com.example.clinic.dao;

import com.example.clinic.model.Doctor;

import java.util.Collection;

public interface DoctorDAO {
    Doctor get(Integer id);

    Collection<Doctor> findAll();

    void insert(Doctor doctor);

    void update(Doctor doctor);

    void delete(Integer id);
}
