package org.transit.app.busmonitoringapp.logic;

import org.transit.app.busmonitoringapp.model.Login;

import java.sql.*;

import static org.transit.app.busmonitoringapp.dbConnection.DBConnection.getConnection;

public class login {
    public boolean loginQuery(Login userData) throws SQLException {
        String username = userData.getUsername();
        String password = userData.getPassword();

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
}

