package com.univer.servlet;

import com.univer.connection.ConnectionCreator;
import com.univer.dao.PatientDao;
import com.univer.entity.Patient;

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

@WebServlet(urlPatterns = {"/patient"})
public class PatientServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    //private HumanDao humanDao = new HumanDao(new ConnectionCreator().createConnection());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/newPatient":
                showNewFormPatient(request, response);
                break;
            case "/insertPatient":
                insertPatient(request, response);
                break;
            case "/deletePatient":
                deletePatient(request, response);
                break;
            case "/editPatient":
                showEditFormPatient(request, response);
                break;
            case "/updatePatient":
                updatePatient(request, response);
                break;
            case "/listPatient":
                listPatients(request, response);
                break;
            default:
                listPatients(request, response);
                break;
        }
    }

    private void homePage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void updatePatient(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PatientDao patientDao = new PatientDao(new ConnectionCreator().createConnection());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date_of_birth = null;
        int id = Integer.parseInt(request.getParameter("id"));
        String first_name = request.getParameter("first_name");
        String last_name = request.getParameter("last_name");
        String address = request.getParameter("address");
        String phone_number = request.getParameter("phone_number");
        String status = request.getParameter("status");
        try {
            date_of_birth = simpleDateFormat.parse(request.getParameter("date_of_birth"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        patientDao.changeOne(id, first_name, last_name, address, phone_number, date_of_birth, status);

        response.sendRedirect("/listPatient");
    }

    private void showEditFormPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PatientDao patientDao = new PatientDao(new ConnectionCreator().createConnection());

        int id = Integer.parseInt(request.getParameter("id"));
        Patient patient;
        patient = patientDao.getOne(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("patient-add.jsp");
        request.setAttribute("patient", patient);
        dispatcher.forward(request, response);
    }

    private void insertPatient(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        PatientDao patientDao = new PatientDao(new ConnectionCreator().createConnection());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            patientDao.addOne(Integer.parseInt(request.getParameter("id")), request.getParameter("first_name"),
                    request.getParameter("last_name"), request.getParameter("address"),
                    request.getParameter("phone_number"),
                    simpleDateFormat.parse(request.getParameter("date_of_birth")),
                    request.getParameter("status"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/listPatient");
    }

    private void showNewFormPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PatientDao patientDao = new PatientDao(new ConnectionCreator().createConnection());

        request.setAttribute("patients", patientDao.getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("patient-add.jsp");
        dispatcher.forward(request, response);
    }

    private void listPatients(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PatientDao patientDao = new PatientDao(new ConnectionCreator().createConnection());

        request.setAttribute("patients", patientDao.getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("patient-list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void deletePatient(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        PatientDao patientDao = new PatientDao(new ConnectionCreator().createConnection());

        patientDao.deleteOne(id);
        response.sendRedirect("/listPatient");
    }
}
