package org.transit.app.busmonitoringapp.logic;

import org.transit.app.busmonitoringapp.model.Signup;
import java.sql.*;

import static org.transit.app.busmonitoringapp.dbConnection.DBConnection.getConnection;

public class registerUserInfo {
    public boolean registerUser(Signup userData) throws SQLException {
        String name = userData.name();
        String username = userData.username();
        String password = userData.password();

        try (Connection connection = getConnection()) {
            String query = "INSERT INTO ACCOUNT (USERNAME, PASSWORD, NAME) VALUES (?, ?, ?)";
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, name);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected == 1;
        }
    }
}
