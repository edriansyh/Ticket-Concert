package Drian.Ticket.Service;

import Drian.Ticket.Dao.UserDAO;
import Drian.Ticket.Model.User;

public class UserService {

    private final UserDAO userDAO = new UserDAO();

    // LOGIN
    public User login(String username, String password) {
        if (username == null || password == null) {
            return null;
        }
        return userDAO.login(username, password);
    }

    // REGISTER
    public boolean register(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) {
            return false;
        }
        return userDAO.register(username, password);
    }
}