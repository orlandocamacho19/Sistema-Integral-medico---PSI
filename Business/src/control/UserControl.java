package control;

import DAO.UserDAO;
import Domain.User;

public class UserControl {

    private static UserControl userControl;
    private static UserDAO ud;

    /**
     * Constructor
     */
    private UserControl() {
        ud = UserDAO.getInstance();
    }

    /**
     * Return the instance of the class following the singleton pattern
     *
     * @return instance
     */
    public static UserControl getInstance() {
        if (userControl == null) {
            userControl = new UserControl();
        }
        return userControl;
    }

    /**
     * Return if was possible inserts in the database the user received in
     * parameters
     *
     * @param user User
     * @return if was possible add
     */
    public Boolean addUser(User user) {
        if (ud.consultByID(user)) {
            return false;
        } else {
            ud.insert(user);
            return true;
        }
    }

    /**
     * Return if was possible update in the database the user received in
     * parameters
     *
     * @param user User
     * @return if was possible update
     */
    public Boolean editUser(User user) {
        for (int i = 0; i < ud.consultAll().size(); i++) {
            if (user.getId() == ud.consultAll().get(i).getId()) {
                ud.update(ud.consultAll().get(i).getId(), user);
                return true;
            }
        }
        return false;
    }

    /**
     * Return if was possible delete in the database the user received in
     * parameters
     *
     * @param user User
     * @return if was possible delete
     */
    public Boolean deleteUser(User user) {
        for (int i = 0; i < ud.consultAll().size(); i++) {
            if (user.getId() == ud.consultAll().get(i).getId()) {
                ud.delete(user.getId());
                return true;
            }
        }
        return false;
    }

    /**
     * Returns all the users in the database
     */
    public void getUsers() {
        if (ud.consultAll().isEmpty()) {
            System.out.println("The database has not users at this time");
        } else {
            System.out.println(ud.consultAll());
        }
    }

    /**
     * Validate user credentials
     *
     * @param email Email
     * @param password Password
     * @return if the email and password are validate
     */
    public Boolean login(String email, String password) {
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
