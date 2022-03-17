package DAO;

import Domain.Patient;
import connection.ConnectionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO extends ConnectionDB {

    private static PatientDAO patientDAO;

    /**
     * Constructor
     */
    private PatientDAO() {
    }

    /**
     * Return the instance of the class following the singleton pattern
     *
     * @return instance
     */
    public static PatientDAO getInstance() {
        if (patientDAO == null) {
            patientDAO = new PatientDAO();
        }
        return patientDAO;
    }

    /**
     * Inserts in the database the patient received in parameters
     *
     * @param patient Patient
     */
    public void insert(Patient patient) {
        try {
            this.connect();
            String sql = "insert into patients (name, phone, birth_date) values (?, ?, ?)";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setString(1, patient.getName());
            ps.setString(2, patient.getPhone());
            ps.setDate(3, (Date) patient.getBirthDate());

            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * Update in the database the patient received in parameters by the patient
     * ID
     *
     * @param id Patient ID
     * @param patient Patient
     */
    public void update(int id, Patient patient) {
        try {
            this.connect();
            String sql = "update patients set name=?, phone=?, birth_date=? where id_patient=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setString(1, patient.getName());
            ps.setString(2, patient.getPhone());
            ps.setDate(3, (Date) patient.getBirthDate());
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * Deletes from the database the patient with which the ID received in
     * parameters matches
     *
     * @param id Patient ID
     */
    public void delete(int id) {
        try {
            this.connect();
            String sql = "delete from patients where id_patient=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * Consults and returns all the patients in the database
     *
     * @return Patients list
     */
    public List<Patient> consultAll() {
        ResultSet res;
        List medicines = new ArrayList();
        try {
            this.connect();
            String sql = "select * from patients";
            PreparedStatement ps = this.getCon().prepareCall(sql);
            res = ps.executeQuery();
            while (res.next()) {
                Patient patient = new Patient();
                patient.setID(res.getInt("id_patient"));
                patient.setName(res.getString("name"));
                patient.setPhone(res.getString("phone"));
                patient.setBirthDate(res.getDate("birth_date"));
                medicines.add(patient);

            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return medicines;
    }

    /**
     * Consults and returns the patients matching their IDs
     *
     * @param patient Patient
     * @return Patient
     */
    public boolean consultByID(Patient patient) {
        for (int i = 0; i < consultAll().size(); i++) {
            if (consultAll().get(i).getID() == patient.getID()) {
                return true;
            }
        }
        return false;
    }

}
