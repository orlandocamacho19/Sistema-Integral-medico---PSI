package control;

import DAO.UserDAO;
import Domain.User;

public class ControlUser {

    public Boolean addUser(User user) {
        UserDAO ud = new UserDAO();
        if (ud.findID(user)) {
            return false;
        } else {
            ud.insert(user);
            return true;
        }
    }

    public Boolean editUser(User user) {
        UserDAO ud = new UserDAO();
        for (int i = 0; i < ud.consultAll().size(); i++) {
            if (user.getId() == ud.consultAll().get(i).getId()) {
                ud.update(ud.consultAll().get(i).getId(), user);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteUser(User user) {
        UserDAO ud = new UserDAO();
        for (int i = 0; i < ud.consultAll().size(); i++) {
            if (user.getId() == ud.consultAll().get(i).getId()) {
                ud.delete(user.getId());
                return true;
            }
        }
        return false;
    }

    public void viewUsers() {
        UserDAO ud = new UserDAO();
        if (ud.consultAll().isEmpty()) {
            System.out.println("The database has not users at this time");
        } else {
            System.out.println(ud.consultAll());
        }
    }

    public Boolean login(String email, String password) {
        UserDAO ud = new UserDAO();
        for (int i = 0; i < ud.consultAll().size(); i++) {
            if (ud.consultAll().get(i).getEmail().equals(email) && ud.consultAll().get(i).getPassword().equals(password)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
