package DAO;

import Domain.Payment;
import conexion.Conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO extends Conexion {

    public void insert(Payment payment) {
        try {
            this.conect();
            String sql = "insert into payments (amount, paid_out) values (?, ?)";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setDouble(1, payment.getAmount());
            ps.setBoolean(2, payment.isPaidOut());

            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void update(int id, Payment payment) {
        try {
            this.conect();
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

    public void delete(int id) {
        try {
            this.conect();
            String sql = "delete from payments where id_payment=?";
            PreparedStatement ps = this.getCon().prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public List consultAll() {
        ResultSet res;
        List payments = new ArrayList();
        try {
            this.conect();
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
}
