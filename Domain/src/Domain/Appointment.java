package Domain;

import java.sql.Timestamp;
import java.util.Objects;

public class Appointment {

    private int id_appointment;
    private Timestamp startTime;
    private Patient patient;
    private AppointmentType aType;
    private Type type;
    private Medicine medicine;
    private Payment payment;
    private boolean confirmation;

    public Appointment() {
    }

    public Appointment(int id_appointment) {
        this.id_appointment = id_appointment;
    }

    public Appointment(int id_appointment, Timestamp startTime) {
        this.id_appointment = id_appointment;
        this.startTime = startTime;
    }

    public Appointment(Timestamp startTime, Patient patient, Medicine medicine, Payment payment, AppointmentType aType, Type type, boolean confirmation) {
        this.startTime = startTime;
        this.patient = patient;
        this.medicine = medicine;
        this.payment = payment;
        this.aType = aType;
        this.type = type;
        this.confirmation = confirmation;
    }

    public Appointment(int id_appointment, Timestamp startTime, Patient patient, Medicine medicine, Payment payment, AppointmentType aType, Type type, boolean confirmation) {
        this.id_appointment = id_appointment;
        this.startTime = startTime;
        this.patient = patient;
        this.aType = aType;
        this.type = type;
        this.medicine = medicine;
        this.payment = payment;
        this.confirmation = confirmation;
    }

    public int getId_appointment() {
        return id_appointment;
    }

    public void setId_appointment(int id_appointment) {
        this.id_appointment = id_appointment;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public AppointmentType getaType() {
        return aType;
    }

    public void setaType(AppointmentType aType) {
        this.aType = aType;
    }

    
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }
    
    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id_appointment);
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
        return Objects.equals(this.id_appointment, other.id_appointment);
    }

    @Override
    public String toString() {
        return "Appointment{" + "ID=" + id_appointment + ", startTime=" + startTime + ", patient=" + patient.getID() + ", aType=" + aType + ", type=" + type + ", medicine=" + medicine.getId_medicine()+ ", payment=" + payment.getId_payment() + ", confirmation=" + confirmation;
    }
   
}
