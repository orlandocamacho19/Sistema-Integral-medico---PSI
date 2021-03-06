package Domain;

import java.util.ArrayList;
import java.sql.Date;
import java.util.List;
import java.util.Objects;

public class Patient {

    private int id_patient;
    private String name;
    private String phone;
    private Date birthDate;
    private String email;
    private String address;
    private List<MedicalNote> medicalNotes = new ArrayList<>();
    private List<Medicine> medicines = new ArrayList<>();

    /**
     * Constructor
     */
    public Patient() {
    }

    /**
     * Constructor that receives the patient ID
     *
     * @param id_patient patient ID
     */
    public Patient(int id_patient) {
        this.id_patient = id_patient;
    }

    /**
     * Constructor that receives the patient ID, name, phone, birthDate, email, address 
     *
     * @param id_patient patient ID
     * @param name Name
     * @param phone Phone number
     * @param birthDate Birthdate
     * @param email Email
     * @param address Address
     */
    public Patient(int id_patient, String name, String phone, Date birthDate, String email, String address) {
        this.id_patient = id_patient;
        this.name = name;
        this.phone = phone;
        this.birthDate = birthDate;
        this.email = email;
        this.address = address;
    }

    /**
     * Constructor that receives the patient name, phone, birthDate, email, address 
     *
     * @param name Name
     * @param phone Phone nuymber
     * @param birthDate Birthdate
     * @param email Email
     * @param address Address
     */
    public Patient(String name, String phone, Date birthDate, String email, String address) {
        this.name = name;
        this.phone = phone;
        this.birthDate = birthDate;
        this.email = email;
        this.address = address;
    }

    /**
     * Return patient ID
     *
     * @return patient ID
     */
    public int getID() {
        return id_patient;
    }

    /**
     * Set patient ID
     *
     * @param id_patient patient ID
     */
    public void setID(int id_patient) {
        this.id_patient = id_patient;
    }

    /**
     * Return patient name
     *
     * @return Name
     */
    public String getName() {
        return name;
    }

    /**
     * Set patient name
     *
     * @param name Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return patient phone
     *
     * @return Patient phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set patient phone
     *
     * @param phone Patient phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Return patient birthdate
     *
     * @return Patient birthdate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Set patient birthdate
     *
     * @param birthDate Patient birthdate
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Return patient medical notes
     *
     * @return Patient medical notes
     */
    public List<MedicalNote> getMedicalNotes() {
        return medicalNotes;
    }

    /**
     * Set patient medical notes
     *
     * @param medicalNotes Patient medical notes
     */
    public void setMedicalNotes(List<MedicalNote> medicalNotes) {
        this.medicalNotes = medicalNotes;
    }

    /**
     * Return patient medicines
     *
     * @return Patient medicines
     */
    public List<Medicine> getMedicines() {
        return medicines;
    }

    /**
     * Set patient medicines
     *
     * @param medicines Patient medicines
     */
    public void setMedicines(List<Medicine> medicines) {
        this.medicines = medicines;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Hash code method
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.id_patient);
        return hash;
    }

    /**
     * Return if the object receive in params and this are equals
     *
     * @param obj object
     * @return True if they are equal, false otherwise
     */
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
        if (this.id_patient != other.id_patient) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.birthDate, other.birthDate);
    }

    /**
     * String of the attributes of the object
     *
     * @return String
     */
    @Override
    public String toString() {
        return name;
    }
}
