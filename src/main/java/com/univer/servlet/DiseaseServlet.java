package com.univer.servlet;

import com.univer.connection.ConnectionCreator;
import com.univer.dao.DiseaseDao;
import com.univer.entity.Disease;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/disease"})
public class DiseaseServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    //private HumanDao humanDao = new HumanDao(new ConnectionCreator().createConnection());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/newDisease":
                showNewFormDisease(request, response);
                break;
            case "/insertDisease":
                insertDisease(request, response);
                break;
            case "/deleteDisease":
                deleteDisease(request, response);
                break;
            case "/editDisease":
                showEditFormDisease(request, response);
                break;
            case "/updateDisease":
                updateDisease(request, response);
                break;
            case "/listDisease":
                listDiseases(request, response);
                break;
            default:
                listDiseases(request, response);
                break;
        }
    }

    private void homePage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void updateDisease(HttpServletRequest request, HttpServletResponse response) throws IOException {
        DiseaseDao diseaseDao = new DiseaseDao(new ConnectionCreator().createConnection());

        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String treatment = request.getParameter("treatment");
        String prevention = request.getParameter("prevention");

        diseaseDao.changeOne(id, title, treatment, prevention);

        response.sendRedirect("/listDisease");
    }

    private void showEditFormDisease(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiseaseDao diseaseDao = new DiseaseDao(new ConnectionCreator().createConnection());

        int id = Integer.parseInt(request.getParameter("id"));
        Disease disease;
        disease = diseaseDao.getOne(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("disease-add.jsp");
        request.setAttribute("disease", disease);
        dispatcher.forward(request, response);
    }

    private void insertDisease(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        DiseaseDao diseaseDao = new DiseaseDao(new ConnectionCreator().createConnection());

        diseaseDao.addOne(Integer.parseInt(request.getParameter("id")), request.getParameter("title"),
                request.getParameter("treatment"), request.getParameter("prevention"));
        response.sendRedirect("/listDisease");
    }

    private void showNewFormDisease(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DiseaseDao diseaseDao = new DiseaseDao(new ConnectionCreator().createConnection());

        request.setAttribute("diseases", diseaseDao.getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("disease-add.jsp");
        dispatcher.forward(request, response);
    }

    private void listDiseases(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DiseaseDao diseaseDao = new DiseaseDao(new ConnectionCreator().createConnection());

        request.setAttribute("diseases", diseaseDao.getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("disease-list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void deleteDisease(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        DiseaseDao diseaseDao = new DiseaseDao(new ConnectionCreator().createConnection());

        diseaseDao.deleteOne(id);
        response.sendRedirect("/listDisease");
    }
}
