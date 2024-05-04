package com.example.clinic.web;

import com.example.clinic.dao.AppointmentDAO;
//import com.example.clinic.dao.impl.inmemory.InMemoryAppointmentDAO;
import com.example.clinic.dao.impl.inmemory.InMemoryDatabase;
import com.example.clinic.model.*;
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
    ScheduleService scheduleService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
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
        Collection<Schedule> schedules = scheduleService.findAll();
        request.setAttribute("schedules", schedules);
        request.getRequestDispatcher("/WEB-INF/jsp/schedule.jsp").forward(request, response);
    }

    protected void appointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.parseInt(request.getParameter("userId"));
        Collection<Appointment> appointments = appointmentService.getAppointmentsByUserId(userId);
        request.setAttribute("appointments", appointments);
        request.getRequestDispatcher("/WEB-INF/jsp/appointment.jsp").forward(request, response);
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

        String command = request.getParameter("command");
        if (command == null) {
            command = "showDashboard";
        }
        switch (command) {
            case "editSchedule":
                showEditFormSchedule(request, response);
                break;
            case "editAppointment":
                showEditFormAppointment(request, response);
                break;
            case "showSchedule":
                showSchedule(request, response);
                break;
            case "showAppointment":
                showAppointment(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        String command = request.getParameter("command");
        if (command == null) {
            response.sendError(400, "parameter command not found");
            return;
        }
        switch (command) {
            case "deleteAppointment":
                deleteAppointment(request, response);
                break;
            case "createAppointment":
                createAppointment(request, response);
                break;
            case "saveAppointment":
                saveAppointment(request, response);
                break;
            case "deleteSchedule":
                deleteSchedule(request, response);
                break;
            case "createSchedule":
                createSchedule(request, response);
                break;
            case "saveSchedule":
                saveSchedule(request, response);
                break;
            default:
                response.sendError(400, "wrong command");
        }

    }
    private void createSchedule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer doctorId = Integer.valueOf(request.getParameter("id"));
            String time = request.getParameter("time");
            Times newTime = new Times(null, time);
            scheduleService.addTimes(doctorId, newTime);
            response.sendRedirect("");
        } catch (Exception e) {
            response.sendError(400);
        }
    }
    private void deleteSchedule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer doctorId = Integer.valueOf(request.getParameter("doctorId"));
            Integer timeId = Integer.valueOf(request.getParameter("timeId"));
            Times time = new Times(timeId, "");

            scheduleService.deleteTimes(doctorId, time);
            response.sendRedirect("");
        } catch (Exception e) {
            response.sendError(400);
        }
    }

    private void showEditFormSchedule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer id = Integer.valueOf(request.getParameter("id"));
            Schedule schedule = scheduleService.getScheduleDoctorId(id);
            request.setAttribute("schedule", schedule);
            request.getRequestDispatcher("WEB-INF/jsp/editSchedule.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendError(400);
        }
    }
    private void showSchedule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Collection<Schedule> schedules = scheduleService.findAll();
        request.setAttribute("schedules", schedules);
        request.getRequestDispatcher("WEB-INF/jsp/schedule.jsp").forward(request, response);
    }

    private void saveSchedule(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer id = Integer.valueOf(request.getParameter("id"));
            String time = String.valueOf(scheduleService.getScheduleDoctorId(id));
            Schedule schedule = new Schedule(id, time);
            scheduleService.updateTimes(schedule);
            response.sendRedirect("");
        } catch (Exception e) {
            response.sendError(400);
        }
    }
    private void createAppointment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            LocalDateTime time = LocalDateTime.parse(request.getParameter("time"));
            Appointment appointment = new Appointment(time);
            appointmentService.addAppointment(appointment);
            response.sendRedirect("");
        } catch (Exception e) {
            response.sendError(400);
        }
    }
    private void deleteAppointment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer id = Integer.valueOf(request.getParameter("id"));
            appointmentService.deleteAppointment(id);
            response.sendRedirect("");
        } catch (Exception e) {
            response.sendError(400);
        }
    }

    private void showEditFormAppointment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer id = Integer.valueOf(request.getParameter("id"));
            Appointment appointment = appointmentService.getAppointmentById(id);
            request.setAttribute("appointment", appointment);
            request.getRequestDispatcher("WEB-INF/jsp/editAppointment.jsp").forward(request, response);
        } catch (Exception e) {
            response.sendError(400);
        }
    }
    private void showAppointment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer id = Integer.valueOf(request.getParameter("id"));
        Collection<Appointment> appointments = appointmentService.getAppointmentsByUserId(id);
        request.setAttribute("appointments", appointments);
        request.getRequestDispatcher("WEB-INF/jsp/appointment.jsp").forward(request, response);
    }

    private void saveAppointment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer id = Integer.valueOf(request.getParameter("id"));
            Integer userId = Integer.valueOf(request.getParameter("userId"));
            Integer doctorId = Integer.valueOf(request.getParameter("doctorId"));
            LocalDateTime time = LocalDateTime.parse(request.getParameter("time"));

            Appointment appointment = new Appointment(id, userId , doctorId, time );
            appointmentService.updateAppointment(appointment);
            response.sendRedirect("");
        } catch (Exception e) {
            response.sendError(400);
        }
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
