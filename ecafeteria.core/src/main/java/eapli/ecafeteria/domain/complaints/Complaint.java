package eapli.ecafeteria.domain.complaints;


import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.ddd.DomainEntity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author <1160777@isep.ipp.pt>Marco Carneiro</1160777@isep.ipp.pt>
 */
@Entity
public class Complaint implements Serializable, DomainEntity<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;

    @ManyToOne()
    private Meal meal;

    @OneToOne()
    private CafeteriaUser user;

    private String title;

    private String description;

    /**
     * ORM complaint constructor
     */
    protected Complaint() {
        //for ORM
    }

    /**
     * Full constructor, for full filled and identified complaints
     *
     * @param user
     * @param meal
     * @param title
     * @param description
     */
    public Complaint(CafeteriaUser user, Meal meal, String title, String description) {
        this.meal = meal;
        this.user = user;
        this.title = title;
        this.description = description;
    }

    /**
     * Regular identified constructor, when a user requires identification but the complaint has nothing
     * to do with a Meal
     *
     * @param user
     * @param title
     * @param description
     */
    public Complaint(CafeteriaUser user, String title, String description) {
        this.user = user;
        this.title = title;
        this.description = description;
    }

    /**
     * Anonymous Meal complaint
     *
     * @param meal
     * @param title
     * @param description
     */
    public Complaint(Meal meal, String title, String description) {
        this.meal = meal;
        this.title = title;
        this.description = description;
    }

    /**
     * Anonymous regular complaint, no user nor meal data is provided
     *
     * @param title
     * @param description
     */
    public Complaint(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String complainantTitle() {
        return this.title;
    }

    public String complainantDescription() {
        return this.description;
    }

    /**
     * Returns the meal to which the complaint was made
     *
     * @return Meal
     */
    public Meal meal() {
        return this.meal;
    }

    /**
     * Returns the user who filed the complain
     *
     * @return User
     */
    public CafeteriaUser complainant() {
        return this.user;
    }

    /**
     * Yet to be implemented
     *
     * @param other
     * @return
     */
    @Override
    public boolean sameAs(Object other) {
        return false;
    }

    /**
     * [Not usable here]Returns the ID of this complaint.
     *
     * @return
     */
    @Override
    public Long id() {
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Complaint)) {
            return false;
        }

        final Complaint other = (Complaint) o;
        return id().equals(other.id());
    }

    @Override
    public int hashCode() {
        return this.title.hashCode()
                +this.description.hashCode();
    }

    @Override
    public String toString() {
        return String.format("User: %s\nMeal: %s\nTitle: %s\nDescription%s\n", user, meal.toString(),title,description);
    }

}
