package com.univer.dao;


import com.univer.entity.Doctor;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DoctorDao {

    private static final String GET_ALL_QUERY = "SELECT * FROM doctor";
    private static final String GET_ONE_DOCTOR = "SELECT * FROM doctor WHERE id = ?";
    private static final String DELETE_ONE_DOCTOR = "DELETE FROM doctor WHERE id = ?";
    private static final String ADD_ONE_DOCTOR = "INSERT INTO doctor VALUES (?, ?, ?, ?, ?, ?)";
    private static final String CHANGE_ONE_DOCTOR = "UPDATE doctor SET first_name = ?, last_name = ?, position = ?, department_id = ?, date_of_birth = ? WHERE id = ?";
    private static final String DELETE_ALL = "DELETE FROM doctor";

    private Connection connection;

    public DoctorDao(Connection connection) {
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

    public void changeOne(int id, String first_name, String last_name, String position, int department_id, Date date_of_birth) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_ONE_DOCTOR);
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, position);
            preparedStatement.setInt(4, department_id);
            preparedStatement.setDate(5, (java.sql.Date) date_of_birth);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOne(int id, String first_name, String last_name, String position, int department_id, Date date_of_birth) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ONE_DOCTOR);
            preparedStatement.setString(1, first_name);
            preparedStatement.setString(2, last_name);
            preparedStatement.setString(3, position);
            preparedStatement.setInt(4, department_id);
            preparedStatement.setDate(5, (java.sql.Date) date_of_birth);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOne(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ONE_DOCTOR);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Doctor getOne(int id) {
        Doctor doctor = new Doctor();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ONE_DOCTOR);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                doctor.setId(resultSet.getInt("id"));
                doctor.setFirst_name(resultSet.getString("first_name"));
                doctor.setLast_name(resultSet.getString("last_name"));
                doctor.setPosition(resultSet.getString("position"));
                doctor.setDepartment_id(resultSet.getInt("department_id"));
                doctor.setDate_of_birth(resultSet.getTimestamp("date_of_birth"));
            }
            return doctor;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Doctor> getAll() {
        List<Doctor> doctorList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_QUERY);
            while (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(resultSet.getInt("id"));
                doctor.setFirst_name(resultSet.getString("first_name"));
                doctor.setLast_name(resultSet.getString("last_name"));
                doctor.setPosition(resultSet.getString("position"));
                doctor.setDepartment_id(resultSet.getInt("department_id"));
                doctor.setDate_of_birth(resultSet.getTimestamp("date_of_birth"));
                doctorList.add(doctor);
            }
            return doctorList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
