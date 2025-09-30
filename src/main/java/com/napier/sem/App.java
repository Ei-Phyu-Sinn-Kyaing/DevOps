package com.napier.sem;

import java.sql.*;

public class App {

    private Connection con = null;

    public static void main(String[] args) {
        App app = new App();
        app.connect();
        app.disconnect();
    }

    // Connect method with retry & default values
    public void connect() {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        // Read environment variables with defaults for IntelliJ
        String host = System.getenv("DB_HOST") != null ? System.getenv("DB_HOST") : "localhost";
        String port = System.getenv("DB_PORT") != null ? System.getenv("DB_PORT") : "3306";
        String dbName = System.getenv("DB_NAME") != null ? System.getenv("DB_NAME") : "devopsdb";
        String user = System.getenv("DB_USER") != null ? System.getenv("DB_USER") : "root";
        String pass = System.getenv("DB_PASSWORD") != null ? System.getenv("DB_PASSWORD") : "root";

        String jdbcUrl = "jdbc:mysql://" + host + ":" + port + "/" + dbName +
                "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

        int retries = 20;
        for (int i = 0; i < retries; i++) {
            System.out.println("Connecting to database... attempt " + (i + 1));
            try {
                con = DriverManager.getConnection(jdbcUrl, user, pass);
                System.out.println("Successfully connected to database!");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed attempt " + (i + 1) + ": " + sqle.getMessage());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException ignored) {
                }
            }
        }

        if (con == null) {
            System.out.println("Could not connect to database after multiple attempts.");
            System.exit(-1);
        }
    }

    // Disconnect method
    public void disconnect() {
        if (con != null) {
            try {
                con.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
