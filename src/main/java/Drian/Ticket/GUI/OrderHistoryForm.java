package Drian.Ticket.GUI;

import Drian.Ticket.Model.Order;
import Drian.Ticket.Model.User;
import Drian.Ticket.Service.OrderService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class OrderHistoryForm extends JFrame {

    private JTable table;
    private final User user;
    private final OrderService orderService = new OrderService();

    public OrderHistoryForm(User user) {
        this.user = user;

        setTitle("Riwayat Order");
        setSize(600, 350);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        table = new JTable();
        loadOrderHistory();

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    private void loadOrderHistory() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"Order ID", "Ticket ID", "Qty", "Total Harga"}, 0
        );

        List<Order> orders = orderService.getOrdersByUser(user.getId());

        for (Order o : orders) {
            model.addRow(new Object[]{
                    o.getId(),
                    o.getTicketId(),
                    o.getQuantity(),
                    o.getTotalPrice()
            });
        }

        table.setModel(model);
    }
}
