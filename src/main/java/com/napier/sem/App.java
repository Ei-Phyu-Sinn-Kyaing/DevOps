package com.napier.sem;

import java.sql.*;

public class App {
    public static void main(String[] args) {
        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        String dbHost = System.getenv("DB_HOST");
        String dbPort = System.getenv("DB_PORT");
        String dbName = System.getenv("DB_NAME");
        String dbUser = System.getenv("DB_USER");
        String dbPass = System.getenv("DB_PASSWORD");

        String jdbcUrl = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName +
                "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";

        Connection con = null;
        int retries = 20;  // enough retries for DB startup
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database... attempt " + (i + 1));
            try {
                con = DriverManager.getConnection(jdbcUrl, dbUser, dbPass);
                System.out.println("Successfully connected to database!");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed attempt " + (i + 1) + ": " + sqle.getMessage());
                try { Thread.sleep(5000); } catch (InterruptedException ignored) {}
            }
        }

        if (con != null) {
            try {
                con.close();
                System.out.println("Connection closed.");
            } catch (SQLException e) {
                System.out.println("Error closing connection: " + e.getMessage());
            }
        } else {
            System.out.println("Could not connect to database after multiple attempts.");
        }
    }
}
