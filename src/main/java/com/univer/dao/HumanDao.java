package com.univer.dao;

import com.univer.entity.Human;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class HumanDao {

    private static final String GET_ALL_QUERY = "SELECT * FROM human";
    private static final String GET_ONE_HUMAN = "SELECT * FROM human WHERE id = ?";
    private static final String DELETE_ONE_HUMAN = "DELETE FROM human WHERE id = ?";
    private static final String ADD_ONE_HUMAN = "INSERT INTO human VALUES (?, ?, ?)";
    private static final String CHANGE_ONE_HUMAN = "UPDATE human SET name = ?, surname = ? WHERE id = ?";
    private static final String DELETE_ALL = "DELETE FROM human";

    private Connection connection;

    public HumanDao(Connection connection) {
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

    public boolean changeOne(Human human) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_ONE_HUMAN);
        preparedStatement.setString(1, human.getName());
        preparedStatement.setString(2, human.getSurname());
        preparedStatement.setInt(3, human.getId());

        boolean rowUpdated = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowUpdated;
    }

    public boolean addOne(Human human) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(ADD_ONE_HUMAN);
        preparedStatement.setInt(1, human.getId());
        preparedStatement.setString(2, human.getName());
        preparedStatement.setString(3, human.getSurname());
        boolean rowInserted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();

        return rowInserted;
    }

    public boolean deleteOne(Human human) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ONE_HUMAN);
        preparedStatement.setInt(1, human.getId());

        boolean rowDeleted = preparedStatement.executeUpdate() > 0;
        preparedStatement.close();
        return rowDeleted;
    }

    public Human getOne(int id) throws SQLException {
        Human human = new Human();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ONE_HUMAN);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            human.setId(resultSet.getInt("id"));
            human.setName(resultSet.getString("name"));
            human.setSurname(resultSet.getString("surname"));
        }
        resultSet.close();
        preparedStatement.close();

        return human;
    }

    public List<Human> getAll()  {
        List<Human> humanList = new ArrayList<>();

        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_QUERY);
            while (resultSet.next()) {
                Human human = new Human();
                human.setId(resultSet.getInt("id"));
                human.setName(resultSet.getString("name"));
                human.setSurname(resultSet.getString("surname"));
                humanList.add(human);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return humanList;
    }
}
