package DAO;

import Domain.Medicine;
import Domain.Patient;
import connection.ConnectionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicineDAO extends ConnectionDB {

    private static MedicineDAO medicineDAO;

    /**
     * Constructor
     */
    private MedicineDAO() {
    }
    
    /**
     * Return the instance of the class following the singleton pattern
     *
     * @return instance
     */
    public static MedicineDAO getInstance(){
        if (medicineDAO == null) {
            medicineDAO = new MedicineDAO();
        }
        return medicineDAO;
    }
    
    /**
     * Inserts in the database the medicine received in parameters
     * @param medicine Medicine
     */
    public void insert(Medicine medicine) {
        try {
            this.connect();
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

    /**
     * Update in the database the medicine received in parameters by the
     * medicine ID
     * @param id Medicine ID
     * @param medicine Medicine
     */
    public void update(int id, Medicine medicine) {
        try {
            this.connect();
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

    /**
     * Deletes from the database the medicine with which the ID received in
     * parameters matches
     * 
     * @param id 
     */
    public void delete(int id) {
        try {
            this.connect();
            String sql = "delete from medicines where id_medicine=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * Consults and returns all the medicines in the database
     *
     * @return medicines list
     */
    public List<Medicine> consultAll() {
        ResultSet res;
        List medicines = new ArrayList();
        try {
            this.connect();
            String sql = "select * from medicines";
            PreparedStatement ps = this.getCon().prepareCall(sql);
            res = ps.executeQuery();
            while (res.next()) {
                Medicine medicine = new Medicine();
                medicine.setId_medicine(res.getInt("id_medicine"));
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
    
    /**
     * Consults and returns the medicine matching their IDs
     * @param medicine Medicine
     * @return Medicine
     */
    public boolean consultByID(Medicine medicine) {
        for (int i = 0; i < consultAll().size(); i++) {
            if (consultAll().get(i).getId_medicine()== medicine.getId_medicine()) {
                return true;
            }
        }
        return false;
    }
}
