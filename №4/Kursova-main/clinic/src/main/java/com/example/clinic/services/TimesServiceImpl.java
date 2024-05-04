package com.example.clinic.services;

import com.example.clinic.dao.ClinicDAO;

import com.example.clinic.model.Times;

import java.util.Collection;

public class TimesServiceImpl implements TimesService{

    ClinicDAO clinicDAO;

    public TimesServiceImpl(ClinicDAO clinicDAO) {
        this.clinicDAO = clinicDAO;
    }

    @Override
    public void create(Times time) {
        clinicDAO.getTimesDAO().create(time);
    }

    @Override
    public void update(Times time) {
        clinicDAO.getTimesDAO().update(time);
    }

    @Override
    public Collection<Times> findAll() {
        return clinicDAO.getTimesDAO().findAll();
    }

    @Override
    public void delete(Integer id) {
        clinicDAO.getTimesDAO().delete(id);
    }

    @Override
    public Times getTimesId(Integer id) {
        return clinicDAO.getTimesDAO().getTimesId(id);
    }
}
