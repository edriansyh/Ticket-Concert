package Drian.Ticket.GUI;

import Drian.Ticket.Model.Ticket;
import Drian.Ticket.Model.User;
import Drian.Ticket.Service.OrderService;
import Drian.Ticket.Service.TicketService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TicketForm extends JFrame {

    private JTable table;
    private JTextField txtQty;
    private final User user;

    private final TicketService ticketService = new TicketService();
    private final OrderService orderService = new OrderService();

    public TicketForm(User user) {
        this.user = user;

        setTitle("Order Tiket Konser");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        table = new JTable();
        loadTicketData();

        add(new JScrollPane(table), BorderLayout.CENTER);

        // ===== PANEL BAWAH =====
        JPanel bottomPanel = new JPanel();

        bottomPanel.add(new JLabel("Jumlah:"));
        txtQty = new JTextField(5);
        bottomPanel.add(txtQty);

        JButton btnOrder = new JButton("Order");
        JButton btnHistory = new JButton("Riwayat Order");

        bottomPanel.add(btnOrder);
        bottomPanel.add(btnHistory);

        add(bottomPanel, BorderLayout.SOUTH);

        // ===== ACTION =====
        btnOrder.addActionListener(e -> orderTicket());
        btnHistory.addActionListener(e ->
                new OrderHistoryForm(user).setVisible(true)
        );
    }

    private void loadTicketData() {
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "Nama Konser", "Harga", "Stok"}, 0
        );

        List<Ticket> tickets = ticketService.getAllTickets();
        for (Ticket t : tickets) {
            model.addRow(new Object[]{
                    t.getId(),
                    t.getConcertName(),
                    t.getPrice(),
                    t.getStock()
            });
        }

        table.setModel(model);
    }

    private void orderTicket() {
        int row = table.getSelectedRow();

        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih tiket terlebih dahulu!");
            return;
        }

        int ticketId = (int) table.getValueAt(row, 0);
        String concertName = table.getValueAt(row, 1).toString();
        int price = (int) table.getValueAt(row, 2);

        int qty;
        try {
            qty = Integer.parseInt(txtQty.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Jumlah tidak valid!");
            return;
        }

        // PROSES ORDER (SERVICE TIDAK DIUBAH)
        orderService.orderTicket(user.getId(), ticketId, qty);

        int total = price * qty;

        // HASIL PEMBAYARAN (GUI)
        JOptionPane.showMessageDialog(this,
                "ORDER BERHASIL\n\n"
                        + "Konser : " + concertName + "\n"
                        + "Harga : Rp " + price + "\n"
                        + "Jumlah : " + qty + "\n"
                        + "Total : Rp " + total,
                "Hasil Pembayaran",
                JOptionPane.INFORMATION_MESSAGE
        );

        loadTicketData(); // refresh stok
    }
}
