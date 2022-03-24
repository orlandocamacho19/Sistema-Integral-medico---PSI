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
    private String reason;
    
    /**
     * Constructor
     */
    public Appointment() {
    }

    /**
     * Constructor that receives the appointment ID
     *
     * @param id_appointment appointment ID
     */
    public Appointment(int id_appointment) {
        this.id_appointment = id_appointment;
    }

    /**
     * Constructor that receives the appointment ID and start time
     *
     * @param id_appointment appointment ID
     * @param startTime start time
     */
    public Appointment(int id_appointment, Timestamp startTime) {
        this.id_appointment = id_appointment;
        this.startTime = startTime;
    }

    /**
     * Constructor that receives the appointment start time, patient, medicine,
     * payment, apointment type, type, confirmation and reason
     *
     * @param startTime start time
     * @param patient patient
     * @param medicine medicine
     * @param payment payment
     * @param aType appointment type
     * @param type type
     * @param confirmation confirmation
     * @param reason reason
     */
    public Appointment(Timestamp startTime, Patient patient, Medicine medicine, Payment payment, AppointmentType aType, Type type, boolean confirmation, String reason) {
        this.startTime = startTime;
        this.patient = patient;
        this.medicine = medicine;
        this.payment = payment;
        this.aType = aType;
        this.type = type;
        this.confirmation = confirmation;
        this.reason = reason;
    }

    /**
     * Constructor that receives the appointment ID, start time, patient,
     * medicine, payment, apointment type, type, confirmation and reason
     *
     * @param id_appointment appointmen ID
     * @param startTime start time
     * @param patient patient
     * @param medicine medicine
     * @param payment payment
     * @param aType appointment type
     * @param type type
     * @param confirmation confirmation
     * @param reason reason
     */
    public Appointment(int id_appointment, Timestamp startTime, Patient patient, Medicine medicine, Payment payment, AppointmentType aType, Type type, boolean confirmation, String reason) {
        this.id_appointment = id_appointment;
        this.startTime = startTime;
        this.patient = patient;
        this.aType = aType;
        this.type = type;
        this.medicine = medicine;
        this.payment = payment;
        this.confirmation = confirmation;
        this.reason = reason;
    }

    /**
     * Return the appointment ID
     *
     * @return appointment ID
     */
    public int getId_appointment() {
        return id_appointment;
    }

    /**
     * Set the appointment ID
     *
     * @param id_appointment appointment ID
     */
    public void setId_appointment(int id_appointment) {
        this.id_appointment = id_appointment;
    }

    /**
     * Return the patient
     *
     * @return patient
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Set the patient
     *
     * @param patient patient
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Return the appointment type
     *
     * @return appointment type
     */
    public AppointmentType getaType() {
        return aType;
    }

    /**
     * Set the appointment type
     *
     * @param aType appointment type
     */
    public void setaType(AppointmentType aType) {
        this.aType = aType;
    }

    /**
     * Return the type
     *
     * @return type
     */
    public Type getType() {
        return type;
    }

    /**
     * Set the type
     *
     * @param type type
     */
    public void setType(Type type) {
        this.type = type;
    }

    /**
     * Return the appointment start time
     *
     * @return start time
     */
    public Timestamp getStartTime() {
        return startTime;
    }

    /**
     * Set the appointment start time
     *
     * @param startTime start time
     */
    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    /**
     * Return the medicine
     *
     * @return medicine
     */
    public Medicine getMedicine() {
        return medicine;
    }

    /**
     * Set the medicine
     *
     * @param medicine medicine
     */
    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    /**
     * Return the payment
     *
     * @return payment
     */
    public Payment getPayment() {
        return payment;
    }

    /**
     * Set the payment
     *
     * @param payment payment
     */
    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    /**
     * Return if the appointment is confirmed
     *
     * @return confirmation
     */
    public boolean isConfirmation() {
        return confirmation;
    }

    /**
     * Set the appointment confirmation
     *
     * @param confirmation confirmation
     */
    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }

     /**
     * Return the reason
     *
     * @return confirmation
     */
    public String getReason() {
        return reason;
    }

    /**
     * Set the reason 
     *
     * @param reason reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * Hash code method
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id_appointment);
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
        final Appointment other = (Appointment) obj;
        return Objects.equals(this.id_appointment, other.id_appointment);
    }
    
    /**
     * String of the attributes of the object
     *
     * @return String
     */
    @Override
    public String toString() {
        return  startTime.toString();
    }

    
    
}
