package Domain;

import java.util.Date;
import java.util.Objects;

public class Medicine {

    private int id_medicine;
    private String name;
    private int amount;
    private String ingredient;
    private int mgIngredient;
    private String indications;
    private boolean active;

    /**
     * Constructor
     */
    public Medicine() {
    }

    /**
     * Constructor that receives the medicine ID
     *
     * @param id_medicine Medicine ID
     */
    public Medicine(int id_medicine) {
        this.id_medicine = id_medicine;
    }

    /**
     * Constructor that receives the medicine ID, name, amount, ingredient, mgIngrediente, active and indications
     * indications, and due date
     *
     * @param id_medicine Medicine ID
     * @param name Name
     * @param amount Amount
     * @param ingredient Ingredient
     * @param mgIngredient mgIngredient
     * @param indications
     * @param active Active
     */
    public Medicine(int id_medicine, String name, int amount, String ingredient, int mgIngredient, String indications, boolean active) {
        this.id_medicine = id_medicine;
        this.name = name;
        this.amount = amount;
        this.ingredient = ingredient;
        this.mgIngredient = mgIngredient;
        this.indications = indications;
        this.active = active;
    }

    /**
     * Constructor that receives the medicine name, amount, ingredient, mgIngrediente, active and indications
     * and due date
     *
     * @param name Name
     * @param amount Amount
     * @param ingredient Ingredient
     * @param mgIngredient mgIngredient
     * @param indications
     * @param active Active
     */
    public Medicine(String name, int amount, String ingredient, int mgIngredient, String indications, boolean active) {
        this.name = name;
        this.amount = amount;
        this.ingredient = ingredient;
        this.mgIngredient = mgIngredient;
        this.indications = indications;
        this.active = active;
    }

    /**
     * Return the medicine name
     *
     * @return Medicine name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the medicine name
     *
     * @param name Medicine name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Return the amount
     *
     * @return Amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Set the amount
     *
     * @param amount Amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Return the medicine ID
     *
     * @return Medicine ID
     */
    public int getId_medicine() {
        return id_medicine;
    }

    /**
     * Set the medicine ID
     *
     * @param id_medicine medicine ID
     */
    public void setId_medicine(int id_medicine) {
        this.id_medicine = id_medicine;
    }

    /**
     * Return the ingredient
     *
     * @return Ingredient
     */
    public String getIngredient() {
        return ingredient;
    }

     /**
     * Set the ingredient
     *
     * @param ingredient  Ingredient
     */
    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

     /**
     * Return the mgIngredient
     *
     * @return mgIngredient
     */
    public int getMgIngredient() {
        return mgIngredient;
    }

     /**
     * Set the mg ingredient
     *
     * @param mgIngredient mgIngredient
     */
    public void setMgIngredient(int mgIngredient) {
        this.mgIngredient = mgIngredient;
    }

    /**
     * Return indications
     *
     * @return Indications
     */
    public String getIndications() {
        return indications;
    }

    /**
     * Set the indications
     *
     * @param indications Indications
     */
    public void setIndications(String indications) {
        this.indications = indications;
    }

     /**
     * Return active
     *
     * @return Active
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Set the mactive
     *
     * @param active Active
     */
    public void setActive(boolean active) {
        this.active = active;
    }

    /**
     * Hash code method
     *
     * @return hash code
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.id_medicine);
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
        final Medicine other = (Medicine) obj;
        if (this.id_medicine != other.id_medicine) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.ingredient, other.ingredient);
    }

    @Override
    public String toString() {
        return name;
    }

    

}
