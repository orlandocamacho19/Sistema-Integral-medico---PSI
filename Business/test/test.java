import Domain.Appointment;
import Domain.AppointmentType;
import Domain.Medicine;
import Domain.Patient;
import Domain.Payment;
import Domain.Type;
import control.AppointmentControl;
import control.PatientControl;
import java.sql.Timestamp;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author orlandocamacho
 */
public class test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Timestamp startTime = Timestamp.valueOf(("2022-03-20 23:00:00"));
        Appointment app = new Appointment(startTime, new Patient(11), new Medicine(14), new Payment(8), AppointmentType.Nutritional, Type.New, true, "" );
        //Appointment app = AppointmentControl.getInstance().getAppointmentByID(39);
        
        System.out.println(app);
        
        AppointmentControl.getInstance().editAppointment(app);
        
    }
}
