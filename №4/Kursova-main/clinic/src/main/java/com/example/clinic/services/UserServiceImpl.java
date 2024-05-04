package com.example.clinic.services;

import com.example.clinic.dao.ClinicDAO;
import com.example.clinic.model.User;


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
    public void create(User user) {
        clinicDAO.getUserDAO().create(user);
    }

    @Override
    public User findById(Integer id) {
        return clinicDAO.getUserDAO().findById(id);
    }

    @Override
    public Collection<User> findAll() {
        return clinicDAO.getUserDAO().findAll();
    }

    @Override
    public User getByLogin(String login) {
        return clinicDAO.getUserDAO().getByLogin(login);
    }


    @Override
    public boolean checkPassword(User user, String password) {
        return user.getPassword().equals(passwordHasher.apply(password));
    }

    @Override
    public void delete(Integer id) {
        clinicDAO.getUserDAO().delete(id);
    }

}
