package DAO;

import Domain.Ocupation;
import Domain.User;
import conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO extends Conexion {

    public void insert(User user) {
        try {
            this.conect();
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

    public void update(int id, User user) {
        try {
            this.conect();
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

    public void delete(int id) {
        try {
            this.conect();
            String sql = "delete from users where id_user=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public List<User> consultAll() {
        ResultSet res;
        List doctors = new ArrayList();
        try {
            this.conect();
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
    
    public boolean findID(User user) {
        for (int i = 0; i < consultAll().size(); i++) {
            if (consultAll().get(i).getId()== user.getId()) {
                return true;
            }
        }
        return false;
    }
}
