package test;

import DAO.AppointmentDAO;
import DAO.UserDAO;
import DAO.MedicalNoteDAO;
import DAO.MedicineDAO;
import DAO.PatientDAO;
import DAO.PaymentDAO;
import Domain.Appointment;
import Domain.AppointmentType;
import Domain.Medicine;
import Domain.Patient;
import Domain.Payment;
import Domain.Type;
import Domain.User;
import Domain.MedicalNote;
import Domain.Ocupation;
import java.sql.Date;

public class test {

    public static void main(String[] args) {
        PatientDAO pd = new PatientDAO();
        AppointmentDAO ad = new AppointmentDAO();
        MedicineDAO md = new MedicineDAO();
        PaymentDAO payd = new PaymentDAO();
        UserDAO dd = new UserDAO();
        MedicalNoteDAO mnd = new MedicalNoteDAO();
        
        
        
        Patient p1 = new Patient(10, "Orlando", "6441643488", new Date(2001-1900, 11-1, 30));
//        pd.insert(p1);

//        System.out.println(pd.findID(p1));
        
        Medicine m1 = new Medicine (14, "Paracetamol", 10, 500, "Una al dia", new Date (2022-1900, 3-1, 23));
//        md.insert(m1);
        Payment pay1 = new Payment(8, 2000, true);
//        payd.insert(pay1);
        
        Appointment a1 = new Appointment(19, new Date(2022-1900, 3-1, 25), p1, m1, pay1, AppointmentType.Surgical, Type.Recurrent, true);
//        System.out.println(ad.consultAll());

        User d = new User(4, "Hector", "torrozapata@gmail.com", "torro123", "0000000000", Ocupation.Doctor,"Cirujano");
//        dd.insert(d);

        MedicalNote mn = new MedicalNote(d, a1, "Tiene que volver en 15 dias");
        mnd.insert(mn);
    }

}
