package Domain;

import java.util.Objects;

public class MedicalNote {
    private int ID;
    private User user;
    private Appointment appointment;
    private String notes;

    public MedicalNote(int ID, User user, Appointment appointment, String notes) {
        this.ID = ID;
        this.user = user;
        this.appointment = appointment;
        this.notes = notes;
    }

    public MedicalNote(User user, Appointment appointment, String notes) {
        this.user = user;
        this.appointment = appointment;
        this.notes = notes;
    }

    public MedicalNote(int ID) {
        this.ID = ID;
    }

    public MedicalNote() {
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
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
        return "MedicalNote{" + "ID=" + ID + ", doctor=" + user + ", appointment=" + appointment + ", notes=" + notes + '}';
    }
        
}
