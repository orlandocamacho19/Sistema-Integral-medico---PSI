package Domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Patient {
    private int id_patient;
    private String name;
    private String phone;
    private Date birthDate;
    private List<MedicalNote> medicalNotes = new ArrayList<>();
    private List<Medicine> medicines = new ArrayList<>();

    public Patient() {
    }

    public Patient(int id_patient) {
        this.id_patient = id_patient;
    }

    public Patient(int id_patient, String name, String phone, Date birthDate) {
        this.id_patient = id_patient;
        this.name = name;
        this.phone = phone;
        this.birthDate = birthDate;
    }

    public Patient(String name, String phone, Date birthDate) {
        this.name = name;
        this.phone = phone;
        this.birthDate = birthDate;
    }

    public int getID() {
        return id_patient;
    }

    public void setID(int id_patient) {
        this.id_patient = id_patient;
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
        hash = 43 * hash + Objects.hashCode(this.id_patient);
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
        return Objects.equals(this.id_patient, other.id_patient);
    }

    @Override
    public String toString() {
        return "Patient: " + "Id: " + id_patient + ", Name: " + name + ", Phone: " + phone + ", Birth Date: " + birthDate;
    }
}
