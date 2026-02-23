package Drian.Ticket.GUI;

import Drian.Ticket.Model.Ticket;

import javax.swing.*;
import java.awt.*;

public class PaymentResultDialog extends JDialog {

    public PaymentResultDialog(JFrame parent, Ticket ticket, int qty, int total) {
        super(parent, "Hasil Pembayaran", true);

        setSize(350, 250);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(5, 2, 10, 10));

        add(new JLabel("Nama Konser"));
        add(new JLabel(ticket.getConcertName()));

        add(new JLabel("Harga Tiket"));
        add(new JLabel("Rp " + ticket.getPrice()));

        add(new JLabel("Jumlah"));
        add(new JLabel(String.valueOf(qty)));

        add(new JLabel("Total Bayar"));
        add(new JLabel("Rp " + total));

        JButton btnOk = new JButton("OK");
        btnOk.addActionListener(e -> dispose());

        add(new JLabel());
        add(btnOk);
    }
}
