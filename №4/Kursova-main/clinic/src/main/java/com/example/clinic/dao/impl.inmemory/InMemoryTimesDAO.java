package com.example.clinic.dao.impl.inmemory;



import com.example.clinic.dao.TimesDAO;
import com.example.clinic.model.Times;
import com.example.clinic.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class InMemoryTimesDAO implements TimesDAO {
    InMemoryTimesDAO(InMemoryDatabase database) {
        super(database.times, Times::getTimesId, Times::setTimesId, database);
    }
    private ArrayList<Times> times = new ArrayList<>();

    @Override
    public void create(Times time) {
        times.add(time);
    }

    @Override
    public void update(Times time) {
        times.add(time.getId(), time);
    }

    @Override
    public void delete(Integer id) {
        times.remove(id);
    }

    @Override
    public Times getTimesId(Integer id) {
        return times.get(id);
    }

    @Override
    public Collection<Times> findAll() {
        return times;
    }

}
