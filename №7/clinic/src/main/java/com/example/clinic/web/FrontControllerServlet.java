package com.example.clinic.web;

import com.example.clinic.model.*;
import com.example.clinic.services.*;

import java.io.IOException;
import java.util.Collection;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;



@WebServlet(name = "FrontControllerServlet", urlPatterns = {"/do/*"})
public class FrontControllerServlet extends HttpServlet {
    AppointmentService appointmentService;
    DoctorService doctorService;
    UserService userService;
    ScheduleService scheduleService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        appointmentService = (AppointmentService) config.getServletContext().getAttribute("appointmentService");
        doctorService = (DoctorService) config.getServletContext().getAttribute("doctorService");
        userService = (UserService) config.getServletContext().getAttribute("userService");
        scheduleService = (ScheduleService) config.getServletContext().getAttribute("scheduleService");
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
                case "/deleteAppointment":
                    deleteAppointment(request, response);
                    break;
                case "/createAppointment":
                    createAppointment(request, response);
                    break;
                case "/appointment":
                    appointment(request, response);
                    break;
                case "/deleteSchedule":
                    deleteSchedule(request, response);
                    break;
                case "/createSchedule":
                    createSchedule(request, response);
                    break;
                case "/saveSchedule":
                    saveSchedule(request, response);
                    break;
                case "/editSchedule":
                    showEditFormSchedule(request, response);
                    break;
                case "/":
                default:
                    appointment(request, response);
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
    protected void createSchedule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer doctorId = Integer.valueOf(request.getParameter("doctorId"));
            String  time = request.getParameter("time");
            doctorService.addSchedule(doctorId, time);
            response.sendRedirect("");
        } catch (Exception e) {
            response.sendError(400);
        }
    }
    protected void deleteSchedule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer doctorId = Integer.valueOf(request.getParameter("doctorId"));
            Integer timeId = Integer.valueOf(request.getParameter("timeId"));
            doctorService.deleteSchedule(doctorId, timeId);
            response.sendRedirect("");
        } catch (Exception e) {
            response.sendError(400);
        }
    }

    protected void showEditFormSchedule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer timeId = Integer.valueOf(request.getParameter("timeId"));
            doctorService.getScheduleById(timeId);
            request.getRequestDispatcher("WEB-INF/jsp/editSchedule.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendError(400);
        }
    }
    protected void saveSchedule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer doctorId = Integer.valueOf(request.getParameter("doctorId"));
            Integer timeId = Integer.valueOf(request.getParameter("timeId"));
            String time = request.getParameter("time");
            doctorService.updateSchedule(timeId, doctorId, time);
            response.sendRedirect("");
        } catch (Exception e) {
            response.sendError(400);
        }
    }
    protected void createAppointment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("user");
            if (user == null) {
                error(request, response, "Sorry, you need to log in");
                return;
            }

            int doctorId = Integer.parseInt(request.getParameter("doctor"));
            Doctor doctor = doctorService.findById(doctorId);
            Collection<Doctor> doctors = doctorService.findAll();

            String time = request.getParameter("time");

            request.setAttribute("doctors", doctors);
            userService.addAppointment(user, doctor, time);
            response.sendRedirect("" + user);
            request.getRequestDispatcher("/WEB-INF/jsp/appointment.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendError(400);
        }
    }
    protected void deleteAppointment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer appointmentId = Integer.valueOf(request.getParameter("appointmentId"));
            appointmentService.deleteAppointment(appointmentId);
            response.sendRedirect("");
        } catch (Exception e) {
            response.sendError(400);
        }
    }

    protected void appointment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            error(request, response, "Sorry, you need to log in");
            return;
        }
        int doctorId = Integer.parseInt(request.getParameter("doctor"));
        Doctor doctor = doctorService.findById(doctorId);
        Collection<Doctor> doctors = doctorService.findAll();

        String time = request.getParameter("time");

        request.setAttribute("doctors", doctors);
        userService.addAppointment(user, doctor, time);
        response.sendRedirect("" + user);
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
        response.sendRedirect("appointment");
    }

    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("appointment", appointmentService.getAllAppointments());
        request.getSession().invalidate();
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
