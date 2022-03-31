package control;

import DAO.AppointmentDAO;
import Domain.Appointment;
import Domain.AppointmentType;
import Domain.Patient;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;
import java.time.Instant;

public class AppointmentControl {
    
    private static AppointmentControl appointmentControl;
    private static AppointmentDAO ad;
    
    /** 
     * Constructor
     */
    private AppointmentControl() {
        ad = AppointmentDAO.getInstance();
    }
    
    /**
     * Return the instance of the class following the singleton pattern
     *
     * @return instance
     */
    public static AppointmentControl getInstance(){
        if (appointmentControl == null) {
            appointmentControl = new AppointmentControl();
        }
        return appointmentControl;
    }
    
    /**
     * Return if was possible inserts in the database the appointment received in
     * parameters
     *
     * @param appointment Appointment
     * @return if was possible add
     */
    public boolean addAppointment(Appointment appointment) {
        List <Appointment> appointments = ad.consultAll();
        
        if (!validateAppointment(appointment, appointments)) {
            return false;
        }
        
        for (int i = 0; i < appointments.size(); i++) {
            if (appointment.getId_appointment() == appointments.get(i).getId_appointment()) {
                return false;
            } else {
                ad.insert(appointment);
                return true;
            }
        }
        return false;
    }

    /**
     * Return if was possible update in the database the appointment received in
     * parameters
     *
     * @param appointment Appointment
     * @return if was possible update
     */
    public boolean editAppointment(Appointment appointment) {
        List <Appointment> appointments = ad.consultAll();
        
        if (!validateAppointment(appointment, appointments)) {
            return false;
        }
        
        for (int i = 0; i < appointments.size(); i++) {
            if (appointment.getId_appointment() == appointments.get(i).getId_appointment()) {
                ad.update(appointments.get(i).getId_appointment(), appointment);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Return if was possible delete in the database the appointment received in
     * parameters
     *
     * @param appointment Appointment
     * @return if was possible delete
     */
    public boolean deleteAppointment(Appointment appointment) {
        List <Appointment> appointments = ad.consultAll();
        for (int i = 0; i < appointments.size(); i++) {
            if (appointment.getId_appointment() == appointments.get(i).getId_appointment()) {
                ad.delete(appointment.getId_appointment());
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns all the appointments in the database
     */
    public List<Appointment> getAppointment(){
        List <Appointment> appointments = ad.consultAll();
        if (appointments.isEmpty()) {
            System.out.println("The database has not appointments at this time");
        }else{
            return appointments;
        }
        return null;
    }
    
    /**
     * Returns all the appointments in the database
     */
    public Appointment getAppointmentByID(int ID){
        List <Appointment> appointments = ad.consultAll();
        if (appointments.isEmpty()) {
            System.out.println("The database has not appointments at this time");
        }else{
            for (Appointment appointment : appointments) {
                if (ID == appointment.getId_appointment()) {
                    return appointment;
                }
            }
        }
        return null;
    }
    
    /**
     * Returns all the appointments of a patient
     * @param patient
     * @return 
     */
    public List<Appointment> getAppointmentByPatient(Patient patient){
        List <Appointment> appointments = ad.consultAll();
        List <Appointment> byPatient = new ArrayList<>();
        
        if (appointments.isEmpty()) {
            System.out.println("The database has not appointments at this time");
            return null;
        } else{
            for (Appointment appointment : appointments) {
                if (patient.getID() == appointment.getPatient().getID()) {
                    byPatient.add(appointment);
                }
            }
        }
        
        if (byPatient.isEmpty()) {
            return null;
        } else {
            return byPatient;
        }
    }
    
    /**
     * Returns all the appointments from now on
     * @param appointments
     * @return 
     */
    public List<Appointment> getAppointmentFromNowOn(List <Appointment> appointments){
        Timestamp currentTime = Timestamp.from(Instant.now());
        List <Appointment> fromNowOn = new ArrayList<>();
        
        if (appointments.isEmpty()) {
            System.out.println("The database has not appointments at this time");
            return null;
        } else {
            for (Appointment appointment : appointments) {
                if (currentTime.before(appointment.getStartTime())) {
                    fromNowOn.add(appointment);
                }
            }
        }
        
        if (fromNowOn.isEmpty()) {
            return null;
        } else {
            return fromNowOn;
        }
    }
    
    /**
     * Validate that the date of the appointment received is different to all in the database
     * @param appointment
     * @return 
     */
    public boolean validateAppointment(Appointment appointment, List <Appointment> appointments){
        long start = appointment.getStartTime().getTime();
        long end;
        
        if (appointment.getaType() == AppointmentType.Esthetic || appointment.getaType() == AppointmentType.Nutritional) {
            end = start + 900000;
        } else{
            end = start + 3600000;
        }
        
        for (Appointment appointmentDB : appointments) {
            long startDB = appointmentDB.getStartTime().getTime();
            long endDB;
            
            if (start == startDB) {
                return false;
            }
            
            if (appointmentDB.getaType() == AppointmentType.Esthetic || appointmentDB.getaType() == AppointmentType.Nutritional) {
                endDB = startDB + 900000;
                
                if (start < startDB) {
                    if (end > endDB) { 
                        return false;
                    }
                } else if (start > startDB) {
                    if (start < endDB) {
                        return false;
                    }
                }
            } else {
                endDB = startDB + 3600000;
                
                if (start < startDB) {
                    if (end > endDB) {
                        return false;
                    }
                } else if (start > startDB) {
                    if (start < endDB) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
