package Drian.Ticket.Model;

import java.sql.Timestamp;

public class Order {
    private int id;
    private int userId;
    private int ticketId;
    private int quantity;
    private int totalPrice;
    private Timestamp orderDate;

    public Order() {}

    public Order(int userId, int ticketId, int quantity, int totalPrice) {
        this.userId = userId;
        this.ticketId = ticketId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    // getter & setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }
}
