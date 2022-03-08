package Domain;

import java.util.Date;
import java.util.Objects;

/**
 * @author Equipo 1
 */
public class Medicine {
    private String ID;
    private String name;
    private double amount;
    private double ingest;
    private String indications;
    private Date dueDate;

    public Medicine(String ID, String name, double amount, double ingest, String indications, Date dueDate) {
        this.ID = ID;
        this.name = name;
        this.amount = amount;
        this.ingest = ingest;
        this.indications = indications;
        this.dueDate = dueDate;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getIngest() {
        return ingest;
    }

    public void setIngest(double ingest) {
        this.ingest = ingest;
    }

    public String getIndications() {
        return indications;
    }

    public void setIndications(String indications) {
        this.indications = indications;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
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
        final Medicine other = (Medicine) obj;
        return Objects.equals(this.ID, other.ID);
    }

    @Override
    public String toString() {
        return "Medicine{" + "ID=" + ID + ", name=" + name + ", amount=" + amount + ", ingest=" + ingest + ", indications=" + indications + ", dueDate=" + dueDate + '}';
    }

    
    
    
}
