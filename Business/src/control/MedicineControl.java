package control;

import DAO.MedicineDAO;
import Domain.Medicine;
import java.util.List;

public class MedicineControl {

    private static MedicineControl medicineControl;
    private static MedicineDAO md;

    /**
     * Constructor
     */
    private MedicineControl() {
        md = MedicineDAO.getInstance();
    }

    /**
     * Return the instance of the class following the singleton pattern
     *
     * @return instance
     */
    public static MedicineControl getInstance() {
        if (medicineControl == null) {
            medicineControl = new MedicineControl();
        }
        return medicineControl;
    }

    /**
     * Return if was possible inserts in the database the medicine received in
     * parameters
     *
     * @param medicine Medicine
     * @return if was possible add
     */
    public boolean addMedicine(Medicine medicine) {
        if (md.consultAll().contains(medicine)) {
            return false;
        }
        
        if (md.consultByID(medicine)) {
            return false;
        } else {
            md.insert(medicine);
            return true;
        }
    }

    /**
     * Return if was possible update in the database the medicine received in
     * parameters
     *
     * @param medicine Medicine
     * @return if was possible update
     */
    public boolean editMedicine(Medicine medicine) {
        for (int i = 0; i < md.consultAll().size(); i++) {
            if (medicine.getId_medicine() == md.consultAll().get(i).getId_medicine()) {
                md.update(md.consultAll().get(i).getId_medicine(), medicine);
                return true;
            }
        }
        return false;
    }

    /**
     * Return if was possible delete in the database the medicine received in
     * parameters
     *
     * @param medicine Medicine
     * @return if was possible delete
     */
    public boolean deleteMedicine(Medicine medicine) {
        for (int i = 0; i < md.consultAll().size(); i++) {
            if (medicine.getId_medicine() == md.consultAll().get(i).getId_medicine()) {
                md.delete(medicine.getId_medicine());
                return true;
            }
        }
        return false;
    }

    /**
     * Returns all the users in the database
     */
    public List<Medicine> getMedicines() {
        if (md.consultAll().isEmpty()) {
            System.out.println("The database has not medicines at this time");
        } else {
            return md.consultAll();
        }
        return null;
    }
    
    /**
     * Returns if was posible to set as active in the database the medicine 
     * received in parameters
     * @param medicine Medicine
     * @return is was posible set as active
     */
    public boolean activeMed(Medicine medicine){
        medicine.setActive(true);
        return editMedicine(medicine);
    }
    
    /**
     * Returns if was posible to set as desactive in the database the medicine 
     * received in parameters
     * @param medicine Medicine
     * @return is was posible set as desactive
     */
    public boolean desactiveMed(Medicine medicine){
        medicine.setActive(false);
        return editMedicine(medicine);
    }
    
    
    
    
}
