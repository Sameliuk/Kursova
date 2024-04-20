package com.example.clinic.dao.impl.inmemory;

import com.example.clinic.model.User;
import com.example.clinic.model.Doctor;
import com.example.clinic.model.Appointment;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class InMemoryTestData {

    public static void generateTo(InMemoryDatabase database) {
        database.users.clear();
        database.doctors.clear();
        database.appointments.clear();

        User alice = new User(1, "Alice", "alice@example.com", "passwordhash", "user");
        User bob = new User(2, "Bob", "bob@example.com", "passwordhash", "user");
        User charlie = new User(3, "Charlie", "charlie@example.com", "passwordhash", "user");
        User diana = new User(4, "Diana", "diana@example.com", "passwordhash", "admin");
        User evil = new User(5, "Evil Emperror", "evil@example.com", "passwordhash", "user");
        List<User> users = Arrays.asList(alice, bob, charlie, diana, evil);
        users.forEach(user -> database.users.put(user.getUserId(), user));

        Doctor smith = new Doctor(1, "Dr. Smith", "General Physician");
        Doctor johnson = new Doctor(2, "Dr. Johnson", "Dermatologist");
        Doctor adams = new Doctor(3, "Dr. Adams", "Pediatrician");
        Doctor white = new Doctor(4, "Dr. White", "Cardiologist");
        Doctor brown = new Doctor(5, "Dr. Brown", "Orthopedic Surgeon");
        List<Doctor> doctors = Arrays.asList(smith, johnson, adams, white, brown);
        doctors.forEach(doctor -> database.doctors.put(doctor.getDoctorId(), doctor));

        Appointment appointment1 = new Appointment(1, alice.getUserId(), smith.getDoctorId(), LocalDateTime.parse("2024-04-18T10:00:00"), LocalDateTime.parse("2024-04-18T10:30:00"), "General Checkup");
        Appointment appointment2 = new Appointment(2, bob.getUserId(), johnson.getDoctorId(), LocalDateTime.parse("2024-04-20T14:00:00"), LocalDateTime.parse("2024-04-20T14:30:00"), "Skin Allergy");
        Appointment appointment3 = new Appointment(3, charlie.getUserId(), adams.getDoctorId(), LocalDateTime.parse("2024-04-22T11:00:00"), LocalDateTime.parse("2024-04-22T11:30:00"), "Child Vaccination");
        Appointment appointment4 = new Appointment(4, diana.getUserId(), white.getDoctorId(), LocalDateTime.parse("2024-04-25T09:30:00"), LocalDateTime.parse("2024-04-25T10:00:00"), "Heart Checkup");
        Appointment appointment5 = new Appointment(5, evil.getUserId(), brown.getDoctorId(), LocalDateTime.parse("2024-04-27T15:30:00"), LocalDateTime.parse("2024-04-27T16:00:00"), "Orthopedic Consultation");
        List<Appointment> appointments = Arrays.asList(appointment1, appointment2, appointment3, appointment4, appointment5);
        appointments.forEach(appointment -> database.appointments.put(appointment.getAppointmentId(), appointment));
    }
}
