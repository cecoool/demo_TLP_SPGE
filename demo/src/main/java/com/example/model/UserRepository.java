package com.example.model;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.Properties;


public class UserRepository {

    public Connection getConnection() throws SQLException, IOException {

    Properties config = new Properties();

    try (InputStream input = Files.newInputStream(
            Path.of("demo", "config.properties"))) {
        config.load(input);
    }

    return DriverManager.getConnection(
            config.getProperty("db.url"),
            config.getProperty("db.username"),
            config.getProperty("db.password")
    );
}

    public boolean createUser(String username, String email, String password) {
        String sql = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";    // INSERT добавя нов ред в таблицата. id се генерира от базата.


        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);

            return statement.executeUpdate() > 0;
        } catch (SQLException | IOException e) {
            return false;
        }
    }

    public User getUserByUsername(String username) {
        String sql = "SELECT id, username, email, password FROM users WHERE username = ?";

        try (
                Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)
        ) {
            statement.setString(1, username);

            ResultSet result = statement.executeQuery();

            if (result.next()) {
                return new User(
                        result.getInt("id"),
                        result.getString("username"),
                        result.getString("email"),
                        result.getString("password")
                );
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
