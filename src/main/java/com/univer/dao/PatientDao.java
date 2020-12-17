package com.univer.dao;

import com.univer.entity.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientDao {

    private static final String GET_ALL_QUERY = "SELECT * FROM patient";
    private static final String GET_ONE_PATIENT = "SELECT * FROM patient WHERE id = ?";
    private static final String DELETE_ONE_PATIENT = "DELETE FROM patient WHERE id = ?";
    private static final String ADD_ONE_PATIENT = "INSERT INTO patient VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String CHANGE_ONE_PATIENT = "UPDATE patient SET first_name = ?, last_name = ?, address = ?, phone_number = ?, date_of_birth = ?, status = ? WHERE id = ?";
    private static final String DELETE_ALL = "DELETE FROM patient";

    private Connection connection;

    public PatientDao(Connection connection) {
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

    public void changeOne(int id, String first_name, String last_name, String address, String phone_number, Date date_of_birth, String status) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_ONE_PATIENT);
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, phone_number);
            preparedStatement.setDate(5, (java.sql.Date) date_of_birth);
            preparedStatement.setString(6, status);
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOne(int id, String first_name, String last_name, String address, String phone_number, Date date_of_birth, String status) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ONE_PATIENT);
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, address);
            preparedStatement.setString(4, phone_number);
            preparedStatement.setDate(5, (java.sql.Date) date_of_birth);
            preparedStatement.setString(6, status);
            preparedStatement.setInt(7, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOne(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ONE_PATIENT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Patient getOne(int id) {
        Patient patient = new Patient();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ONE_PATIENT);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                patient.setId(resultSet.getInt("id"));
                patient.setFirst_name(resultSet.getString("first_name"));
                patient.setLast_name(resultSet.getString("last_name"));
                patient.setAddress(resultSet.getString("address"));
                patient.setPhone_number(resultSet.getString("phone_number"));
                patient.setDate_of_birth(resultSet.getTimestamp("date_of_birth"));
                patient.setStatus(resultSet.getString("status"));
            }
            return patient;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Patient> getAll() {
        List<Patient> patientList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_QUERY);
            while (resultSet.next()) {
                Patient patient = new Patient();
                patient.setId(resultSet.getInt("id"));
                patient.setFirst_name(resultSet.getString("first_name"));
                patient.setLast_name(resultSet.getString("last_name"));
                patient.setAddress(resultSet.getString("address"));
                patient.setPhone_number(resultSet.getString("phone_number"));
                patient.setDate_of_birth(resultSet.getTimestamp("date_of_birth"));
                patient.setStatus(resultSet.getString("status"));
                patientList.add(patient);
            }
            return patientList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
