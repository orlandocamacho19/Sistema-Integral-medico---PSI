package DAO;

import Domain.User;
import Domain.MedicalNote;
import connection.ConnectionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalNoteDAO extends ConnectionDB {

    private static MedicalNoteDAO medicalNoteDAO;

    /**
     * Constructor
     */
    private MedicalNoteDAO() {
    }

    /**
     * Return the instance of the class following the singleton pattern
     *
     * @return instance
     */
    public static MedicalNoteDAO getInstance() {
        if (medicalNoteDAO == null) {
            medicalNoteDAO = new MedicalNoteDAO();
        }
        return medicalNoteDAO;
    }

    /**
     * Inserts in the database the medical note received in parameters
     *
     * @param medicalNote Medical note
     */
    public void insert(MedicalNote medicalNote) {
        try {
            this.connect();
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

    /**
     * Update in the database the medical note received in parameters by the
     * medical note ID
     *
     * @param id Medical note iD
     * @param medicalNote Medical note
     */
    public void update(int id, MedicalNote medicalNote) {
        try {
            this.connect();
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

    /**
     * Deletes from the database the medical note with which the ID received in
     * parameters matches
     *
     * @param id
     */
    public void delete(int id) {
        try {
            this.connect();
            String sql = "delete from medical_notes where id_medical_note=?";
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
    public List<MedicalNote> consultAll() {
        ResultSet res;
        List medicalNotes = new ArrayList();
        try {
            this.connect();
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

    /**
     * Consults and returns the medical note matching their IDs
     *
     * @param medicalnote Medical note
     * @return Medical note
     */
    public boolean consultByID(MedicalNote medicalnote) {
        for (int i = 0; i < consultAll().size(); i++) {
            if (consultAll().get(i).getID() == medicalnote.getID()) {
                return true;
            }
        }
        return false;
    }
}
