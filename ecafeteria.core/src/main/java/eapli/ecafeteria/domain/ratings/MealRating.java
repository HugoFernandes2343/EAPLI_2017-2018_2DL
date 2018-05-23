package eapli.ecafeteria.domain.ratings;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 *
 * @author Andre Rodrigues <1151136@isep.ipp.pt>
 */
@Entity
public class MealRating implements AggregateRoot<Long>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Score ratingNumber;

    @ManyToOne
    private CafeteriaUser user;

    @ManyToOne
    private Meal meal;

    public MealRating() {
    }

    public MealRating(CafeteriaUser user, Meal meal, int ratingNumber) {
        if (user == null || meal == null) {
            throw new IllegalStateException();
        }
        this.user = user;
        this.meal = meal;
        setRating(new Score(ratingNumber));
    }

    private void setRating(Score newRating) {
        if (checkRatingNumber(newRating)) {
            this.ratingNumber = newRating;
        } else {
            throw new IllegalArgumentException("Invalid rating");
        }
    }

    private boolean checkRatingNumber(Score newRating) {
        if (newRating.score() > 5) {
            throw new NumberFormatException("Insert a positive number, between 1 and 5");
        } else if (newRating.score() < 1) {
            throw new NumberFormatException("Insert a positive number, between 1 and 5");
        } else {
            return true;
        }
    }

    public CafeteriaUser user() {
        return user;
    }

    public Meal meal() {
        return meal;
    }

    public Score ratingNumber() {
        return ratingNumber;
    }

    @Override
    public Long id() {
        return id;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.ratingNumber);
        hash = 97 * hash + Objects.hashCode(this.user);
        hash = 97 * hash + Objects.hashCode(this.meal);
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
        final MealRating other = (MealRating) obj;
        if (this.ratingNumber != other.ratingNumber) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.user, other.user)) {
            return false;
        }
        if (!Objects.equals(this.meal, other.meal)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof MealRating)) {
            return false;
        }

        final MealRating mr = (MealRating) other;
        if (this == mr) {
            return true;
        }

        if (!this.id.equals(mr.id)) {
            return false;
        }

        if (!this.user.equals(mr.user)) {
            return false;
        }

        if (!this.meal.equals(mr.meal)) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return ("Meal Rating ->     Date the meal was consumed: " + meal.date() + "     Meal: " + meal.toString() + "       Rating: " + ratingNumber);
    }
}
