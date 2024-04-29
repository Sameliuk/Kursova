package com.example.clinic.dao.impl.inmemory;

import com.example.clinic.dao.ClinicDAO;
import com.example.clinic.model.*;
import java.util.*;

public class InMemoryDatabase {

    public Map<Integer, User> users;
    public Map<Integer, Doctor> doctors;
    public Map<Integer, Appointment> appointments;

    public InMemoryDatabase() {

        users = new TreeMap<>();
        doctors = new TreeMap<>();
        appointments = new TreeMap<>();
    }

    public ClinicDAO getDaoFactory() {
        return new InMemoryClinicDAO(this);
    }

    public ClinicDAO getClinicDAO() {
        return new InMemoryClinicDAO(this);
    }

}
