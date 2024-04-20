package com.example.clinic.services;

import com.example.clinic.model.User;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class UserService {
    private static Map<String, User> users = new HashMap<>();


    public User getByLogin(String login) {
        return users.get(login);
    }

    public boolean authenticate(String login, String password) {
        User user = getByLogin(login);
        return user != null && user.getPassword().equals(password);
    }

    public abstract List<User> getAllUsers();

    public void addUser(User user) {
        users.put(user.getLogin(), user);
    }

    public boolean checkPassword(User user, String password) {
        return user.getPassword().equals(password);
    }

    public abstract User getUserById(Integer id);

    public User getUserByLogin(String login) {
        return users.get(login);
    }

}
