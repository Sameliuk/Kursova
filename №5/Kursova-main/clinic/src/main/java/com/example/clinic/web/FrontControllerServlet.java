package com.example.clinic.web;

import com.example.clinic.model.*;
import com.example.clinic.services.*;

import java.io.IOException;
import java.time.LocalDateTime;
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
                case "/saveAppointment":
                    saveAppointment(request, response);
                    break;
                case "/editAppointment":
                    showEditFormAppointment(request, response);
                    break;
                case "/showAppointment":
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
                case "/showSchedule":
                    schedule(request, response);

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
    protected void createSchedule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer doctorId = Integer.valueOf(request.getParameter("doctorId"));
            Integer timeId = Integer.valueOf(request.getParameter("timeId"));
            LocalDateTime time = LocalDateTime.parse(request.getParameter("time"));
            Schedule schedule = new Schedule (doctorId, timeId, time);
            scheduleService.addTimes(schedule);
            doctorService.addSchedule(doctorId, schedule);
            response.sendRedirect("");
        } catch (Exception e) {
            response.sendError(400);
        }
    }
    protected void deleteSchedule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer timeId = Integer.valueOf(request.getParameter("timeId"));
            scheduleService.deleteTimes(timeId);
            response.sendRedirect("");
        } catch (Exception e) {
            response.sendError(400);
        }
    }

    protected void showEditFormSchedule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer timeId = Integer.valueOf(request.getParameter("timeId"));
            Schedule schedule = scheduleService.getScheduleById(timeId);
            request.setAttribute("schedule", schedule);
            request.getRequestDispatcher("WEB-INF/jsp/editSchedule.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendError(400);
        }
    }
    protected void schedule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer doctorId = Integer.valueOf(request.getParameter("doctorId"));
        Collection<Schedule> schedules = scheduleService.findAll(doctorId);
        request.setAttribute("schedules", schedules);
        request.getRequestDispatcher("WEB-INF/jsp/schedule.jsp").forward(request, response);
    }

    protected void saveSchedule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer doctorId = Integer.valueOf(request.getParameter("doctorId"));
            Integer timeId = Integer.valueOf(request.getParameter("timeId"));
            LocalDateTime time = LocalDateTime.parse(request.getParameter("text"));
            Schedule schedule = new Schedule (doctorId, timeId, time);
            scheduleService.updateTimes(schedule);
            response.sendRedirect("");
        } catch (Exception e) {
            response.sendError(400);
        }
    }
    protected void createAppointment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            LocalDateTime time = LocalDateTime.parse(request.getParameter("text"));
            Appointment appointment = new Appointment(time);
            appointmentService.addAppointment(appointment);
            response.sendRedirect("");
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

    protected void showEditFormAppointment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer appointmentId = Integer.valueOf(request.getParameter("appointmentId"));
            Appointment appointment = appointmentService.getAppointmentById(appointmentId);
            request.setAttribute("appointment", appointment);
            request.getRequestDispatcher("WEB-INF/jsp/editAppointment.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendError(400);
        }
    }
    protected void appointment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer userId = Integer.valueOf(request.getParameter("user"));
        Collection<Appointment> appointments = appointmentService.getAppointmentsByUserId(userId);
        request.setAttribute("appointments", appointments);
        request.getRequestDispatcher("WEB-INF/jsp/appointment.jsp").forward(request, response);
    }

    protected void saveAppointment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer appointmentId = Integer.valueOf(request.getParameter("appointmentId"));
            Integer userId = Integer.valueOf(request.getParameter("user"));
            Integer doctorId = Integer.valueOf(request.getParameter("doctor"));
            LocalDateTime time = LocalDateTime.parse(request.getParameter("text"));

            Appointment appointment = new Appointment(appointmentId, userId , doctorId, time );
            appointmentService.updateAppointment(appointment);
            response.sendRedirect("");
        } catch (Exception e) {
            response.sendError(400);
        }
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
