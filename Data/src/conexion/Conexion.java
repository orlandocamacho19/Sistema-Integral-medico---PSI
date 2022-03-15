package conexion;

import java.sql.*;

public class Conexion {

    Connection con;

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

    public void conect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/intento?user=root&password=admin");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void disconect() {
        try {
            if (con != null) {
                if (con.isClosed() == false) {
                    con.close();
                }
            }
        } catch (Exception e) {
            e.getStackTrace();
        }
    }
}
