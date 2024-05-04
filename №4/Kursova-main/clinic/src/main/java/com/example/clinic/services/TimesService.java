package com.example.clinic.services;

import com.example.clinic.model.Times;

import java.util.Collection;

public interface TimesService {

    void create(Times time);

    void update(Times time);

    Collection<Times> findAll();

    void delete(Integer id);

    Times getTimesId(Integer id);
}
