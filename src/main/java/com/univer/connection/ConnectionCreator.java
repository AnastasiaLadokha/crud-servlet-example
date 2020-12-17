package com.univer.connection;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionCreator {


    public ConnectionCreator() {

    }

    public Connection createConnection() {
        /*Properties properties = new Properties();
        try {
            properties.load(this.getClass().getResourceAsStream("com/univer/db.properties"));
        } catch (IOException e) {
            e.printStackTrace();

        }*/
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        String url = "jdbc:postgresql://localhost:5432/hospital";      //properties.getProperty("db.url");
        String username = "postgres";  //properties.getProperty("db.username");
        String password = "2509";  //properties.getProperty("db.password");
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
