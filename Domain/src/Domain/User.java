package Domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private Ocupation ocupation;
    private String specialty;
    private List<Appointment> appointments = new ArrayList<>();

    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public User(int id, String name, String email, String password, String phone, Ocupation ocupation, String specialty) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.ocupation = ocupation;
        this.specialty = specialty;
    }

    public User(String name, String email, String password, String phone, Ocupation ocupation, String specialty) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.ocupation = ocupation;
        this.specialty = specialty;
    }

    public Ocupation getOcupation() {
        return ocupation;
    }

    public void setOcupation(Ocupation ocupation) {
        this.ocupation = ocupation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final User other = (User) obj;
        return Objects.equals(this.id, other.id);
    }

    @Override
    public String toString() {
        return "User: " + "Id: " + id + ", Name: " + name + ", Email: " + email + ", Password: " + password + ", Phone: " + phone + ", Ocupation: " + ocupation + ", Specialty: " + specialty;
    }
}
