package control;

import DAO.AppointmentDAO;
import Domain.Appointment;
import Domain.AppointmentType;
import java.util.List;

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
//                if (this.betweenHours(appointment, ad.consultAll().get(i))) {
//                    System.out.println("Error - Conflicts between hours");
//                    return false;
//                }
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
    public List<Appointment> getAppointment(){
        if (ad.consultAll().isEmpty()) {
            System.out.println("The database has not appointments at this time");
        }else{
            return ad.consultAll();
        }
        return null;
    }
    
//    public boolean betweenHours(Appointment add, Appointment bdd){
//        if (bdd.getaType() == AppointmentType.Nutritional) {
//            if (bdd.getStartTime().getTime() <= add.getStartTime().getTime() && bdd.getStartTime().getTime() + 900000 >= add.getStartTime().getTime()) {
//                System.out.println("qp");
//                return true;
//            }
//        } else if (bdd.getaType() == AppointmentType.Surgical) {
//            if (bdd.getStartTime().getTime() <= add.getStartTime().getTime() && bdd.getStartTime().getTime() + 3600000 >= add.getStartTime().getTime()) {
//                System.out.println("qp2");
//                return true;
//            }
//        } else if (bdd.getaType() == AppointmentType.Esthetic) {
//            if (bdd.getStartTime().getTime() <= add.getStartTime().getTime() && bdd.getStartTime().getTime() + 900000 >= add.getStartTime().getTime()) {
//                System.out.println("qp3");
//                return true;
//            }
//        }
//        return false;
//    }
}
