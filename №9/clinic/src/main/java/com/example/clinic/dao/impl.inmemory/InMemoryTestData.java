package com.example.clinic.dao.impl.inmemory;

import com.example.clinic.model.Schedule;
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


        User alice = new User(1, "Alice", "alice@example.com", "passwordhash", "user");
        User bob = new User(2, "Bob", "bob@example.com", "passwordhash", "user");
        User charlie = new User(3, "Charlie", "charlie@example.com", "passwordhash", "admin");
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

        smith.getSchedules().add(new Schedule(1, 1, "2024-04-18 10:00:00"));
        smith.getSchedules().add(new Schedule(1, 2, "2024-04-18 11:00:00"));
        smith.getSchedules().add(new Schedule(1, 3, "2024-04-18 13:00:00"));
        smith.getSchedules().add(new Schedule(1, 4, "2024-04-18 14:00:00"));
        doctors.stream()
                .flatMap(doctor -> doctor.getSchedules().stream())
                .forEach(schedule -> database.schedules.put(schedule.getTimeId(), schedule));

        alice.getAppointment().add(new Appointment(1, alice, smith, "2024-04-18 10:00:00"));
        bob.getAppointment().add(new Appointment(2, bob, johnson, "2024-04-20 14:00:00"));
        alice.getAppointment().add(new Appointment(3, alice, adams, "2024-04-22 11:00:00"));
        evil.getAppointment().add(new Appointment(4, evil, brown, "2024-04-27 15:00:00"));
        users.stream()
                .flatMap(user -> user.getAppointment().stream())
                .forEach(appointment -> database.appointments.put(appointment.getAppointmentId(), appointment));


    }
}
