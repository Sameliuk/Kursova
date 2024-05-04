package com.example.clinic.services;

import com.example.clinic.model.Doctor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface DoctorService {

    Doctor findById(Integer id);

    Collection<Doctor> findAll();

    void create(Doctor doctor);

    void delete(Integer id);
}
