package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.ddd.AggregateRoot;
import eapli.util.Strings;
import java.io.Serializable;
import javax.persistence.*;

/**
 * a dish type, e.g., vegetarian or fish or meat.
 * <p>
 * this class is implemented in a more traditional way than DDD, by using
 * primitive types for the attributes instead of value objects. this means that
 * some semantic is lost and potential code duplication may rise if the same
 * concept is used as an attribute in more than one class. however, the learning
 * curve is smoother when compared to full DDD.
 * <p>
 * <p>
 * Created by MCN on 29/03/2016.
 */
@Entity
public class DishType implements AggregateRoot<String>, Serializable {

    private static final long serialVersionUID = 1L;

    // ORM primary key
    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;

    // business ID
    @Column(unique = true)
    private String acronym;
    private String description;
    private boolean active;

    protected DishType() {
        // for ORM
    }

    /**
     * DishType constructor.
     *
     * @param name Mandatory
     * @param description Mandatory
     */
    public DishType(String name, String description) {
        setName(name);
        setDescription(description);
        this.active = true;
    }

    /**
     * Sets and validates newDescription.
     *
     * @param newDescription
     */
    private void setDescription(String newDescription) {
        if (descriptionMeetsMinimumRequirements(newDescription)) {
            this.description = description;
        } else {
            throw new IllegalArgumentException("Invalid Description");
        }
    }

    /**
     * Sets and validates newName.
     *
     * @param newName
     */
    private void setName(String newName) {
        if (nameMeetsMinimumRequirements(newName)) {
            this.acronym = newName;
        } else {
            throw new IllegalArgumentException("Invalid Name");
        }
    }

    /**
     * Ensure name is not null or empty.
     *
     * @param name
     * @return True if name meets minimum requirements. False if name does not
     * meet minimum requirements.
     */
    private boolean nameMeetsMinimumRequirements(String name) {
        return !Strings.isNullOrEmpty(name);
    }

    /**
     * Ensure description is not null or empty.
     *
     * @param description
     * @return True if description meets minimum requirements. False if
     * description does not meet minimum requirements.
     */
    private boolean descriptionMeetsMinimumRequirements(String description) {
        return !Strings.isNullOrEmpty(description);
    }

    public String description() {
        return this.description;
    }

    public boolean isActive() {
        return this.active;
    }

    /**
     * Toggles the state of the dishtype, activating it or deactivating it
     * accordingly.
     *
     * @return whether the dishtype is active or not
     */
    public boolean toogleState() {

        this.active = !this.active;
        return isActive();
    }

    /**
     * Change DishType description
     *
     * @param newDescription New description.
     */
    public void changeDescriptionTo(String newDescription) {
        if (!descriptionMeetsMinimumRequirements(newDescription)) {
            throw new IllegalArgumentException();
        }
        this.description = newDescription;
    }

    @Override
    public boolean is(String id) {
        return id.equalsIgnoreCase(this.acronym);
    }

    @Override
    public String id() {
        return this.acronym;
    }

    @Override
    public boolean sameAs(Object other) {
        final DishType dishType = (DishType) other;
        return this.equals(dishType) && description().equals(dishType.description())
                && isActive() == dishType.isActive();
    }

    @Override
    public int hashCode() {
        return this.acronym.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DishType)) {
            return false;
        }

        final DishType other = (DishType) o;
        return id().equals(other.id());
    }

}
