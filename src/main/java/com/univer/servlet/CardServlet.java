package com.univer.servlet;

import com.univer.connection.ConnectionCreator;
import com.univer.dao.CardDao;
import com.univer.entity.Card;

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

@WebServlet(urlPatterns = {"/newCard", "/insertCard, /deleteCard", "/editCard", "/updateCard", "/listCard", "/deleteCard*"})
public class CardServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    //private HumanDao humanDao = new HumanDao(new ConnectionCreator().createConnection());

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        switch (action) {
            case "/newCard":
                showNewFormCard(request, response);
                break;
            case "/insertCard":
                insertCard(request, response);
                break;
            case "/deleteCard":
                deleteCard(request, response);
                break;
            case "/editCard":
                showEditFormCard(request, response);
                break;
            case "/updateCard":
                updateCard(request, response);
                break;
            case "/listCard":
                listCards(request, response);
                break;
            default:
                listCards(request, response);
                break;
        }
    }

    private void homePage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void updateCard(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CardDao cardDao = new CardDao(new ConnectionCreator().createConnection());

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date receipt_date = null, discharge_date = null;
        int id = Integer.parseInt(request.getParameter("id"));
        try {
            receipt_date = simpleDateFormat.parse(request.getParameter("receipt_date"));
            discharge_date = simpleDateFormat.parse(request.getParameter("discharge_date"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int doctor_id = Integer.parseInt(request.getParameter("doctor_id"));
        int patient_id = Integer.parseInt(request.getParameter("patient_id"));
        int disease_id = Integer.parseInt(request.getParameter("disease_id"));

        cardDao.changeOne(id, receipt_date, discharge_date, doctor_id, patient_id, disease_id);

        response.sendRedirect("/listCard");
    }

    private void showEditFormCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CardDao cardDao = new CardDao(new ConnectionCreator().createConnection());

        int id = Integer.parseInt(request.getParameter("id"));
        Card card = new Card();
        card = cardDao.getOne(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("card-add.jsp");
        request.setAttribute("card", card);
        dispatcher.forward(request, response);
    }

    private void insertCard(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        CardDao cardDao = new CardDao(new ConnectionCreator().createConnection());

        Date receipt_date = null, discharge_date = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");

        try {
            cardDao.addOne(Integer.parseInt(request.getParameter("id")),
                    receipt_date = simpleDateFormat.parse(request.getParameter("receipt_date")),
                    discharge_date = simpleDateFormat.parse(request.getParameter("discharge_date")),
                    Integer.parseInt(request.getParameter("id")), Integer.parseInt(request.getParameter("id")),
                    Integer.parseInt(request.getParameter("id")));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        response.sendRedirect("/listCard");
    }

    private void showNewFormCard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CardDao cardDao = new CardDao(new ConnectionCreator().createConnection());

        request.setAttribute("cards", cardDao.getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("card-add.jsp");
        dispatcher.forward(request, response);
    }

    private void listCards(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CardDao cardDao = new CardDao(new ConnectionCreator().createConnection());

        request.setAttribute("cards", cardDao.getAll());
        RequestDispatcher dispatcher = request.getRequestDispatcher("card-list.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void deleteCard(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        CardDao cardDao = new CardDao(new ConnectionCreator().createConnection());

        cardDao.deleteOne(id);
        response.sendRedirect("/listCard");
    }
}
