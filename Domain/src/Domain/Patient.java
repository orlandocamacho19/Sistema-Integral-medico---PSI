package Domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Equipo 1
 */
public class Patient {
    private String ID;
    private String name;
    private String phone;
    private Date birthDate;
    private List<MedicalNote> medicalNotes = new ArrayList<>();
    private List<Medicine> medicines = new ArrayList<>();

    public Patient(String ID, String name, String phone, Date birthDate) {
        this.ID = ID;
        this.name = name;
        this.phone = phone;
        this.birthDate = birthDate;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public List<MedicalNote> getMedicalNotes() {
        return medicalNotes;
    }

    public void setMedicalNotes(List<MedicalNote> medicalNotes) {
        this.medicalNotes = medicalNotes;
    }

    public List<Medicine> getMedicines() {
        return medicines;
    }

    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.ID);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Patient other = (Patient) obj;
        return Objects.equals(this.ID, other.ID);
    }

    @Override
    public String toString() {
        return "Patient{" + "ID=" + ID + ", name=" + name + ", phone=" + phone + ", birthDate=" + birthDate + ", medicalNotes=" + medicalNotes + ", medicines=" + medicines + '}';
    }
}
