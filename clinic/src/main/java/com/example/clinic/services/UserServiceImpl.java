package com.example.clinic.services;

import com.example.clinic.dao.UserDAO;
import com.example.clinic.model.User;

import java.util.List;

public class UserServiceImpl extends UserService {

    private final UserDAO userDAO;

    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public User getUserById(Integer id) {
        return userDAO.get(id);
    }

    @Override
    public User getUserByLogin(String login) {
        return userDAO.getByLogin(login);
    }

    @Override
    public List<User> getAllUsers() {
        return (List<User>) userDAO.findAll();
    }

    @Override
    public void addUser(User user) {
        userDAO.insert(user);
    }

}
