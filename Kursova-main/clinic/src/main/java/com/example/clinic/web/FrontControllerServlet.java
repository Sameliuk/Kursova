package com.example.clinic.web;

import com.example.clinic.dao.AppointmentDAO;
//import com.example.clinic.dao.impl.inmemory.InMemoryAppointmentDAO;
import com.example.clinic.dao.impl.inmemory.InMemoryDatabase;
import com.example.clinic.model.Appointment;
import com.example.clinic.model.Doctor;
import com.example.clinic.model.User;
import com.example.clinic.services.*;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import static com.example.clinic.services.AppointmentService.*;

@WebServlet(name = "FrontControllerServlet", urlPatterns = {"/do/*"})
public class FrontControllerServlet extends HttpServlet {

    AppointmentService appointmentService;
    DoctorService doctorService;
    UserService userService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        appointmentService = (AppointmentService) config.getServletContext().getAttribute("appointmentService");
        doctorService = (DoctorService) config.getServletContext().getAttribute("doctorService");
        userService = (UserService) config.getServletContext().getAttribute("userService");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String pathInfo = request.getPathInfo();
        if (pathInfo == null) {
            pathInfo = "/";
        }
        try {
            switch (pathInfo) {
                case "/login":
                    login(request, response);
                    break;
                case "/logout":
                    logout(request, response);
                    break;
                case "/doctors":
                    doctors(request, response);
                    break;
                case "/schedule":
                    schedule(request, response);
                    break;
                case "/appointment":
                    appointment(request, response);
                    break;
                default:
                    response.sendRedirect(".");
                    break;
            }
        } catch (RuntimeException ex) {
            error(request, response, "Error, " + ex.getMessage());
        }

    }

    protected void doctors(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Collection<Doctor> doctors = doctorService.findAll();
        request.setAttribute("doctors", doctors);
        request.getRequestDispatcher("/WEB-INF/jsp/doctors.jsp").forward(request, response);
    }

    protected void schedule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void appointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


    }


    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().invalidate();

        String login = request.getParameter("login");
        User user = userService.getByLogin(login);
        if (user == null) {
            error(request, response, "Sorry, user with login '" + login + "' not exists");
            return;
        }
        String password = request.getParameter("password");

        if (!userService.checkPassword(user, password)) {
            error(request, response, "Sorry, wrong password");
            return;
        }

        request.getSession().setAttribute("user", user);
        response.sendRedirect(".");
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        response.sendRedirect(".");
    }

    protected void error(HttpServletRequest request, HttpServletResponse response, String message) throws ServletException, IOException {
        request.setAttribute("message", message);
        request.getRequestDispatcher("/WEB-INF/jsp/error.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
