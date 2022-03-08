package Domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author Equipo 1
 */
public class Payment {
    private String ID;
    private double Amount;
    private List<Double> payments = new ArrayList<>();
    private List<Date> paymentsDate = new ArrayList<>();
    private boolean paidOut;

    public Payment(String ID, double Amount, boolean paidOut) {
        this.ID = ID;
        this.Amount = Amount;
        this.paidOut = paidOut;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public double getAmount() {
        return Amount;
    }

    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

    public List<Double> getPayments() {
        return payments;
    }

    public void setPayments(List<Double> payments) {
        this.payments = payments;
    }

    public List<Date> getPaymentsDate() {
        return paymentsDate;
    }

    public void setPaymentsDate(List<Date> paymentsDate) {
        this.paymentsDate = paymentsDate;
    }

    public boolean isPaidOut() {
        return paidOut;
    }

    public void setPaidOut(boolean paidOut) {
        this.paidOut = paidOut;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.ID);
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
        final Payment other = (Payment) obj;
        return Objects.equals(this.ID, other.ID);
    }

    @Override
    public String toString() {
        return "Payment{" + "ID=" + ID + ", Amount=" + Amount + ", payments=" + payments + ", paymentsDate=" + paymentsDate + ", paidOut=" + paidOut + '}';
    }
    
    
    
}
