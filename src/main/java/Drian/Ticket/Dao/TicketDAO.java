package Drian.Ticket.Dao;

import Drian.Ticket.Config.DatabaseConfig;
import Drian.Ticket.Model.Ticket;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TicketDAO {
    // AMBIL SEMUA TIKET
    public List<Ticket> getAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        String sql = "SELECT * FROM tickets";

        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Ticket t = new Ticket();
                t.setId(rs.getInt("id"));
                t.setConcertName(rs.getString("concert_name"));
                t.setLocation(rs.getString("location"));
                t.setConcertDate(rs.getDate("concert_date"));
                t.setPrice(rs.getInt("price"));
                t.setStock(rs.getInt("stock"));
                tickets.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tickets;
    }

    // AMBIL TIKET BERDASARKAN ID
    public Ticket getTicketById(int id) {
        String sql = "SELECT * FROM tickets WHERE id=?";
        Ticket t = null;

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                t = new Ticket();
                t.setId(rs.getInt("id"));
                t.setConcertName(rs.getString("concert_name"));
                t.setPrice(rs.getInt("price"));
                t.setStock(rs.getInt("stock"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    // UPDATE STOK TIKET
    public void updateStock(int ticketId, int newStock) {
        String sql = "UPDATE tickets SET stock=? WHERE id=?";

        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, newStock);
            ps.setInt(2, ticketId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CREATE TIKET (ADMIN)
    public void addTicket(Ticket t) {
    String sql = "INSERT INTO tickets (concert_name, location, concert_date, price, stock) VALUES (?, ?, ?, ?, ?)";

    try (Connection conn = DatabaseConfig.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, t.getConcertName());
        ps.setString(2, t.getLocation());
        ps.setDate(3, t.getConcertDate());
        ps.setInt(4, t.getPrice());
        ps.setInt(5, t.getStock());

        ps.executeUpdate();
        System.out.println("✅ Tiket berhasil ditambahkan");

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    // UPDATE DATA TIKET (ADMIN)
public void updateTicket(Ticket t) {
    String sql = """
        UPDATE tickets
        SET concert_name=?, location=?, concert_date=?, price=?, stock=?
        WHERE id=?
    """;

    try (Connection conn = DatabaseConfig.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setString(1, t.getConcertName());
        ps.setString(2, t.getLocation());
        ps.setDate(3, t.getConcertDate());
        ps.setInt(4, t.getPrice());
        ps.setInt(5, t.getStock());
        ps.setInt(6, t.getId());

        ps.executeUpdate();
        System.out.println("✅ Tiket berhasil diupdate");

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    // DELETE TIKET (ADMIN)
    public void deleteTicket(int id) {
    String sql = "DELETE FROM tickets WHERE id=?";

    try (Connection conn = DatabaseConfig.getConnection();
         PreparedStatement ps = conn.prepareStatement(sql)) {

        ps.setInt(1, id);
        ps.executeUpdate();
        System.out.println("✅ Tiket berhasil dihapus");

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    public void updateStock(int ticketId, int newStock, Connection conn) throws SQLException {
    String sql = "UPDATE tickets SET stock=? WHERE id=?";

    try (PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setInt(1, newStock);
        ps.setInt(2, ticketId);
        ps.executeUpdate();
    }
}

}
