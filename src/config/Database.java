package config;

import java.sql.*;


public class Database {
    private static Database instance;
    private Connection connection;
    private String url = "jdbc:postgresql://localhost:5432/greenpulse";
    private String username = "GreenPulse";
    private String password = "";

    private Database() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            throw new SQLException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public static Database getInstance() throws SQLException {
        if (instance == null) {
            instance = new Database();
        } else if (instance.getConnection().isClosed()) {
            instance = new Database();
        }
        return instance;
    }
}
