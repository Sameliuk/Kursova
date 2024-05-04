package com.example.clinic.dao.impl.inmemory;

import com.example.clinic.dao.ClinicDAO;
import com.example.clinic.model.*;
import java.util.*;

public class InMemoryDatabase {

    public TreeMap<Integer, User> users;
    public TreeMap<Integer, Doctor> doctors;
    public TreeMap<Integer, Appointment> appointments;

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
