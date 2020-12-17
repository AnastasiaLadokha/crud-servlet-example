package com.univer.dao;


import com.univer.entity.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao {

    private static final String GET_ALL_QUERY = "SELECT * FROM department";
    private static final String GET_ONE_DEPARTMENT = "SELECT * FROM department WHERE id = ?";
    private static final String DELETE_ONE_DEPARTMENT = "DELETE FROM department WHERE id = ?";
    private static final String ADD_ONE_DEPARTMENT = "INSERT INTO department VALUES (?, ?, ?)";
    private static final String CHANGE_ONE_DEPARTMENT = "UPDATE department SET title = ?, count_of_rooms = ? WHERE id = ?";
    private static final String DELETE_ALL = "DELETE FROM department";

    private Connection connection;

    public DepartmentDao(Connection connection) {
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

    public void changeOne(int id, String title, int count_of_rooms) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_ONE_DEPARTMENT);
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, count_of_rooms);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOne(int id, String title, int count_of_rooms) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ONE_DEPARTMENT);
            preparedStatement.setString(1, title);
            preparedStatement.setInt(2, count_of_rooms);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOne(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ONE_DEPARTMENT);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Department getOne(int id) {
        Department department = new Department();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ONE_DEPARTMENT);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                department.setId(resultSet.getInt("id"));
                department.setTitle(resultSet.getString("title"));
                department.setCount_of_rooms(resultSet.getInt("count_of_rooms"));
            }
            return department;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Department> getAll() {
        List<Department> departmentList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_QUERY);
            while (resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getInt("id"));
                department.setTitle(resultSet.getString("title"));
                department.setCount_of_rooms(resultSet.getInt("count_of_rooms"));
                departmentList.add(department);
            }
            return departmentList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
