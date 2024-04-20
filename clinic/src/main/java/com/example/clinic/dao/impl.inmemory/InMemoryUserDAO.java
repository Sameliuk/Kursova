package com.example.clinic.dao.impl.inmemory;


import com.example.clinic.dao.UserDAO;
import com.example.clinic.model.Appointment;
import com.example.clinic.model.User;

import java.util.Collection;
import java.util.HashMap;

public class InMemoryUserDAO extends InMemoryAbstractDAO<User> implements UserDAO {

    private HashMap<Object, Object> appointments;

    public InMemoryUserDAO(InMemoryDatabase database) {
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

}
