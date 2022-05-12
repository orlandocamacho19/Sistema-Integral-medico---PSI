import Domain.Appointment;
import Domain.AppointmentType;
import Domain.Medicine;
import Domain.Patient;
import Domain.Payment;
import Domain.Type;
import control.AppointmentControl;
import control.MedicineControl;
import control.PatientControl;
import java.sql.Timestamp;
import java.time.Instant;
import java.sql.Date;
import java.time.LocalDate;
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
        // Agrega Paciente
        Patient pt = new Patient("Juancho", "6543219780", Date.valueOf(LocalDate.now()), "email@mail.com", "calle si");
        System.out.println(PatientControl.getInstance().addPatient(pt));
        // Agrega medicina
        Medicine med = new Medicine("Paracetamol", 12, "Dunno", 5, "Tomeselo B)", true);
        System.out.println(MedicineControl.getInstance().addMedicine(med));
    }
}
