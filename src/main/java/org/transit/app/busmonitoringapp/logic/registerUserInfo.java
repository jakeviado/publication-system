package org.transit.app.busmonitoringapp.logic;

import org.transit.app.busmonitoringapp.model.Signup;
import java.sql.*;

import static org.transit.app.busmonitoringapp.utilities.DBConnection.getConnection;

public class registerUserInfo {
    public boolean registerUser(Signup userData) throws SQLException {
        String first_name = userData.first_name();
        String last_name = userData.last_name();
        String email = userData.email();
        String username = userData.username();
        String password = userData.password();

        try (Connection connection = getConnection()) {
            String query = "INSERT INTO USERS (USERNAME, PASSWORD, EMAIL, FIRST_NAME, LAST_NAME) VALUES (?, ?, ?, ?, ?)";
            assert connection != null;
            PreparedStatement stmt = connection.prepareStatement(query);

            stmt.setString(1, username);
            stmt.setString(2, password);
            stmt.setString(3, email);
            stmt.setString(4, first_name);
            stmt.setString(5, last_name);

            int rowsAffected = stmt.executeUpdate();
            return rowsAffected == 1;
        }
    }
}
