package Drian.Ticket.GUI;

import Drian.Ticket.Model.Ticket;
import Drian.Ticket.Service.TicketService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminTicketForm extends JFrame {

    private final TicketService ticketService = new TicketService();
    private JTable table;
    private DefaultTableModel model;

    private JTextField txtNama, txtLokasi, txtTanggal, txtHarga, txtStok;

    public AdminTicketForm() {
        setTitle("Admin - CRUD Tiket Konser");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ===== TABLE =====
        model = new DefaultTableModel(
                new Object[]{"ID", "Nama", "Lokasi", "Tanggal", "Harga", "Stok"}, 0
        );
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // ===== FORM INPUT =====
        JPanel form = new JPanel(new GridLayout(3, 4, 10, 10));
        form.setBorder(BorderFactory.createTitledBorder("Form Tiket"));

        txtNama = new JTextField();
        txtLokasi = new JTextField();
        txtTanggal = new JTextField();
        txtHarga = new JTextField();
        txtStok = new JTextField();

        form.add(new JLabel("Nama Konser"));
        form.add(txtNama);
        form.add(new JLabel("Lokasi"));
        form.add(txtLokasi);

        form.add(new JLabel("Tanggal (yyyy-mm-dd)"));
        form.add(txtTanggal);
        form.add(new JLabel("Harga"));
        form.add(txtHarga);

        form.add(new JLabel("Stok"));
        form.add(txtStok);
        form.add(new JLabel()); // kosong

        add(form, BorderLayout.NORTH);

        // ===== BUTTON =====
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        JButton btnTambah = new JButton("Tambah");
        JButton btnUpdate = new JButton("Update");
        JButton btnHapus = new JButton("Hapus");

        buttons.add(btnTambah);
        buttons.add(btnUpdate);
        buttons.add(btnHapus);

        add(buttons, BorderLayout.SOUTH);

        // ===== EVENT =====
        btnTambah.addActionListener(e -> tambahTicket());
        btnUpdate.addActionListener(e -> updateTicket());
        btnHapus.addActionListener(e -> deleteTicket());

        table.getSelectionModel().addListSelectionListener(e -> isiFormDariTabel());

        loadData();
    }

    // ===== LOAD DATA =====
    private void loadData() {
        model.setRowCount(0);
        List<Ticket> tickets = ticketService.getAllTickets();

        for (Ticket t : tickets) {
            model.addRow(new Object[]{
                    t.getId(),
                    t.getConcertName(),
                    t.getLocation(),
                    t.getConcertDate(),
                    t.getPrice(),
                    t.getStock()
            });
        }
    }

    // ===== TAMBAH =====
    private void tambahTicket() {
        try {
            Ticket t = new Ticket();
            t.setConcertName(txtNama.getText());
            t.setLocation(txtLokasi.getText());
            t.setConcertDate(java.sql.Date.valueOf(txtTanggal.getText()));
            t.setPrice(Integer.parseInt(txtHarga.getText()));
            t.setStock(Integer.parseInt(txtStok.getText()));

            ticketService.addTicket(t);
            loadData();
            clearForm();

            JOptionPane.showMessageDialog(this, "Tiket berhasil ditambahkan");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Input tidak valid");
        }
    }

    // ===== UPDATE =====
    private void updateTicket() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih tiket dulu");
            return;
        }

        try {
            int id = (int) model.getValueAt(row, 0);

            Ticket t = new Ticket();
            t.setId(id);
            t.setConcertName(txtNama.getText());
            t.setLocation(txtLokasi.getText());
            t.setConcertDate(java.sql.Date.valueOf(txtTanggal.getText()));
            t.setPrice(Integer.parseInt(txtHarga.getText()));
            t.setStock(Integer.parseInt(txtStok.getText()));

            ticketService.updateTicket(t);
            loadData();
            clearForm();

            JOptionPane.showMessageDialog(this, "Tiket berhasil diupdate");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Input tidak valid");
        }
    }

    // ===== DELETE =====
    private void deleteTicket() {
        int row = table.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Pilih tiket dulu");
            return;
        }

        int id = (int) model.getValueAt(row, 0);

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Yakin ingin menghapus tiket ini?",
                "Konfirmasi",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            ticketService.deleteTicket(id);
            loadData();
            clearForm();
        }
    }

    // ===== ISI FORM =====
    private void isiFormDariTabel() {
        int row = table.getSelectedRow();
        if (row != -1) {
            txtNama.setText(model.getValueAt(row, 1).toString());
            txtLokasi.setText(model.getValueAt(row, 2).toString());
            txtTanggal.setText(model.getValueAt(row, 3).toString());
            txtHarga.setText(model.getValueAt(row, 4).toString());
            txtStok.setText(model.getValueAt(row, 5).toString());
        }
    }

    private void clearForm() {
        txtNama.setText("");
        txtLokasi.setText("");
        txtTanggal.setText("");
        txtHarga.setText("");
        txtStok.setText("");
    }
}
