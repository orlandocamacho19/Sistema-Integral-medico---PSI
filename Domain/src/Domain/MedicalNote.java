package Domain;

import java.util.Objects;

/**
 * @author Equipo 1
 */
public class MedicalNote {
    private final String ID;
    private final Doctor doctor;
    private final Appointment appointment;
    private String notes;

    public MedicalNote(String ID, Doctor doctor, Appointment appointment, String notes) {
        this.ID = ID;
        this.doctor = doctor;
        this.appointment = appointment;
        this.notes = notes;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.ID);
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
        final MedicalNote other = (MedicalNote) obj;
        return Objects.equals(this.ID, other.ID);
    }

    @Override
    public String toString() {
        return "MedicalNote{" + "ID=" + ID + ", doctor=" + doctor + ", appointment=" + appointment + ", notes=" + notes + '}';
    }
        
}
