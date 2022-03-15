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

    public Medicine() {
    }

    public Medicine(int id_medicine) {
        this.id_medicine = id_medicine;
    }

    public Medicine(int id_medicine, String name, double amount, double ingest, String indications, Date dueDate) {
        this.id_medicine = id_medicine;
        this.name = name;
        this.amount = amount;
        this.ingest = ingest;
        this.indications = indications;
        this.dueDate = dueDate;
    }

    public Medicine(String name, double amount, double ingest, String indications, Date dueDate) {
        this.name = name;
        this.amount = amount;
        this.ingest = ingest;
        this.indications = indications;
        this.dueDate = dueDate;
    }

    public int getID() {
        return id_medicine;
    }

    public void setID(int id_medicine) {
        this.id_medicine = id_medicine;
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
        hash = 29 * hash + Objects.hashCode(this.id_medicine);
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
        return Objects.equals(this.id_medicine, other.id_medicine);
    }

    @Override
    public String toString() {
        return "Medicine: " + "Id: " + id_medicine + ", Name: " + name + ", Amount: " + amount + ", Ingest: " + ingest + ", Indications: " + indications + ", Due Date: " + dueDate;
    }

    
    
    
}
