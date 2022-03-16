package control;

import DAO.MedicineDAO;
import Domain.Medicine;

public class ControlMedicine {

    public Boolean addMedicine(Medicine medicine) {
        MedicineDAO md = new MedicineDAO();
        if (md.findID(medicine)) {
            return false;
        }else{
            md.insert(medicine);
            return true;
        }
    }


    public Boolean editMedicine(Medicine medicine) {
        MedicineDAO md = new MedicineDAO();
        for (int i = 0; i < md.consultAll().size(); i++) {
            if (medicine.getId_medicine() == md.consultAll().get(i).getId_medicine()) {
                md.update(md.consultAll().get(i).getId_medicine(), medicine);
                return true;
            }
        }
        return false;
    }

    public Boolean deleteMedicine(Medicine medicine) {
        MedicineDAO md = new MedicineDAO();
        for (int i = 0; i < md.consultAll().size(); i++) {
            if (medicine.getId_medicine() == md.consultAll().get(i).getId_medicine()) {
                md.delete(medicine.getId_medicine());
                return true;
            }
        }
        return false;
    }

    public void viewMedicines() {
        MedicineDAO md = new MedicineDAO();
        if (md.consultAll().isEmpty()) {
            System.out.println("The database has not medicines at this time");
        } else {
            System.out.println(md.consultAll());
        }
    }
}
