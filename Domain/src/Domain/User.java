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

    /**
     * Constructor
     */
    public User() {
    }

    /**
     * Constructor that receives user ID
     *
     * @param id User ID
     */
    public User(int id) {
        this.id = id;
    }

    /**
     * Constructor that receives user ID, name, email, password, phone,
     * ocupation and specialty
     *
     * @param id User ID
     * @param name Name
     * @param email Email
     * @param password Password
     * @param phone Phone number
     * @param ocupation Ocupation
     * @param specialty Specialty
     */
    public User(int id, String name, String email, String password, String phone, Ocupation ocupation, String specialty) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.ocupation = ocupation;
        this.specialty = specialty;
    }

    /**
     * Constructor that receives user name, email, password, phone, ocupation,
     * specialty
     *
     * @param name Name
     * @param email Email
     * @param password Password
     * @param phone Phone number
     * @param ocupation Ocupation
     * @param specialty Specialty
     */
    public User(String name, String email, String password, String phone, Ocupation ocupation, String specialty) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.ocupation = ocupation;
        this.specialty = specialty;
    }

    /**
     * Return user ocupation
     *
     * @return Ocupation
     */
    public Ocupation getOcupation() {
        return ocupation;
    }

    /**
     * Set user ocupation
     *
     * @param ocupation Ocupation
     */
    public void setOcupation(Ocupation ocupation) {
        this.ocupation = ocupation;
    }

    /**
     * Return user ID
     *
     * @return User ID
     */
    public int getId() {
        return id;
    }

    /**
     * Set user ID
     *
     * @param id User ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Return user name
     *
     * @return User name
     */
    public String getName() {
        return name;
    }

    /**
     * Set user name
     *
     * @param name User name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return user email
     *
     * @return User email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set user email
     *
     * @param email User email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Return user password
     *
     * @return User password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set user password
     *
     * @param password User password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Return user phone number
     *
     * @return User phone number
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Set user phone number
     *
     * @param phone phone number
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Return user specialty
     *
     * @return User specialty
     */
    public String getSpecialty() {
        return specialty;
    }

    /**
     * Set user specialty
     *
     * @param specialty User specialty
     */
    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    /**
     * Return user appointmens list
     *
     * @return User appointments list
     */
    public List<Appointment> getAppointments() {
        return appointments;
    }

    /**
     * Set user appointments list
     *
     * @param appointments User appointments list
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments = appointments;
    }

    /**
     * Hash code method
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final User other = (User) obj;
        return Objects.equals(this.id, other.id);
    }

    /**
     * String of the attributes of the object
     *
     * @return String
     */
    @Override
    public String toString() {
        return "User: " + "Id: " + id + ", Name: " + name + ", Email: " + email + ", Password: " + password + ", Phone: " + phone + ", Ocupation: " + ocupation + ", Specialty: " + specialty;
    }
}
