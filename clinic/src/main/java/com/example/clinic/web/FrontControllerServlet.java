package com.example.clinic.web;

import com.example.clinic.dao.AppointmentDAO;
import com.example.clinic.dao.impl.inmemory.InMemoryAppointmentDAO;
import com.example.clinic.dao.impl.inmemory.InMemoryDatabase;
import com.example.clinic.model.Appointment;
import com.example.clinic.model.Doctor;
import com.example.clinic.model.User;
import com.example.clinic.services.AppointmentService;
import com.example.clinic.services.AppointmentServiceImpl;
import com.example.clinic.services.DoctorService;
import com.example.clinic.services.UserService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import static com.example.clinic.services.AppointmentService.*;

@WebServlet(name = "FrontControllerServlet", urlPatterns = {"/do/*"})
public class FrontControllerServlet extends HttpServlet {

    DoctorService doctorService;
    UserService userService;

    @Override
    public void init() throws ServletException {
        doctorService = new DoctorService();
        userService = new UserService() {
            @Override
            public List<User> getAllUsers() {
                return null;
            }

            @Override
            public User getUserById(Integer id) {
                return null;
            }

        };
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
        Collection<Doctor> doctors = doctorService.getAllDoctors();
        request.setAttribute("doctors", doctors);
        request.getRequestDispatcher("/WEB-INF/jsp/doctors.jsp").forward(request, response);
    }

    protected void schedule(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        Doctor doctor = doctorService.getDoctorById(doctorId);
        request.setAttribute("doctor", doctor);
        request.getRequestDispatcher("/WEB-INF/jsp/schedule.jsp").forward(request, response);
    }

    protected void appointment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Получаем параметры запроса
        int appointmentId = Integer.parseInt(request.getParameter("appointmentId"));
        int userId = Integer.parseInt(request.getParameter("userId"));
        int doctorId = Integer.parseInt(request.getParameter("doctorId"));
        String startTime = request.getParameter("startTime");
        String endTime = request.getParameter("endTime");
        String description = request.getParameter("description");


        Appointment appointment = new Appointment(appointmentId, userId, doctorId, LocalDateTime.parse(startTime), LocalDateTime.parse(endTime), description);


        AppointmentDAO appointmentDAO = new InMemoryAppointmentDAO(new InMemoryDatabase());
        AppointmentService appointmentService = new AppointmentServiceImpl(appointmentDAO) {
            @Override
            public Collection<Appointment> getAllAppointments() {
                return appointmentDAO.findAll();
            }

            @Override
            public Appointment getAppointmentById(Integer id) {
                return appointmentDAO.get(id);
            }

        };
        appointmentService.addAppointment(appointment);
        response.sendRedirect(request.getContextPath() + "/success.jsp");
    }


    protected void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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

    protected void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
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
