
import Domain.Patient;
import control.AppointmentControl;
import control.PatientControl;

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
        System.out.println(PatientControl.getInstance().getPatients());
    }
    
}
