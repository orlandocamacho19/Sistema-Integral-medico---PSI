package control;

import Domain.Patient;
import DAO.PatientDAO;
import java.util.List;

public class PatientControl {

    private static PatientControl patientControl;
    private static PatientDAO pd;

    /**
     * Constructor
     */
    private PatientControl() {
        pd = PatientDAO.getInstance();
    }

    /**
     * Return the instance of the class following the singleton pattern
     *
     * @return instance
     */
    public static PatientControl getInstance() {
        if (patientControl == null) {
            patientControl = new PatientControl();
        }
        return patientControl;
    }

    /**
     * Return if was possible inserts in the database the patient received in
     * parameters
     *
     * @param patient Patient
     * @return if was possible add
     */
    public Boolean addPatient(Patient patient) {
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

    /**
     * Return if was possible update in the database the patient received in
     * parameters
     *
     * @param patient Patient
     * @return if was possible update
     */
    public Boolean editPatient(Patient patient) {
        for (int i = 0; i < pd.consultAll().size(); i++) {
            if (patient.getID() == pd.consultAll().get(i).getID()) {
                pd.update(pd.consultAll().get(i).getID(), patient);
                return true;
            }
        }
        return false;
    }

    /**
     * Return if was possible delete in the database the patient received in
     * parameters
     *
     * @param patient Patient
     * @return if was possible delete
     */
    public Boolean deletePatient(Patient patient) {
        for (int i = 0; i < pd.consultAll().size(); i++) {
            if (patient.getID() == pd.consultAll().get(i).getID()) {
                pd.delete(patient.getID());
                return true;
            }
        }
        return false;
    }

    /**
     * Returns all the users in the database
     */
    public List<Patient> getPatients() {
        if (pd.consultAll().isEmpty()) {
            System.out.println("The database has not patients at this time");
        } else {
            return pd.consultAll();
        }
        return null;
    }
}
