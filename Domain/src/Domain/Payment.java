package Domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Payment {
    private int id_payment;
    private double Amount;
    private List<Double> payments = new ArrayList<>();
    private List<Date> paymentsDate = new ArrayList<>();
    private boolean paidOut;

    public Payment() {
    }

    public Payment(int id_payment) {
        this.id_payment = id_payment;
    }

    public Payment(double Amount, boolean paidOut) {
        this.Amount = Amount;
        this.paidOut = paidOut;
    }

    public Payment(int id_payment, double Amount, boolean paidOut) {
        this.id_payment = id_payment;
        this.Amount = Amount;
        this.paidOut = paidOut;
    }

    public int getId_payment() {
        return id_payment;
    }

    public void setId_payment(int id_payment) {
        this.id_payment = id_payment;
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
        hash = 97 * hash + Objects.hashCode(this.id_payment);
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
        return Objects.equals(this.id_payment, other.id_payment);
    }

    @Override
    public String toString() {
        return "Payment: " + "Id: " + id_payment + ", Amount: " + Amount + ", Paid Out: " + paidOut;
    }
    
    
    
}
