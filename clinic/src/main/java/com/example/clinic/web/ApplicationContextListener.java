package com.example.clinic.web;

import com.example.clinic.dao.AppointmentDAO;
import com.example.clinic.dao.ClinicDAO;
import com.example.clinic.dao.DoctorDAO;
import com.example.clinic.dao.UserDAO;
import com.example.clinic.dao.impl.inmemory.InMemoryDatabase;
import com.example.clinic.dao.impl.inmemory.InMemoryTestData;
import com.example.clinic.model.Appointment;
import com.example.clinic.services.*;
import jakarta.servlet.*;

import java.util.Collection;

public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        // Change to real database in real project
        InMemoryDatabase database = new InMemoryDatabase();

        // Don't use in real project
        InMemoryTestData.generateTo(database);

        ClinicDAO clinicDAO = database.getClinicDAO();

        DoctorService doctorService = new DoctorServiceImpl((DoctorDAO) clinicDAO);
        sce.getServletContext().setAttribute("doctorService", doctorService);

        AppointmentService appointmentService = new AppointmentServiceImpl((AppointmentDAO) clinicDAO) {
            @Override
            public Collection<Appointment> getAllAppointments() {
                return appointmentDAO.findAll();
            }

            @Override
            public Appointment getAppointmentById(Integer id) {
                return appointmentDAO.get(id);
            }

        };
        sce.getServletContext().setAttribute("appointmentService", appointmentService);

        UserService userService = new UserServiceImpl((UserDAO) clinicDAO);
        sce.getServletContext().setAttribute("userService", userService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
