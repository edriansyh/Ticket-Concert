package Drian.Ticket.Service;

import Drian.Ticket.Dao.OrderDAO;
import Drian.Ticket.Dao.TicketDAO;
import Drian.Ticket.Model.Ticket;
import Drian.Ticket.Model.Order;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import Drian.Ticket.Config.DatabaseConfig;

public class OrderService {

    private final TicketDAO ticketDAO = new TicketDAO();
    private final OrderDAO orderDAO = new OrderDAO();

    /**
     * Proses pemesanan tiket
     */
    public void orderTicket(int userId, int ticketId, int qty) {

    Connection conn = null;

    try {
        conn = DatabaseConfig.getConnection();
        conn.setAutoCommit(false); // TRANSACTION START

        Ticket ticket = ticketDAO.getTicketById(ticketId);

        if (ticket == null) {
            System.out.println("❌ Tiket tidak ditemukan");
            return;
        }

        if (ticket.getStock() < qty) {
            System.out.println("❌ Stok tidak mencukupi");
            return;
        }

        int totalHarga = ticket.getPrice() * qty;

        Order order = new Order(userId, ticketId, qty, totalHarga);
        orderDAO.createOrder(order, conn);   // kirim conn
        ticketDAO.updateStock(ticketId, ticket.getStock() - qty, conn);

        conn.commit(); // TRANSACTION SUCCESS

        System.out.println("\n✅ ORDER BERHASIL!");
        System.out.println("Nama Konser : " + ticket.getConcertName());
        System.out.println("Harga Tiket : " + ticket.getPrice());
        System.out.println("Jumlah      : " + qty);
        System.out.println("Total Bayar : " + totalHarga);

    } catch (SQLException e) {
        try {
            if (conn != null) conn.rollback();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        e.printStackTrace();
    }
}

    public List<Order> getOrdersByUser(int userId) {
    return orderDAO.getOrdersByUser(userId);
    }


}
