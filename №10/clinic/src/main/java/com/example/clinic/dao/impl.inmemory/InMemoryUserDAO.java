package com.example.clinic.dao.impl.inmemory;


import com.example.clinic.dao.UserDAO;
import com.example.clinic.model.Appointment;
import com.example.clinic.model.Doctor;
import com.example.clinic.model.User;

import java.util.Collection;


import java.util.TreeMap;

class InMemoryUserDAO extends InMemoryAbstractDAO<User> implements UserDAO {

    InMemoryUserDAO(InMemoryDatabase database) {
        super(database.users, User::getUserId, User::setUserId, database);
    }


    @Override
    public User getByLogin(String login) {
        return database.users.values()
                .stream()
                .filter(user -> user.getLogin().equals(login))
                .findFirst()
                .orElse(null);
    }

    @Override
    public User getUserId(Integer id) {
        return database.users.get(id);
    }

    @Override
    public Collection<User> findAll() {
        return database.users.values();
    }

    @Override
    public Collection<Appointment> findAllAppointments(User user) {
        return database.appointments.values();
    }

}
