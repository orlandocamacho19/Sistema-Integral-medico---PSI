package DAO;

import Domain.Payment;
import connection.ConnectionDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO extends ConnectionDB {

    private static PaymentDAO paymentDAO;

    /**
     * Constructor
     */
    private PaymentDAO() {
    }

    /**
     * Return the instance of the class following the singleton pattern
     *
     * @return instance
     */
    public static PaymentDAO getInstance() {
        if (paymentDAO == null) {
            paymentDAO = new PaymentDAO();
        }
        return paymentDAO;
    }

    /**
     * Inserts in the database the payment received in parameters
     *
     * @param payment Payment
     */
    public void insert(Payment payment) {
        try {
            this.connect();
            String sql = "insert into payments (amount, paid_out) values (?, ?)";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setDouble(1, payment.getAmount());
            ps.setBoolean(2, payment.isPaidOut());

            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * Update in the database the payment received in parameters by the payment
     * ID
     *
     * @param id Payment ID
     * @param payment Payment
     */
    public void update(int id, Payment payment) {
        try {
            this.connect();
            String sql = "update payments set amount=?, paid_out=? where id_payment=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setDouble(1, payment.getAmount());
            ps.setBoolean(2, payment.isPaidOut());
            ps.setInt(3, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * Deletes from the database the payment with which the ID received in
     * parameters matches
     *
     * @param id Payment ID
     */
    public void delete(int id) {
        try {
            this.connect();
            String sql = "delete from payments where id_payment=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * Consults and returns all the payments in the database
     *
     * @return payments list
     */
    public List<Payment> consultAll() {
        ResultSet res;
        List payments = new ArrayList();
        try {
            this.connect();
            String sql = "select * from payments";
            PreparedStatement ps = this.getCon().prepareCall(sql);
            res = ps.executeQuery();
            while (res.next()) {
                Payment payment = new Payment();
                payment.setId_payment(res.getInt("id_payment"));
                payment.setAmount(res.getDouble("amount"));
                payment.setPaidOut(res.getBoolean("paid_out"));
                payments.add(payments);

            }
        } catch (Exception e) {
            e.getStackTrace();
        }
        return payments;
    }

    /**
     * Consults and returns the payment matching their IDs
     *
     * @param payment
     * @return
     */
    public boolean consultByID(Payment payment) {
        for (int i = 0; i < consultAll().size(); i++) {
            if (consultAll().get(i).getId_payment() == payment.getId_payment()) {
                return true;
            }
        }
        return false;
    }
}
