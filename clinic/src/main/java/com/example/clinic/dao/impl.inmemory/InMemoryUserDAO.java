package com.example.clinic.dao.impl.inmemory;


import com.example.clinic.dao.UserDAO;
import com.example.clinic.model.User;

import java.util.Collection;


import java.util.TreeMap;

class InMemoryUserDAO extends InMemoryAbstractDAO<User> implements UserDAO {

    InMemoryUserDAO(InMemoryDatabase database) {
        super(database.users, User::getUserId, User::setUserId, database);
    }


    private TreeMap<Integer, User> users = new TreeMap<>();

    @Override
    public void create(User user) {
        int id = users.isEmpty() ? 1 : users.lastKey() + 1;
        user.setUserId(id);
        users.put(id, user);
    }
    @Override
    public User findById(Integer id) {
        return users.get(id);
    }

    @Override
    public Collection<User> findAll() {
        return users.values();
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
    public void delete(Integer id) {
        users.remove(id);
    }

}
