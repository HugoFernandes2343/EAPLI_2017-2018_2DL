package eapli.ecafeteria.domain.ratings;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.reservations.Reservation;
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

    @Embedded
    private Comment comment;

    @ManyToOne
    private Reservation reservation;

    public MealRating() {
    }

    public MealRating(Reservation reservation, int ratingNumber) {
        try {
            this.reservation = reservation;
            setRating(new Score(ratingNumber));
            this.comment=null;
        } catch (NullPointerException e) {
            System.out.println("Error: " + e);
        }
    }

    public MealRating(Reservation reservation, int ratingNumber, String comment) {
        if (reservation == null) {
            throw new NullPointerException();
        }
        this.reservation = reservation;
        setRating(new Score(ratingNumber));
        this.comment=new Comment(comment);
    }

    private void setRating(Score newRating) {
        if (checkRatingNumber(newRating)) {
            this.ratingNumber = newRating;
        } 
    }

    private boolean checkRatingNumber(Score newRating) {
        if (newRating.score() > 5 || newRating.score() < 1) {
            throw new NumberFormatException();
        } else {
            return true;
        }
    }

    

    public Reservation reservation() {
        return reservation;
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
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.id);
        hash = 47 * hash + Objects.hashCode(this.ratingNumber);
        hash = 47 * hash + Objects.hashCode(this.reservation);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.ratingNumber, other.ratingNumber)) {
            return false;
        }
        if (!Objects.equals(this.reservation, other.reservation)) {
            return false;
        }
        return true;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof MealRating)) {
            return false;
        }

        final MealRating that = (MealRating) other;
        if (this == that) {
            return true;
        }

        return id().equals(that.id()) && ratingNumber.equals(that.ratingNumber)
                && comment.equals(that.comment) && reservation.equals(that.reservation);
    }

    @Override
    public String toString() {
        return ("Meal Rating ->     Date the meal was consumed: " + reservation.meal().date() + "     Meal: " + reservation.meal().toString() + "       Rating: " + ratingNumber);
    }
}
