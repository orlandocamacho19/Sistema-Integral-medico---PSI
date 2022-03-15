package DAO;

import Domain.Medicine;
import conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAO extends Conexion {

    public void insert(Medicine medicine) {
        try {
            this.conect();
            String sql = "insert into medicines (name, amount, ingest, indications, due_date) values (?, ?, ?, ?, ?)";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setString(1, medicine.getName());
            ps.setDouble(2, medicine.getAmount());
            ps.setDouble(3, medicine.getIngest());
            ps.setString(4, medicine.getIndications());
            ps.setDate(5, (Date) medicine.getDueDate());

            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void update(int id, Medicine medicine) {
        try {
            this.conect();
            String sql = "update medicines set name=?, amount=?, ingest=?, indications=?, due_date=? where id_medicine=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setString(1, medicine.getName());
            ps.setDouble(2, medicine.getAmount());
            ps.setDouble(3, medicine.getIngest());
            ps.setString(4, medicine.getIndications());
            ps.setDate(5, (Date) medicine.getDueDate());
            ps.setInt(6, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void delete(int id) {
        try {
            this.conect();
            String sql = "delete from medicines where id_medicine=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public List consultAll() {
        ResultSet res;
        List medicines = new ArrayList();
        try {
            this.conect();
            String sql = "select * from medicines";
            PreparedStatement ps = this.getCon().prepareCall(sql);
            res = ps.executeQuery();
            while (res.next()) {
                Medicine medicine = new Medicine();
                medicine.setID(res.getInt("id_medicine"));
                medicine.setName(res.getString("name"));
                medicine.setAmount(res.getDouble("amount"));
                medicine.setIngest(res.getDouble("ingest"));
                medicine.setIndications(res.getString("indications"));
                medicine.setDueDate(res.getDate("due_date"));
                medicines.add(medicine);

            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return medicines;
    }
}
