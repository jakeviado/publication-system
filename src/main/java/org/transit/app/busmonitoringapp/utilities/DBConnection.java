package org.transit.app.busmonitoringapp.utilities;
import java.sql.*;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static final String ORACLE_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";
    private static final String DBusername = "system";
    private static final String DBpassword = "system";

    public static Connection getConnection() {
        try {
            Class.forName(ORACLE_DRIVER).newInstance();
            return DriverManager.getConnection(url, DBusername, DBpassword);
        } catch (ClassNotFoundException ex) {
            System.out.println("Error has occurred. Driver not found." + ex.getMessage());
        } catch (InstantiationException | IllegalAccessException ex) {
            System.out.println("Error has occurred. Cannot create a database instance." + ex.getMessage());
        } catch (SQLException ex) {
            System.out.println("Error has occurred. Cannot connect to the database." + ex.getMessage());
        }
        return null;
    }
}
