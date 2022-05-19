package connection;

import java.sql.*;

public class ConnectionDB {

    Connection con;

    /**
     * Return connection
     * @return connection
     */
    public Connection getCon() {
        return con;
    }

    /**
     * Set connection
     * @param con connection
     */
    public void setCon(Connection con) {
        this.con = con;
    }

    /**
     * Establishes the connection with the database
     */
    public void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            //con = DriverManager.getConnection("jdbc:mysql://localhost:3306/intento?user=root&password=9103");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/secretaria_salud?user=root&password=Carlosalv089");
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    /**
     * Closes the connection to the database, disconnects
     */
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
