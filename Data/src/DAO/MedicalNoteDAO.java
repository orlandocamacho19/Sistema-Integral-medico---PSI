package DAO;

import Domain.Appointment;
import Domain.AppointmentType;
import Domain.User;
import Domain.MedicalNote;
import Domain.Medicine;
import Domain.Patient;
import Domain.Payment;
import Domain.Type;
import conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalNoteDAO extends Conexion {

    public void insert(MedicalNote medicalNote) {
        try {
            this.conect();
            String sql = "insert into medical_notes (id_user, id_appointment, notes) values (?, ?, ?)";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setInt(1, medicalNote.getUser().getId());
            ps.setInt(2, medicalNote.getAppointment().getId_appointment());
            ps.setString(3, medicalNote.getNotes());
            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void update(int id, MedicalNote medicalNote) {
        try {
            this.conect();
            String sql = "update medical_notes set id_user=?, id_appointment=?, notes=? where id_medical_note=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setInt(1, medicalNote.getUser().getId());
            ps.setInt(2, medicalNote.getAppointment().getId_appointment());
            ps.setString(3, medicalNote.getNotes());
            ps.setInt(4, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void delete(int id) {
        try {
            this.conect();
            String sql = "delete from medical_notes where id_medical_note=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public List consultAll() {
        ResultSet res;
        List medicalNotes = new ArrayList();
        try {
            this.conect();
            String sql = "select * from medical_notes";
            PreparedStatement ps = this.getCon().prepareCall(sql);
            res = ps.executeQuery();
            while (res.next()) {
                MedicalNote medicalNote = new MedicalNote();
                medicalNote.setID(res.getInt("id_medical_note"));
                
                User u = new User((int) res.getObject("id_user"));
                medicalNote.setUser(u);

                medicalNote.setNotes(res.getString("notes"));

                medicalNotes.add(medicalNote);

            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return medicalNotes;
    }
}
