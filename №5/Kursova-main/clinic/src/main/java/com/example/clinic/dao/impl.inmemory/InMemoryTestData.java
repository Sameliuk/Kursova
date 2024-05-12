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
        database.appointments.clear();


        User alice = new User(1, "alice@example.com", "12345qwe", "Alice", "user");
        User bob = new User(2, "bob@example.com", "qwerty", "Bob", "user");
        User charlie = new User(3, "charlie@example.com", "qweaszx", "Charlie", "user");
        User diana = new User(4, "diana@example.com", "123qwe", "Diana", "admin");
        User evil = new User(5, "evil@example.com", "54321aszx", "Evil Emperror", "admin");
        List<User> users = Arrays.asList(alice, bob, charlie, diana, evil);
        users.forEach(user -> database.users.put(user.getUserId(), user));

        Doctor smith = new Doctor(1, "Dr. Smith", "General Physician");
        Doctor johnson = new Doctor(2, "Dr. Johnson", "Dermatologist");
        Doctor adams = new Doctor(3, "Dr. Adams", "Pediatrician");
        Doctor white = new Doctor(4, "Dr. White", "Cardiologist");
        Doctor brown = new Doctor(5, "Dr. Brown", "Orthopedic Surgeon");
        List<Doctor> doctors = Arrays.asList(smith, johnson, adams, white, brown);
        doctors.forEach(doctor -> database.doctors.put(doctor.getDoctorId(), doctor));

        smith.getSchedules().add(new Schedule(1, 1, LocalDateTime.parse("2024-04-18T10:00:00")));
        smith.getSchedules().add(new Schedule(1, 2, LocalDateTime.parse("2024-04-18T11:00:00")));
        smith.getSchedules().add(new Schedule(1, 3, LocalDateTime.parse("2024-04-18T13:00:00")));
        smith.getSchedules().add(new Schedule(1, 4, LocalDateTime.parse("2024-04-18T14:00:00")));

        Appointment appointment1 = new Appointment(1, 1, 1, LocalDateTime.parse("2024-04-18T10:00:00"));
        Appointment appointment2 = new Appointment(2, 2, 1, LocalDateTime.parse("2024-04-20T14:00:00"));
        Appointment appointment3 = new Appointment(3, 3, 1, LocalDateTime.parse("2024-04-22T11:00:00"));
        Appointment appointment4 = new Appointment(4, 4, 1, LocalDateTime.parse("2024-04-25T09:00:00"));
        Appointment appointment5 = new Appointment(5, 5, 1, LocalDateTime.parse("2024-04-27T15:00:00"));
        List<Appointment> appointments = Arrays.asList(appointment1, appointment2, appointment3, appointment4, appointment5);
        appointments.forEach(appointment -> database.appointments.put(appointment.getAppointmentId(), appointment));


    }
}
