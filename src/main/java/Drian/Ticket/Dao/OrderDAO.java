package Drian.Ticket.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Drian.Ticket.Config.DatabaseConfig;
import Drian.Ticket.Model.Order;

public class OrderDAO {
    public void insertOrder(int userId, int ticketId, int qty, int total) {
        String sql = """
            INSERT INTO orders(user_id, ticket_id, quantity, total_price)
            VALUES (?, ?, ?, ?)
            """;
            
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, userId);
            ps.setInt(2, ticketId);
            ps.setInt(3, qty);
            ps.setInt(4, total);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createOrder(Order order, Connection conn) throws SQLException {
    String sql = "INSERT INTO orders (user_id, ticket_id, quantity, total_price) VALUES (?, ?, ?, ?)";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, order.getUserId());
        ps.setInt(2, order.getTicketId());
        ps.setInt(3, order.getQuantity());
        ps.setInt(4, order.getTotalPrice());
        ps.executeUpdate();
    }
}

public List<Order> getOrdersByUser(int userId) {
    List<Order> orders = new ArrayList<>();

    String sql = "SELECT * FROM orders WHERE user_id = ?";

    try (Connection conn = DatabaseConfig.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, userId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setUserId(rs.getInt("user_id"));
            order.setTicketId(rs.getInt("ticket_id"));
            order.setQuantity(rs.getInt("quantity"));
            order.setTotalPrice(rs.getInt("total_price"));

            orders.add(order);
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }

    return orders;
    }
}