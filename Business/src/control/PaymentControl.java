package control;

import DAO.PaymentDAO;
import Domain.Payment;

public class PaymentControl {

    private static PaymentControl paymentControl;
    private static PaymentDAO pd;

    /**
     * Constructor
     */
    private PaymentControl() {
        pd = PaymentDAO.getInstance();
    }

    /**
     * Return the instance of the class following the singleton pattern
     *
     * @return instance
     */
    public static PaymentControl getInstance() {
        if (paymentControl == null) {
            paymentControl = new PaymentControl();
        }
        return paymentControl;
    }

    /**
     * Return if was possible inserts in the database the payment received in
     * parameters
     *
     * @param payment Payment
     * @return if was possible add
     */
    public Boolean addPayment(Payment payment) {
        for (int i = 0; i < pd.consultAll().size(); i++) {
            if (payment.getId_payment() == pd.consultAll().get(i).getId_payment()) {
                return false;
            } else {
                pd.insert(payment);
                return true;
            }
        }
        return false;
    }

    /**
     * Return if was possible update in the database the payment received in
     * parameters
     *
     * @param payment Payment
     * @return if was possible update
     */
    public Boolean editPayment(Payment payment) {
        for (int i = 0; i < pd.consultAll().size(); i++) {
            if (payment.getId_payment() == pd.consultAll().get(i).getId_payment()) {
                pd.update(pd.consultAll().get(i).getId_payment(), payment);
                return true;
            }
        }
        return false;
    }

    /**
     * Return if was possible delete in the database the payment received in
     * parameters
     *
     * @param payment
     * @return if was possible delete
     */
    public Boolean deletePayment(Payment payment) {
        for (int i = 0; i < pd.consultAll().size(); i++) {
            if (payment.getId_payment() == pd.consultAll().get(i).getId_payment()) {
                pd.delete(payment.getId_payment());
                return true;
            }
        }
        return false;
    }

    /**
     * Returns all the users in the database
     */
    public void getPayments() {
        if (pd.consultAll().isEmpty()) {
            System.out.println("The database has not payments at this time");
        } else {
            System.out.println(pd.consultAll());
        }
    }
}
