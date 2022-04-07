package control;

import DAO.MedicalNoteDAO;
import Domain.MedicalNote;

public class MedicalNoteControl {
    
    private static MedicalNoteControl medicalNoteControl;
    private static MedicalNoteDAO mnd;
    
    /**
     * Constructor
     */
    private MedicalNoteControl() {
        mnd = MedicalNoteDAO.getInstance();
    }
    
    /**
     * Return the instance of the class following the singleton pattern
     *
     * @return instance
     */
    public static MedicalNoteControl getInstance(){
        if (medicalNoteControl == null) {
            medicalNoteControl = new MedicalNoteControl();
        }
        return medicalNoteControl;
    }
    
    /**
     * Return if was possible inserts in the database the medicalNote received in
     * parameters
     *
     * @param medicalNote MedicalNote
     * @return if was possible add
     */
    public Boolean addMedicalNote(MedicalNote medicalNote) {
        for (int i = 0; i < mnd.consultAll().size(); i++) {
            if (medicalNote.getID() == mnd.consultAll().get(i).getID()) {
                return false;
            } else {
                mnd.insert(medicalNote);
                return true;
            }
        }
        return false;
    }

    /**
     * Return if was possible update in the database the medicalNote received in
     * parameters
     *
     * @param medicalNote MedicalNote
     * @return if was possible update
     */
    public Boolean editMedicalNote(MedicalNote medicalNote) {
        for (int i = 0; i < mnd.consultAll().size(); i++) {
            if (medicalNote.getID() == mnd.consultAll().get(i).getID()) {
                mnd.update(mnd.consultAll().get(i).getID(), medicalNote);
                return true;
            }
        }
        return false;
    }
    
    /**
     * Return if was possible delete in the database the medicalNote received in
     * parameters
     *
     * @param medicalNote MedicalNote
     * @return if was possible delete
     */
    public Boolean deleteMedicalNote(MedicalNote medicalNote) {
        for (int i = 0; i < mnd.consultAll().size(); i++) {
            if (medicalNote.getID() == mnd.consultAll().get(i).getID()) {
                mnd.delete(medicalNote.getID());
                return true;
            }
        }
        return false;
    }
    
    /**
     * Returns all the users in the database
     */
    public void getMedicalNotes(){
        if (mnd.consultAll().isEmpty()) {
            System.out.println("The database has not medical notes at this time");
        }else{
            System.out.println(mnd.consultAll());
        }
    }
}
