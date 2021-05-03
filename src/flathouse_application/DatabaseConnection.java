/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package whishare.flathouse_application;

/**
 *
 * @author nicol
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    public Connection connection;

    private DatabaseConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:c:/sqlite/gui_flathouse.db");
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}