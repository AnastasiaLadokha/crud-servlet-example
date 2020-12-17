package com.univer.dao;


import com.univer.entity.Card;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CardDao {

    private static final String GET_ALL_QUERY = "SELECT * FROM card";
    private static final String GET_ONE_CARD = "SELECT * FROM card WHERE id = ?";
    private static final String DELETE_ONE_CARD = "DELETE FROM card WHERE id = ?";
    private static final String ADD_ONE_CARD = "INSERT INTO card VALUES (?, ?, ?, ?, ?, ?)";
    private static final String CHANGE_ONE_CARD = "UPDATE card SET receipt_date = ?, discharge_date = ?, doctor_id = ?, patient_id = ?, disease_id = ? WHERE id = ?";
    private static final String DELETE_ALL = "DELETE FROM card";

    private Connection connection;

    public CardDao(Connection connection) {
        this.connection = connection;
    }

    public void deleteAll() {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void changeOne(int id, Date receipt_date, Date discharge_date, int doctor_id, int patient_id, int disease_id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_ONE_CARD);
            preparedStatement.setDate(1, (java.sql.Date) receipt_date);
            preparedStatement.setDate(2, (java.sql.Date) discharge_date);
            preparedStatement.setInt(3, doctor_id);
            preparedStatement.setInt(4, patient_id);
            preparedStatement.setInt(5, disease_id);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOne(int id, Date receipt_date, Date discharge_date, int doctor_id, int patient_id, int disease_id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ONE_CARD);
            preparedStatement.setDate(1, (java.sql.Date) receipt_date);
            preparedStatement.setDate(2, (java.sql.Date) discharge_date);
            preparedStatement.setInt(3, doctor_id);
            preparedStatement.setInt(4, patient_id);
            preparedStatement.setInt(5, disease_id);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOne(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ONE_CARD);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Card getOne(int id) {
        Card card = new Card();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ONE_CARD);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                card.setId(resultSet.getInt("id"));
                card.setReceipt_date(resultSet.getTimestamp("receipt_date"));
                card.setDischarge_date(resultSet.getTimestamp("discharge_date"));
                card.setDoctor_id(resultSet.getInt("doctor_id"));
                card.setPatient_id(resultSet.getInt("patient_id"));
                card.setDisease_id(resultSet.getInt("disease_id"));
            }
            return card;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Card> getAll() {
        List<Card> cardList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_QUERY);
            while (resultSet.next()) {
                Card card = new Card();
                card.setId(resultSet.getInt("id"));
                card.setReceipt_date(resultSet.getTimestamp("receipt_date"));
                card.setDischarge_date(resultSet.getTimestamp("discharge_date"));
                card.setDoctor_id(resultSet.getInt("doctor_id"));
                card.setPatient_id(resultSet.getInt("patient_id"));
                card.setDisease_id(resultSet.getInt("disease_id"));
                cardList.add(card);
            }
            return cardList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
