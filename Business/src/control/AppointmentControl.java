package control;

import DAO.AppointmentDAO;
import Domain.Appointment;

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
    public Boolean addAppointment(Appointment appointment) {
        for (int i = 0; i < ad.consultAll().size(); i++) {
            if (appointment.getId_appointment()== ad.consultAll().get(i).getId_appointment()) {
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
    public Boolean editAppointment(Appointment appointment) {
        for (int i = 0; i < ad.consultAll().size(); i++) {
            if (appointment.getId_appointment() == ad.consultAll().get(i).getId_appointment()) {
                ad.update(ad.consultAll().get(i).getId_appointment(), appointment);
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
    public Boolean deleteAppointment(Appointment appointment) {
        for (int i = 0; i < ad.consultAll().size(); i++) {
            if (appointment.getId_appointment() == ad.consultAll().get(i).getId_appointment()) {
                ad.delete(appointment.getId_appointment());
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns all the users in the database
     */
    public void getAppointment(){
        if (ad.consultAll().isEmpty()) {
            System.out.println("The database has not patients at this time");
        }else{
            System.out.println(ad.consultAll());
        }
    }
}
