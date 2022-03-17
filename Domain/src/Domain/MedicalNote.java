package Domain;

import java.util.Objects;

public class MedicalNote {

    private int ID;
    private User user;
    private Appointment appointment;
    private String notes;

    /**
     * Constructor
     */
    public MedicalNote() {
    }

    /**
     * Constructor that receives the medical note ID
     *
     * @param ID medical note ID
     */
    public MedicalNote(int ID) {
        this.ID = ID;
    }

    /**
     * Constructor that receives the user, appointment and notes
     *
     * @param user User (Doctor or nurse)
     * @param appointment Appointment
     * @param notes Notes
     */
    public MedicalNote(User user, Appointment appointment, String notes) {
        this.user = user;
        this.appointment = appointment;
        this.notes = notes;
    }

    /**
     * Constructor that receives the medical note ID, the user, appointment and
     * notes
     *
     * @param ID medical note ID
     * @param user User (Doctr or nurse)
     * @param appointment Appointment
     * @param notes Notes
     */
    public MedicalNote(int ID, User user, Appointment appointment, String notes) {
        this.ID = ID;
        this.user = user;
        this.appointment = appointment;
        this.notes = notes;
    }

    /**
     * Return the notes
     *
     * @return Notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Set the notes
     *
     * @param notes Notes
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * Return the medical notes ID
     *
     * @return medical notes ID
     */
    public int getID() {
        return ID;
    }

    /**
     * Set the medical notes ID
     *
     * @param ID medical notes ID
     */
    public void setID(int ID) {
        this.ID = ID;
    }

    /**
     * Return the user
     *
     * @return User (Doctor or nurse)
     */
    public User getUser() {
        return user;
    }

    /**
     * Set the user
     *
     * @param user User (Doctor or nurse)
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Return the appointment
     *
     * @return appointment
     */
    public Appointment getAppointment() {
        return appointment;
    }

    /**
     * Set the appointment
     *
     * @param appointment Appointment
     */
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    /**
     * Hash code method
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.ID);
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
        final MedicalNote other = (MedicalNote) obj;
        return Objects.equals(this.ID, other.ID);
    }

    /**
     * String of the attributes of the object
     *
     * @return String
     */
    @Override
    public String toString() {
        return "MedicalNote{" + "ID=" + ID + ", doctor=" + user + ", appointment=" + appointment + ", notes=" + notes + '}';
    }
}
