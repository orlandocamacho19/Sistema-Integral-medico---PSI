package control;

import Domain.Patient;
import DAO.PatientDAO;

public class ControlPatient {

    public Boolean addPatient(Patient patient) {
        PatientDAO pd = new PatientDAO();
        for (int i = 0; i < pd.consultAll().size(); i++) {
            if (patient.getID() == pd.consultAll().get(i).getID()) {
                return false;
            } else {
                pd.insert(patient);
                return true;
            }
        }
        return false;
    }

    public Boolean editPatient(Patient patient) {
        PatientDAO pd = new PatientDAO();
        for (int i = 0; i < pd.consultAll().size(); i++) {
            if (patient.getID() == pd.consultAll().get(i).getID()) {
                pd.update(pd.consultAll().get(i).getID(), patient);
                return true;
            }
        }
        return false;
    }
    
    public Boolean deletePatient(Patient patient) {
        PatientDAO pd = new PatientDAO();
        for (int i = 0; i < pd.consultAll().size(); i++) {
            if (patient.getID() == pd.consultAll().get(i).getID()) {
                pd.delete(patient.getID());
                return true;
            }
        }
        return false;
    }
    
    public void getPatients(){
        PatientDAO pd = new PatientDAO();
        if (pd.consultAll().isEmpty()) {
            System.out.println("The database has not patients at this time");
        }else{
            System.out.println(pd.consultAll());
        }
    }
}
