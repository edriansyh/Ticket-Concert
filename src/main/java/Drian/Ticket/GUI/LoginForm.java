package Drian.Ticket.GUI;

import Drian.Ticket.Model.User;
import Drian.Ticket.Service.UserService;

import javax.swing.*;
import java.awt.*;

public class LoginForm extends JFrame {

    private final UserService userService = new UserService();

    public LoginForm() {
        setTitle("Login - Ticket Concert");
        setSize(350, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel lblUser = new JLabel("Username:");
        JTextField txtUser = new JTextField();

        JLabel lblPass = new JLabel("Password:");
        JPasswordField txtPass = new JPasswordField();

        JButton btnLogin = new JButton("Login");

        panel.add(lblUser);
        panel.add(txtUser);
        panel.add(lblPass);
        panel.add(txtPass);
        panel.add(new JLabel());
        panel.add(btnLogin);

        add(panel, BorderLayout.CENTER);

        btnLogin.addActionListener(e -> {

            String username = txtUser.getText();
            String password = new String(txtPass.getPassword());

            User user = userService.login(username, password);

            if (user == null) {
                JOptionPane.showMessageDialog(this, "Login gagal");
                return;
            }

            JOptionPane.showMessageDialog(this, "Login berhasil sebagai " + user.getRole());

            // 🔑 PEMBEDA ADMIN & USER
            if ("admin".equalsIgnoreCase(user.getRole())) {
                new AdminTicketForm().setVisible(true);
            } else {
                new TicketForm(user).setVisible(true);
            }

            dispose();
        });
    }
}
