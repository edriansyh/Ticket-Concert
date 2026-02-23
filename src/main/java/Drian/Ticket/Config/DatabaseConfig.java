package Drian.Ticket.Config;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConfig {

    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/ticket_konser";
    private static final String username = "root";
    private static final String password = "";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException Exception) {
            Exception.printStackTrace();
            System.out.println("Gagal koneksi ke database");
            return null;
        }
    }
}
