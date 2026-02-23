package Drian.Ticket;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import java.sql.Connection;


public class jdbcTest {
    @Test
    public void jdbcConnect() {
        try {
            Driver mysqlDriver = new com.mysql.cj.jdbc.Driver();
            DriverManager.registerDriver(mysqlDriver);

        } catch (SQLException Exception) {
            Exception.printStackTrace();
    }

    String jdbcUrl = "jdbc:mysql://localhost:3306/ticket_konser";
    String username = "root";
    String password = "";
    try (Connection conn = DriverManager.getConnection(jdbcUrl, username, password)) {
        System.out.println("Database successfully connected!");
    } catch (SQLException Exception) {
        Assertions.fail(Exception);
    }
}
}
