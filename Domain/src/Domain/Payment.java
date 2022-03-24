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

    /**
     * Constructor
     */
    public Payment() {
    }

    /**
     * Constructor that receives the payment id
     *
     * @param id_payment Payment ID
     */
    public Payment(int id_payment) {
        this.id_payment = id_payment;
    }

    /**
     * Constructor that receives the payment amount and if its paid out
     *
     * @param Amount Amount
     * @param paidOut Paid out
     */
    public Payment(double Amount, boolean paidOut) {
        this.Amount = Amount;
        this.paidOut = paidOut;
    }

    /**
     * Constructor that receives the payment id, amount and if its paid out
     *
     * @param id_payment Payment ID
     * @param Amount Amount
     * @param paidOut Paid out
     */
    public Payment(int id_payment, double Amount, boolean paidOut) {
        this.id_payment = id_payment;
        this.Amount = Amount;
        this.paidOut = paidOut;
    }

    /**
     * Return payment ID
     *
     * @return Payment ID
     */
    public int getId_payment() {
        return id_payment;
    }

    /**
     * Set payment ID
     *
     * @param id_payment Payment ID
     */
    public void setId_payment(int id_payment) {
        this.id_payment = id_payment;
    }

    /**
     * Return payment amount
     *
     * @return Amount
     */
    public double getAmount() {
        return Amount;
    }

    /**
     * Set payment amount
     *
     * @param Amount Amount
     */
    public void setAmount(double Amount) {
        this.Amount = Amount;
    }

    /**
     * Return payments list
     *
     * @return Payments list
     */
    public List<Double> getPayments() {
        return payments;
    }

    /**
     * Set payments
     *
     * @param payments Payments
     */
    public void setPayments(List<Double> payments) {
        this.payments = payments;
    }

    /**
     * Return payments dates list
     *
     * @return Payments dates list
     */
    public List<Date> getPaymentsDate() {
        return paymentsDate;
    }

    /**
     * Set payments dates list
     *
     * @param paymentsDate Payments dates list
     */
    public void setPaymentsDate(List<Date> paymentsDate) {
        this.paymentsDate = paymentsDate;
    }

    /**
     * Return if the payment is paid out
     *
     * @return paid out
     */
    public boolean isPaidOut() {
        return paidOut;
    }

    /**
     * Set the paid out
     *
     * @param paidOut Paid out
     */
    public void setPaidOut(boolean paidOut) {
        this.paidOut = paidOut;
    }

    /**
     * Hash code method
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id_payment);
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
        final Payment other = (Payment) obj;
        return Objects.equals(this.id_payment, other.id_payment);
    }

    /**
     * String of the attributes of the object
     *
     * @return String
     */
    @Override
    public String toString() {
        return "Payment: " + "Id: " + id_payment + ", Amount: " + Amount + ", Paid Out: " + paidOut;
    }
}
