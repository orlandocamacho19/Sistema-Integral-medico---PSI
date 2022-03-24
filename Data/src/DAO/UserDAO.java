package DAO;

import Domain.Ocupation;
import Domain.User;
import connection.ConnectionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends ConnectionDB {
    
    private static UserDAO userDAO;

    /**
     * Constructor
     */
    private UserDAO() {
    }
    
    /**
     * Return the instance of the class following the singleton pattern
     *
     * @return instance
     */
    public static UserDAO getInstance(){
        if (userDAO == null) {
            userDAO = new UserDAO();
        }
        return userDAO;
    }

    /**
     * Inserts in the database the user received in parameters
     * @param user User
     */
    public void insert(User user) {
        try {
            this.connect();
            String sql = "insert into users (name, email, password, phone, speciality, ocupation) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getSpecialty());
            ps.setString(6, user.getOcupation().toString());
            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * Update in the database the user received in parameters by the
     * user ID
     * @param id User ID
     * @param user User
     */
    public void update(int id, User user) {
        try {
            this.connect();
            String sql = "update users set name=?, email=?, password=?, phone=?, speciality=?, ocupation=? where id_user=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getSpecialty());
            ps.setString(6, user.getOcupation().toString());
            ps.setInt(7, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * Deletes from the database the user with which the ID received in
     * parameters matches
     * @param id 
     */
    public void delete(int id) {
        try {
            this.connect();
            String sql = "delete from users where id_user=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * Consults and returns all the medical notes in the database
     *
     * @return medical notes list
     */
    public List<User> consultAll() {
        ResultSet res;
        List doctors = new ArrayList();
        try {
            this.connect();
            String sql = "select * from users";
            PreparedStatement ps = this.getCon().prepareCall(sql);
            res = ps.executeQuery();
            while (res.next()) {
                User user = new User();
                user.setId(res.getInt("id_doctor"));
                user.setName(res.getString("name"));
                user.setEmail(res.getString("email"));
                user.setPassword(res.getString("password"));
                user.setPhone("phone");
                user.setSpecialty(res.getString("speciality"));
                user.setOcupation(Ocupation.valueOf((String) res.getObject("ocupation")));
                doctors.add(user);

            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return doctors;
    }
    
    /**
     * Consults and returns the user matching their IDs
     * @param user User
     * @return User 
     */
    public boolean consultByID(User user) {
        for (int i = 0; i < consultAll().size(); i++) {
            if (consultAll().get(i).getId()== user.getId()) {
                return true;
            }
        }
        return false;
    }
}
