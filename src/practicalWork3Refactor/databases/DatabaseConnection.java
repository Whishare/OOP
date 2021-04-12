package practicalWork3Refactor.databases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static DatabaseConnection instance;
    public Connection connection;

    private DatabaseConnection() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:c:/sqlite/FlatHouse.db");
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        }
        return instance;
    }
}
