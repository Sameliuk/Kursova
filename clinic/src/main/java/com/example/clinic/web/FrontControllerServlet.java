package com.example.clinic.web;

import com.example.clinic.dao.AppointmentDAO;
//import com.example.clinic.dao.impl.inmemory.InMemoryAppointmentDAO;
import com.example.clinic.dao.impl.inmemory.InMemoryDatabase;
import com.example.clinic.model.Appointment;
import com.example.clinic.model.Doctor;
import com.example.clinic.model.Schedule;
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
        request.getRequestDispatcher("/WEB-INF/jsp/doctors.jsp").forward(request, response);
    }

    protected void appointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String command = request.getParameter("command");
        if (command == null) {
            command = "showAppointment";
        }
        switch (command) {
            case "delete":
                deleteAppointment(request, response);
                break;
            case "create":
                createAppointment(request, response);
                break;
            case "save":
                saveAppointment(request, response);
                break;
            case "edit":
                showEditForm(request, response);
            case "showAppointment":
            default:
                showAppointment(request, response);
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

    private void createAppointment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            LocalDateTime startTime = LocalDateTime.parse(request.getParameter("startTime"));
            LocalDateTime endTime = LocalDateTime.parse(request.getParameter("endTime"));
            Appointment appointment = new Appointment(startTime, endTime);
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

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            Integer id = Integer.valueOf(request.getParameter("id"));
            Appointment appointment = appointmentService.getAppointmentById(id);
            request.setAttribute("appointment", appointment);
            request.getRequestDispatcher("WEB-INF/jsp/edit.jsp").forward(request, response);
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
            LocalDateTime startTime = LocalDateTime.parse(request.getParameter("startTime"));
            LocalDateTime endTime = LocalDateTime.parse(request.getParameter("endTime"));

            Appointment appointment = new Appointment(id, userId , doctorId, startTime, endTime );
            appointmentService.updateAppointment(appointment);
            response.sendRedirect("");
        } catch (Exception e) {
            response.sendError(400);
        }
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
