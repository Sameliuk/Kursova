package com.example.clinic.services;

import com.example.clinic.model.User;

import java.util.Collection;

public interface   UserService {

   void create(User user);
   User findById(Integer id);

   Collection<User> findAll();

   User getByLogin(String login);

   boolean checkPassword(User user, String password);

   void delete(Integer id);
}
