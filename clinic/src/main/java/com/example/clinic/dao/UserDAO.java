package com.example.clinic.dao;

import com.example.clinic.model.User;

import java.util.Collection;

public interface UserDAO {
    User get(Integer id);
    User getByLogin(String login);
    Collection<User> findAll();
    void insert(User user);

}
