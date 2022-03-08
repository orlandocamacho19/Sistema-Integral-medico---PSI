package Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Equipo 1
 */
public class Doctor {
    private final String ID;
    private final String name;
    private String email;
    private String password;
    private String phone;
    private String specialty;
    private List<Appointment> appointments = new ArrayList<>();

    public Doctor(String ID, String name, String email, String password, String phone, String specialty) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.specialty = specialty;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public List<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.ID);
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
        final Doctor other = (Doctor) obj;
        return Objects.equals(this.ID, other.ID);
    }

    @Override
    public String toString() {
        return "Doctor{" + "ID=" + ID + ", name=" + name + ", email=" + email + ", password=" + password + ", phone=" + phone + ", specialty=" + specialty + ", appointments=" + appointments + '}';
    }  
}
