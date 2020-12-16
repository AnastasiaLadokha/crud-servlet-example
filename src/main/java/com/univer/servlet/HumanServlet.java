package com.univer.servlet;

import com.univer.connection.ConnectionCreator;
import com.univer.dao.HumanDao;
import com.univer.entity.Human;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/")
public class HumanServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    //private HumanDao humanDao = new HumanDao(new ConnectionCreator().createConnection());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            /*case "/new":
                listHumans(request, response);*/
               /* break;*/
            /*case "/insert":
                insertBook(request, response);
                break;*/
            case "/delete":
                deleteHuman(request, response);
                break;
            /*case "/edit":
                showEditForm(request, response);
                break;
            case "/update":
                updateBook(request, response);
                break;*/
            case "/list":
                listHumans(request, response);
                break;
            default:
                listHumans(request, response);
                break;
        }
    }

    private void listHumans(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HumanDao humanDao = new HumanDao(new ConnectionCreator().createConnection());

        request.setAttribute("humans", humanDao.getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }

    private void deleteHuman(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        HumanDao humanDao = new HumanDao(new ConnectionCreator().createConnection());

        Human human = new Human();
        human.setId(id);

        humanDao.deleteOne(human);
        response.sendRedirect("list");
    }
}
