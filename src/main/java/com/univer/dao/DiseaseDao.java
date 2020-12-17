package com.univer.dao;


import com.univer.entity.Disease;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiseaseDao {

    private static final String GET_ALL_QUERY = "SELECT * FROM disease";
    private static final String GET_ONE_DISEASE = "SELECT * FROM disease WHERE id = ?";
    private static final String DELETE_ONE_DISEASE = "DELETE FROM disease WHERE id = ?";
    private static final String ADD_ONE_DISEASE = "INSERT INTO disease VALUES (?, ?, ?, ?)";
    private static final String CHANGE_ONE_DISEASE = "UPDATE disease SET title = ?, treatment = ?, prevention = ? WHERE id = ?";
    private static final String DELETE_ALL = "DELETE FROM disease";

    private Connection connection;

    public DiseaseDao(Connection connection) {
        this.connection = connection;
    }

    public void deleteAll(){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL);
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void changeOne(int id, String title, String treatment, String prevention) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_ONE_DISEASE);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, treatment);
            preparedStatement.setString(3, prevention);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addOne(int id, String title, String treatment, String prevention) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(ADD_ONE_DISEASE);
            preparedStatement.setString(1, title);
            preparedStatement.setString(2, treatment);
            preparedStatement.setString(3, prevention);
            preparedStatement.setInt(4, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteOne(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ONE_DISEASE);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Disease getOne(int id) {
        Disease disease = new Disease();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ONE_DISEASE);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                disease.setId(resultSet.getInt("id"));
                disease.setTitle(resultSet.getString("title"));
                disease.setTreatment(resultSet.getString("treatment"));
                disease.setPrevention(resultSet.getString("prevention"));
            }
            return disease;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Disease> getAll() {
        List<Disease> diseaseList = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_QUERY);
            while (resultSet.next()) {
                Disease disease = new Disease();
                disease.setId(resultSet.getInt("id"));
                disease.setTitle(resultSet.getString("title"));
                disease.setTreatment(resultSet.getString("treatment"));
                disease.setPrevention(resultSet.getString("prevention"));
                diseaseList.add(disease);
            }
            return diseaseList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
