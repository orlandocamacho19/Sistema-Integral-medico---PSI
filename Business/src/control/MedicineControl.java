package control;

import DAO.MedicineDAO;
import Domain.Medicine;

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
    public Boolean addMedicine(Medicine medicine) {
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
    public Boolean editMedicine(Medicine medicine) {
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
    public Boolean deleteMedicine(Medicine medicine) {
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
    public void getMedicines() {
        if (md.consultAll().isEmpty()) {
            System.out.println("The database has not medicines at this time");
        } else {
            System.out.println(md.consultAll());
        }
    }
}
