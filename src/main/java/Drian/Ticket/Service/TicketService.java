package Drian.Ticket.Service;

import Drian.Ticket.Dao.TicketDAO;
import Drian.Ticket.Model.Ticket;

import java.util.List;

public class TicketService {

    private final TicketDAO ticketDAO = new TicketDAO();

    // READ (USER & ADMIN)
    public List<Ticket> getAllTickets() {
        return ticketDAO.getAllTickets();
    }

    // READ BY ID (ORDER)
    public Ticket getTicketById(int id) {
        return ticketDAO.getTicketById(id);
    }

    // CREATE (ADMIN)
    public void addTicket(Ticket ticket) {
        ticketDAO.addTicket(ticket);
    }

    // UPDATE (ADMIN)
    public void updateTicket(Ticket ticket) {
        ticketDAO.updateTicket(ticket);
    }

    // DELETE (ADMIN)
    public void deleteTicket(int id) {
        ticketDAO.deleteTicket(id);
    }
}
