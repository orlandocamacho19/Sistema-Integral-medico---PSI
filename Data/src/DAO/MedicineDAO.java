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
            String sql = "insert into medicines (name, amount, ingredient, mgIngredient, indications, active) values (?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setString(1, medicine.getName());
            ps.setDouble(2, medicine.getAmount());
            ps.setString(3, medicine.getIngredient());
            ps.setInt(4, medicine.getMgIngredient());
            ps.setString(5, medicine.getIndications());
            ps.setBoolean(6, medicine.isActive());

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
            String sql = "update medicines set name=?, amount=?, ingredient=?, mgIngredient=?, indication=?, sactive=? where id_medicine=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setString(1, medicine.getName());
            ps.setDouble(2, medicine.getAmount());
            ps.setString(3, medicine.getIngredient());
            ps.setInt(4, medicine.getMgIngredient());
            ps.setString(5, medicine.getIndications());
            ps.setBoolean(6, medicine.isActive());
            ps.setInt(7, id);
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
                medicine.setIngredient(res.getString("ingredient"));
                medicine.setMgIngredient(res.getInt("mgIngredient"));
                medicine.setIndications(res.getString("indications"));
                medicine.setActive(res.getBoolean("active"));
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
