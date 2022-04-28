import Domain.Appointment;
import Domain.AppointmentType;
import Domain.Medicine;
import Domain.Patient;
import Domain.Payment;
import Domain.Type;
import control.AppointmentControl;
import control.PatientControl;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

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
        Timestamp currentTime = Timestamp.valueOf("2022-04-26 12:30:00");
        Object[] appointments = AppointmentControl.getInstance().getAppointmentByWeek(currentTime);
        for (Object appointment : appointments) {            
            System.out.println((List<Appointment>) appointment);
        }
    }
}
