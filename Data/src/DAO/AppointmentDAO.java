package DAO;

import Domain.Appointment;
import Domain.AppointmentType;
import Domain.Medicine;
import Domain.Patient;
import Domain.Payment;
import Domain.Type;
import conexion.Conexion;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;

public class AppointmentDAO extends Conexion {

    public void insert(Appointment appointment) {
        try {
            this.conect();
            String sql = "INSERT INTO appointments (start_time, id_patient, id_medicine, id_payment, appointment_type, type, confirmation) VALUES (?, ?, ?, ?, ?, ?, ?);";
            PreparedStatement ps = this.getCon().prepareStatement(sql);

            ps.setTimestamp(1,  appointment.getStartTime());
            ps.setInt(2, appointment.getPatient().getID());
            ps.setInt(3, appointment.getMedicine().getId_medicine());
            ps.setInt(4, appointment.getPayment().getId_payment());
            ps.setString(5, appointment.getaType().toString());
            ps.setString(6, appointment.getType().toString());
            ps.setBoolean(7, appointment.isConfirmation());

            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void update(int id, Appointment appointment) {
        try {
            this.conect();
            String sql = "update appointments set start_time=?, id_patient=?, id_medicine=?, id_payment=?, appointment_type=?, type=?, confirmation=? where id_appointment=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setTimestamp(1,appointment.getStartTime());
            ps.setInt(2, appointment.getPatient().getID());
            ps.setInt(3, appointment.getMedicine().getId_medicine());
            ps.setInt(4, appointment.getPayment().getId_payment());
            ps.setString(5, appointment.getaType().toString());
            ps.setString(6, appointment.getType().toString());
            ps.setBoolean(7, appointment.isConfirmation());
            ps.setInt(8, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void delete(int id) {
        try {
            this.conect();
            String sql = "delete from appointments where id_appointment=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public List<Appointment> consultAll() {
        ResultSet res;
        List appointments = new ArrayList();
        try {
            this.conect();
            String sql = "select * from appointments";
            PreparedStatement ps = this.getCon().prepareCall(sql);
            res = ps.executeQuery();
            while (res.next()) {
                Appointment appointment = new Appointment();
                appointment.setId_appointment(res.getInt("id_appointment"));
                appointment.setStartTime(res.getTimestamp("start_time"));
                Patient p = new Patient((int) res.getObject("id_patient"));
                appointment.setPatient(p);
                
                Medicine m = new Medicine((int) res.getObject("id_medicine"));
                appointment.setMedicine(m);

                Payment pa = new Payment((int) res.getObject("id_payment"));
                appointment.setPayment(pa);

               
                appointment.setaType(AppointmentType.valueOf((String) res.getObject("appointment_type")));
                appointment.setType(Type.valueOf((String) res.getObject("type")));
                appointment.setConfirmation(res.getBoolean("confirmation"));
                appointments.add(appointment);

            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return appointments;
    }
}
