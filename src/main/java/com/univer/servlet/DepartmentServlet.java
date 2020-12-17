package com.univer.servlet;

import com.univer.connection.ConnectionCreator;
import com.univer.dao.DepartmentDao;
import com.univer.entity.Department;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/department"})
public class DepartmentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    //private HumanDao humanDao = new HumanDao(new ConnectionCreator().createConnection());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/newDepartment":
                showNewFormDepartment(request, response);
                break;
            case "/insertDepartment":
                insertDepartment(request, response);
                break;
            case "/deleteDepartment":
                deleteDepartment(request, response);
                break;
            case "/editDepartment":
                showEditFormDepartment(request, response);
                break;
            case "/updateDepartment":
                updateDepartment(request, response);
                break;
            case "/listDepartment":
                listDepartments(request, response);
                break;
            default:
                listDepartments(request, response);
                break;
        }
    }

    private void homePage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void updateDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DepartmentDao departmentDao = new DepartmentDao(new ConnectionCreator().createConnection());

        int id = Integer.parseInt(request.getParameter("id"));

        int count_of_rooms = Integer.parseInt(request.getParameter("count_of_rooms"));
        String title = request.getParameter("title");

        departmentDao.changeOne(id, title, count_of_rooms);

        response.sendRedirect("/listDepartment");
    }

    private void showEditFormDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DepartmentDao departmentDao = new DepartmentDao(new ConnectionCreator().createConnection());

        int id = Integer.parseInt(request.getParameter("id"));
        Department department = new Department();
        department = departmentDao.getOne(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("department-add.jsp");
        request.setAttribute("department", department);
        dispatcher.forward(request, response);
    }

    private void insertDepartment(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        DepartmentDao departmentDao = new DepartmentDao(new ConnectionCreator().createConnection());

        departmentDao.addOne(Integer.parseInt(request.getParameter("id")), request.getParameter("title"),
                Integer.parseInt(request.getParameter("count_of_rooms")));
        response.sendRedirect("/listDepartment");
    }

    private void showNewFormDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DepartmentDao departmentDao = new DepartmentDao(new ConnectionCreator().createConnection());

        request.setAttribute("departments", departmentDao.getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("department-add.jsp");
        dispatcher.forward(request, response);
    }

    private void listDepartments(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DepartmentDao departmentDao = new DepartmentDao(new ConnectionCreator().createConnection());

        request.setAttribute("departments", departmentDao.getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("department-list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void deleteDepartment(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DepartmentDao departmentDao = new DepartmentDao(new ConnectionCreator().createConnection());

        departmentDao.deleteOne(id);
        response.sendRedirect("/listDepartment");
    }
}
