package com.example.clinic.services;

import com.example.clinic.model.Doctor;

import java.util.ArrayList;
import java.util.List;

public class DoctorService {
    private static List<Doctor> doctors = new ArrayList<>();

    public List<Doctor> getAllDoctors() {
        return doctors;
    }

    public Doctor getDoctorById(int id) {
        for (Doctor doctor : doctors) {
            if (doctor.getDoctorId().equals(id)) {
                return doctor;
            }
        }
        return null;
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void updateDoctor(Doctor updatedDoctor) {
        for (Doctor doctor : doctors) {
            if (doctor.getDoctorId().equals(updatedDoctor.getDoctorId())) {
                doctor.setName(updatedDoctor.getName());
                doctor.setSpecialty(updatedDoctor.getSpecialty());
                doctor.setSchedule(updatedDoctor.getSchedule());
                return;
            }
        }
    }

    public void deleteDoctor(int id) {
        doctors.removeIf(doctor -> doctor.getDoctorId().equals(id));
    }
}
