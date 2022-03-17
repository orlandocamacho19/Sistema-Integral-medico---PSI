package Domain;

import java.util.Date;
import java.util.Objects;

public class Medicine {

    private int id_medicine;
    private String name;
    private double amount;
    private double ingest;
    private String indications;
    private Date dueDate;

    /**
     * Constructor
     */
    public Medicine() {
    }

    /**
     * Constructor that receives the medicine ID
     *
     * @param id_medicine Medicine ID
     */
    public Medicine(int id_medicine) {
        this.id_medicine = id_medicine;
    }

    /**
     * Constructor that receives the medicine ID, name, amount, ingest,
     * indications, and due date
     *
     * @param id_medicine Medicine ID
     * @param name Name
     * @param amount Amount
     * @param ingest Ingest
     * @param indications Indications
     * @param dueDate Due date
     */
    public Medicine(int id_medicine, String name, double amount, double ingest, String indications, Date dueDate) {
        this.id_medicine = id_medicine;
        this.name = name;
        this.amount = amount;
        this.ingest = ingest;
        this.indications = indications;
        this.dueDate = dueDate;
    }

    /**
     * Constructor that receives the medicine name, amount, ingest, indications
     * and due date
     *
     * @param name Name
     * @param amount Amount
     * @param ingest Ingest
     * @param indications Indications
     * @param dueDate Due date
     */
    public Medicine(String name, double amount, double ingest, String indications, Date dueDate) {
        this.name = name;
        this.amount = amount;
        this.ingest = ingest;
        this.indications = indications;
        this.dueDate = dueDate;
    }

    /**
     * Return the medicine name
     *
     * @return Medicine name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the medicine name
     *
     * @param name Medicine name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the amount
     *
     * @return Amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Set the amount
     *
     * @param amount Amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Return the medicine ID
     *
     * @return Medicine ID
     */
    public int getId_medicine() {
        return id_medicine;
    }

    /**
     * Set the medicine ID
     *
     * @param id_medicine medicine ID
     */
    public void setId_medicine(int id_medicine) {
        this.id_medicine = id_medicine;
    }

    /**
     * Return the ingest
     *
     * @return Ingest
     */
    public double getIngest() {
        return ingest;
    }

    /**
     * Set the ingest
     *
     * @param ingest Ingest
     */
    public void setIngest(double ingest) {
        this.ingest = ingest;
    }

    /**
     * Return the indications
     *
     * @return Indications
     */
    public String getIndications() {
        return indications;
    }

    /**
     * Set the indications
     *
     * @param indications
     */
    public void setIndications(String indications) {
        this.indications = indications;
    }

    /**
     * Return the due date
     *
     * @return Due date
     */
    public Date getDueDate() {
        return dueDate;
    }

    /**
     * Set the due date
     *
     * @param dueDate Due date
     */
    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    /**
     * Hash code method
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id_medicine);
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
        final Medicine other = (Medicine) obj;
        return Objects.equals(this.id_medicine, other.id_medicine);
    }

    /**
     * String of the attributes of the object
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Medicine: " + "Id: " + id_medicine + ", Name: " + name + ", Amount: " + amount + ", Ingest: " + ingest + ", Indications: " + indications + ", Due Date: " + dueDate;
    }

}
