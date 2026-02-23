package Drian.Ticket.App;

import Drian.Ticket.Dao.TicketDAO;
import Drian.Ticket.Dao.UserDAO;
import Drian.Ticket.Model.Ticket;
import Drian.Ticket.Model.User;
import Drian.Ticket.Service.OrderService;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final UserDAO userDAO = new UserDAO();
    private static final TicketDAO ticketDAO = new TicketDAO();
    private static final OrderService orderService = new OrderService();

    private static User loginUser = null;

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n=== SISTEM ORDER TIKET KONSER ===");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("0. Keluar");
            System.out.print("Pilih menu: ");
            int pilih = scanner.nextInt();
            scanner.nextLine();

            switch (pilih) {
                case 1 -> login();
                case 2 -> register();
                case 0 -> {
                    System.out.println("Terima kasih 🙏");
                    System.exit(0);
                }
                default -> System.out.println("Menu tidak valid!");
            }
        }
    }

    // ================= LOGIN =================
    private static void login() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        loginUser = userDAO.login(username, password);

        if (loginUser != null) {
            System.out.println("✅ Login berhasil!");

            if (loginUser.getRole().equalsIgnoreCase("ADMIN")) {
                menuAdmin();
            } else {
                menuUser();
            }

        } else {
            System.out.println("❌ Username atau password salah");
        }
    }

    // ================= REGISTER =================
    private static void register() {
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User user = new User(username, password, "USER");
        userDAO.register(user);

        System.out.println("✅ Registrasi berhasil, silakan login");
    }

    // ================= MENU USER =================
    private static void menuUser() {
        while (true) {
            System.out.println("\n=== MENU USER ===");
            System.out.println("1. Lihat Tiket Konser");
            System.out.println("2. Pesan Tiket");
            System.out.println("0. Logout");
            System.out.print("Pilih menu: ");
            int pilih = scanner.nextInt();
            scanner.nextLine();

            switch (pilih) {
                case 1 -> lihatTiket();
                case 2 -> pesanTiket();
                case 0 -> {
                    loginUser = null;
                    System.out.println("Logout berhasil");
                    return;
                }
                default -> System.out.println("Menu tidak valid!");
            }
        }
    }

    // ================= MENU ADMIN =================
    private static void menuAdmin() {
        while (true) {
            System.out.println("\n=== MENU ADMIN ===");
            System.out.println("1. Lihat Tiket");
            System.out.println("2. Tambah Tiket");
            System.out.println("3. Update Tiket");
            System.out.println("4. Hapus Tiket");
            System.out.println("0. Logout");
            System.out.print("Pilih menu: ");

            int pilih = scanner.nextInt();
            scanner.nextLine();

            switch (pilih) {
                case 1 -> lihatTiket();
                case 2 -> tambahTiket();
                case 3 -> updateTiket();
                case 4 -> hapusTiket();
                case 0 -> {
                    loginUser = null;
                    System.out.println("Logout admin");
                    return;
                }
                default -> System.out.println("Menu tidak valid!");
            }
        }
    }

    // ================= LIHAT TIKET =================
    private static void lihatTiket() {
        List<Ticket> tickets = ticketDAO.getAllTickets();

        System.out.println("\n=== DAFTAR TIKET KONSER ===");
        for (Ticket t : tickets) {
            System.out.println(
                    t.getId() + ". " +
                    t.getConcertName() +
                    " | " + t.getLocation() +
                    " | Tanggal: " + t.getConcertDate() +
                    " | Harga: " + t.getPrice() +
                    " | Stok: " + t.getStock()
            );
        }
    }

    // ================= PESAN TIKET =================
    private static void pesanTiket() {
        lihatTiket();

        System.out.print("\nMasukkan ID Tiket: ");
        int ticketId = scanner.nextInt();
        System.out.print("Jumlah tiket: ");
        int qty = scanner.nextInt();
        scanner.nextLine();

        orderService.orderTicket(loginUser.getId(), ticketId, qty);
    }

    // ================= ADMIN - TAMBAH TIKET =================
    private static void tambahTiket() {
        System.out.print("Nama konser: ");
        String nama = scanner.nextLine();

        System.out.print("Lokasi: ");
        String lokasi = scanner.nextLine();

        System.out.print("Tanggal konser (yyyy-mm-dd): ");
        String tanggal = scanner.nextLine();
        Date concertDate = Date.valueOf(tanggal);

        System.out.print("Harga: ");
        int harga = scanner.nextInt();

        System.out.print("Stok: ");
        int stok = scanner.nextInt();
        scanner.nextLine();

        Ticket t = new Ticket(nama, lokasi, concertDate, harga, stok);
        ticketDAO.addTicket(t);
    }

    // ================= ADMIN - UPDATE TIKET =================
    private static void updateTiket() {
        lihatTiket();

        System.out.print("ID tiket: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Nama konser baru: ");
        String nama = scanner.nextLine();

        System.out.print("Lokasi baru: ");
        String lokasi = scanner.nextLine();

        System.out.print("Tanggal konser baru (yyyy-mm-dd): ");
        String tanggal = scanner.nextLine();
        Date concertDate = Date.valueOf(tanggal);

        System.out.print("Harga baru: ");
        int harga = scanner.nextInt();

        System.out.print("Stok baru: ");
        int stok = scanner.nextInt();
        scanner.nextLine();

        Ticket t = new Ticket(id, nama, lokasi, concertDate, harga, stok);
        ticketDAO.updateTicket(t);
    }

    // ================= ADMIN - HAPUS TIKET =================
    private static void hapusTiket() {
        lihatTiket();

        System.out.print("ID tiket yang dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        ticketDAO.deleteTicket(id);
    }
}
