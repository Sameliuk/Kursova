package com.example.clinic.dao;

import com.example.clinic.model.User;

import java.util.Collection;

public interface UserDAO {
    void create(User user);
    User findById(Integer id);
    Collection<User> findAll();
    User getByLogin(String login);
    void delete(Integer id);
}
