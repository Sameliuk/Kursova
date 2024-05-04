package com.example.clinic.dao;

import com.example.clinic.model.Schedule;
import com.example.clinic.model.Times;

import java.util.Collection;

public interface TimesDAO {

    void create(Times time);

    void update(Times time);

    Collection<Times> findAll();

    void delete(Integer id);

    Times getTimesId(Integer id);

}