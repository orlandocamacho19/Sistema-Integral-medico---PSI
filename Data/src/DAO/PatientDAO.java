package DAO;

import Domain.Medicine;
import Domain.Patient;
import conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO extends Conexion {

    public void insert(Patient patient) {
        try {
            this.conect();
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

    public void update(int id, Patient patient) {
        try {
            this.conect();
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

    public void delete(int id) {
        try {
            this.conect();
            String sql = "delete from patients where id_patient=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public List<Patient> consultAll() {
        ResultSet res;
        List medicines = new ArrayList();
        try {
            this.conect();
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

    public boolean findID(Patient patient) {
        for (int i = 0; i < consultAll().size(); i++) {
            if (consultAll().get(i).getID() == patient.getID()) {
                return true;
            }
        }
        return false;
    }

//    public Patient clon(Patient patient){
//        for (int i = 0; i < consultAll().size(); i++) {
//            if (patient.getName().equalsIgnoreCase(consultAll().get(i).getName())) {
//                if (patient.getPhone().equalsIgnoreCase(consultAll().get(i).getPhone())) {
//                    if (patient.getBirthDate().equals(consultAll().get(i).getBirthDate())) {
//                        Patient pClone = new Patient();
//                        pClone.setID(patient.);
//                    }
//                }
//            }
//        }
//        return patient;
//    }
}
