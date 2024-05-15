package com.example.clinic.services;

import com.example.clinic.dao.ClinicDAO;
import com.example.clinic.model.Appointment;
import com.example.clinic.model.Doctor;
import com.example.clinic.model.User;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.function.UnaryOperator;

public class UserServiceImpl implements UserService {

    ClinicDAO clinicDAO;
    UnaryOperator<String> passwordHasher;

    public UserServiceImpl(ClinicDAO clinicDAO, UnaryOperator<String> passwordHasher) {
        this.clinicDAO = clinicDAO;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public User getByLogin(String login) {
        return clinicDAO.getUserDAO().getByLogin(login);
    }

    @Override
    public User getUserId(Integer id) {
        return clinicDAO.getUserDAO().getUserId(id);
    }

    @Override
    public boolean checkPassword(User user, String password) {
        return user.getPasswordHash().equals(passwordHasher.apply(password));
    }

    @Override
    public void addAppointment(User user, Doctor doctor, String time) {
        clinicDAO.getAppointmentDAO().create(user, doctor, time);
    }
    @Override
    public Collection<User> findAll() {
        return clinicDAO.getUserDAO().findAll();
    }

    @Override
    public Collection<Appointment> findAllAppointments(User user) {
        return clinicDAO.getUserDAO().findAllAppointments(user);
    }

}
