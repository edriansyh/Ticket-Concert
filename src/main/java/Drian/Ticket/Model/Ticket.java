package Drian.Ticket.Model;

import java.sql.Date;

public class Ticket {

    private int id;
    private String concertName;
    private String location;
    private Date concertDate;   // ⬅️ INI YANG KURANG
    private int price;
    private int stock;

    // ====== CONSTRUCTOR KOSONG ======
    public Ticket() {}

    // ====== CONSTRUCTOR ADMIN ======
    public Ticket(String concertName, String location, Date concertDate, int price, int stock) {
        this.concertName = concertName;
        this.location = location;
        this.concertDate = concertDate;
        this.price = price;
        this.stock = stock;
    }

    // ====== CONSTRUCTOR UPDATE ======
    public Ticket(int id, String concertName, String location, Date concertDate, int price, int stock) {
        this.id = id;
        this.concertName = concertName;
        this.location = location;
        this.concertDate = concertDate;
        this.price = price;
        this.stock = stock;
    }

    // ====== GETTER & SETTER ======
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConcertName() {
        return concertName;
    }

    public void setConcertName(String concertName) {
        this.concertName = concertName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getConcertDate() {
        return concertDate;
    }

    public void setConcertDate(Date concertDate) {
        this.concertDate = concertDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
