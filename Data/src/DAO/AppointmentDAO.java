package DAO;

import Domain.Appointment;
import Domain.AppointmentType;
import Domain.Medicine;
import Domain.Patient;
import Domain.Payment;
import Domain.Type;
import connection.ConnectionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO extends ConnectionDB {

    private static AppointmentDAO appointmentDAO;

    /**
     * Constructor
     */
    private AppointmentDAO() {
    }

    /**
     * Return the instance of the class following the singleton pattern
     *
     * @return instance
     */
    public static AppointmentDAO getInstance() {
        if (appointmentDAO == null) {
            appointmentDAO = new AppointmentDAO();
        }
        return appointmentDAO;
    }

    /**
     * Inserts in the database the appointment received in parameters
     *
     * @param appointment Appointment
     */
    public void insert(Appointment appointment) {
        try {
            this.connect();
            String sql = "INSERT INTO appointments (start_time, id_patient, appointment_type, type, confirmation, reason) VALUES (?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = this.getCon().prepareStatement(sql);

            ps.setTimestamp(1, appointment.getStartTime());
            ps.setInt(2, appointment.getPatient().getID());
            ps.setString(3, appointment.getaType().toString());
            ps.setString(4, appointment.getType().toString());
            ps.setBoolean(5, appointment.isConfirmation());
            ps.setString(6, appointment.getReason());
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Update in the database the appointment received in parameters by the
     * appointment ID
     *
     * @param id Appointment ID
     * @param appointment Appointment
     */
    public void update(int id, Appointment appointment) {
        try {
            this.connect();
            String sql = "update appointments set start_time=?, id_patient=?, appointment_type=?, type=?, confirmation=?, reason=? where id_appointment=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setTimestamp(1, appointment.getStartTime());
            ps.setInt(2, appointment.getPatient().getID());
            ps.setString(3, appointment.getaType().toString());
            ps.setString(4, appointment.getType().toString());
            ps.setBoolean(5, appointment.isConfirmation());
            ps.setString(6, appointment.getReason());
            ps.setInt(7, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * Deletes from the database the appointment with which the ID received in
     * parameters matches
     *
     * @param id Appointment ID
     */
    public void delete(int id) {
        try {
            this.connect();
            String sql = "delete from appointments where id_appointment=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * Consults and returns all the appointments in the database
     *
     * @return appointments list
     */
    public List<Appointment> consultAll() {
        ResultSet res;
        List appointments = new ArrayList();
        try {
            this.connect();
            String sql = "select * from appointments";
            PreparedStatement ps = this.getCon().prepareCall(sql);
            res = ps.executeQuery();
            while (res.next()) {
                Appointment appointment = new Appointment();
                appointment.setId_appointment(res.getInt("id_appointment"));
                appointment.setStartTime(res.getTimestamp("start_time"));
                Patient p = new Patient((int) res.getObject("id_patient"));
                appointment.setPatient(p);

                if (res.getObject("id_medicine") != null) {
                   Medicine m = new Medicine((int) res.getObject("id_medicine"));
                    appointment.setMedicine(m); 
                }
                
                if (res.getObject("id_payment") != null) {
                    Payment pa = new Payment((int) res.getObject("id_payment"));
                    appointment.setPayment(pa);
                }

                appointment.setaType(AppointmentType.valueOf((String) res.getObject("appointment_type")));
                appointment.setType(Type.valueOf((String) res.getObject("type")));
                appointment.setConfirmation(res.getBoolean("confirmation"));
                appointment.setReason(res.getString("reason"));
                appointments.add(appointment);

            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return appointments;
    }
    
    /**
     * Consults and returns the appointment matching their IDs
     * @param appointment Appointment
     * @return Appointment
     */
    public boolean consultByID(Appointment appointment) {
        for (int i = 0; i < consultAll().size(); i++) {
            if (consultAll().get(i).getId_appointment()== appointment.getId_appointment()) {
                return true;
            }
        }
        return false;
    }
}
