package org.transit.app.busmonitoringapp.logic;

import org.transit.app.busmonitoringapp.model.Login;
import org.transit.app.busmonitoringapp.model.Signup;

import java.sql.*;

import static org.transit.app.busmonitoringapp.dbConnection.DBConnection.getConnection;

public class retrieveUserInfo {
    public boolean loginQuery(Login userData) throws SQLException {
        String username = userData.username();
        String password = userData.password();

        try (Connection connection = getConnection()) {
            String query = "SELECT * from ACCOUNT where USERNAME = ? AND PASSWORD = ?";
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public boolean retrieveName(Signup userData) throws SQLException {
        String name = userData.name();

        try (Connection connection = getConnection()) {
            String query = "SELECT name FROM users WHERE username = ?";
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(3, name);

            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }
}

