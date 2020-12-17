package com.univer.servlet;

import com.univer.connection.ConnectionCreator;
import com.univer.dao.DoctorDao;
import com.univer.entity.Doctor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = {"/doctor"})
public class DoctorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    //private HumanDao humanDao = new HumanDao(new ConnectionCreator().createConnection());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/newDoctor":
                showNewFormDoctor(request, response);
                break;
            case "/insertDoctor":
                insertDoctor(request, response);
                break;
            case "/deleteDoctor":
                deleteDoctor(request, response);
                break;
            case "/editDoctor":
                showEditFormDoctor(request, response);
                break;
            case "/updateDoctor":
                updateDoctor(request, response);
                break;
            case "/listDoctor":
                listDoctors(request, response);
                break;
            default:
                listDoctors(request, response);
                break;
        }
    }

    private void homePage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void updateDoctor(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DoctorDao doctorDao = new DoctorDao(new ConnectionCreator().createConnection());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date_of_birth = null;
        int id = Integer.parseInt(request.getParameter("id"));
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String position = request.getParameter("position");
        int department_id = Integer.parseInt(request.getParameter("department_id"));
        try {
            date_of_birth = simpleDateFormat.parse(request.getParameter("date_of_birth"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        doctorDao.changeOne(id, first_name, last_name, position, department_id, date_of_birth);

        response.sendRedirect("/listDoctor");
    }

    private void showEditFormDoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DoctorDao doctorDao = new DoctorDao(new ConnectionCreator().createConnection());

        int id = Integer.parseInt(request.getParameter("id"));
        Doctor doctor;
        doctor = doctorDao.getOne(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("doctor-add.jsp");
        request.setAttribute("doctor", doctor);
        dispatcher.forward(request, response);
    }

    private void insertDoctor(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        DoctorDao doctorDao = new DoctorDao(new ConnectionCreator().createConnection());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            doctorDao.addOne(Integer.parseInt(request.getParameter("id")), request.getParameter("first_name"),
                    request.getParameter("last_name"), request.getParameter("position"),
                    Integer.parseInt(request.getParameter("department_id")),
                    simpleDateFormat.parse(request.getParameter("date_of_birth")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/listDoctor");
    }

    private void showNewFormDoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DoctorDao doctorDao = new DoctorDao(new ConnectionCreator().createConnection());

        request.setAttribute("doctors", doctorDao.getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("doctor-add.jsp");
        dispatcher.forward(request, response);
    }

    private void listDoctors(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DoctorDao doctorDao = new DoctorDao(new ConnectionCreator().createConnection());

        request.setAttribute("doctors", doctorDao.getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("doctor-list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void deleteDoctor(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DoctorDao doctorDao = new DoctorDao(new ConnectionCreator().createConnection());

        doctorDao.deleteOne(id);
        response.sendRedirect("/listDoctor");
    }
}
