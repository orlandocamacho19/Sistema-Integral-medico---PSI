package Domain;

import java.util.Date;
import java.util.Objects;

/**
 * @author Equipo 1
 */
public class Appointment {
    private final String ID;
    private Date startTime;
    private final Patient patient;
    private final AppointmentType aType;
    private final Type type;
    private Medicine medicine;
    private Payment payment;
    private boolean confirmation;
    private String notes;


    public Appointment(String ID, Date startTime, Patient patient, AppointmentType aType, Type type, Medicine medicine, Payment payment, boolean confirmation, String notes) {
        this.ID = ID;
        this.startTime = startTime;
        this.patient = patient;
        this.aType = aType;
        this.type = type;
        this.medicine = medicine;
        this.payment = payment;
        this.confirmation = confirmation;
        this.notes = notes;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public boolean isConfirmation() {
        return confirmation;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.ID);
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
        final Appointment other = (Appointment) obj;
        return Objects.equals(this.ID, other.ID);
    }

    @Override
    public String toString() {
        return "Appointment{" + "ID=" + ID + ", startTime=" + startTime + ", patient=" + patient + ", aType=" + aType + ", type=" + type + ", medicine=" + medicine + ", payment=" + payment + ", confirmation=" + confirmation + ", notes=" + notes + '}';
    }
   
}
