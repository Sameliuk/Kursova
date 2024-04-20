package com.example.clinic.dao.impl.inmemory;

import com.example.clinic.dao.DoctorDAO;
import com.example.clinic.model.Doctor;
import java.util.Collection;
import java.util.Map;

public class InMemoryDoctorDAO implements DoctorDAO {
    private final Map<Integer, Doctor> doctors;
    private int nextDoctorId = 1;

    public InMemoryDoctorDAO(InMemoryDatabase database) {
        this.doctors = database.doctors;
    }

    @Override
    public Doctor get(Integer id) {
        return doctors.get(id);
    }

    @Override
    public Collection<Doctor> findAll() {
        return doctors.values();
    }

    @Override
    public void insert(Doctor doctor) {

    }

    @Override
    public void update(Doctor doctor) {

    }

    @Override
    public void delete(Integer id) {

    }

}
