package com.example.clinic.web;

import com.example.clinic.dao.AppointmentDAO;
import com.example.clinic.dao.ClinicDAO;
import com.example.clinic.dao.DoctorDAO;
import com.example.clinic.dao.UserDAO;
import com.example.clinic.dao.impl.inmemory.InMemoryDatabase;
import com.example.clinic.dao.impl.inmemory.InMemoryTestData;
import com.example.clinic.services.*;
import jakarta.servlet.*;

import java.util.function.UnaryOperator;

public class ApplicationContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        InMemoryDatabase database = new InMemoryDatabase();
        InMemoryTestData.generateTo(database);
        sce.getServletContext().setAttribute("database", database);

        ClinicDAO clinicDAO = database.getClinicDAO();

        DoctorService doctorService = new DoctorServiceImpl(clinicDAO);
        sce.getServletContext().setAttribute("doctorService", doctorService);

        AppointmentService appointmentService = new AppointmentServiceImpl(clinicDAO);
        sce.getServletContext().setAttribute("appointmentService", appointmentService);

        UserService userService = new UserServiceImpl(clinicDAO,  UnaryOperator.identity());
        sce.getServletContext().setAttribute("userService", userService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
