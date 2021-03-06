/*
<<<<<<< Updated upstream
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package eapli.ecafeteria.domain.reservations;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.ReservationStateViolationException;
import eapli.framework.domain.ddd.AggregateRoot;
import eapli.framework.domain.money.Money;
import eapli.framework.util.DateTime;
import eapli.framework.util.Strings;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;

/**
 * Class Reservation
 *
 * @author 1160777 & 1161569
 */
@Entity
public class Reservation implements AggregateRoot<String>, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;

    // business ID
    @Column(unique = true)
    private String code;

    @ManyToOne()
    private Meal meal;

    @ManyToOne()
    private CafeteriaUser user;

    @Enumerated(EnumType.STRING)
    private ReservationState currentState;

    private boolean active;

    protected Reservation() {
        //ORM
        this.active = true;
    }

    /**
     * Reservation Class
     *
     * @param code
     * @param user
     * @param meal
     */
    public Reservation(String code, Meal meal, CafeteriaUser user) {
        if (Strings.isNullOrEmpty(code)) {
            throw new IllegalArgumentException();
        }
        this.code = code;
        this.meal = meal;
        this.currentState = ReservationState.BOOKED;
        this.user = user;
        this.active = true;
    }

    public void cancel() throws ReservationStateViolationException {
        if (this.currentState == ReservationState.BOOKED) {
            this.currentState = ReservationState.CANCELLED;
        } else {
            throw new ReservationStateViolationException();
        }
    }

    public void deliver() throws ReservationStateViolationException {
        if (this.currentState == ReservationState.BOOKED) {
            this.currentState = ReservationState.DELIVERED;
        } else {
            throw new ReservationStateViolationException();
        }
    }

    public void expire() throws ReservationStateViolationException {
        if (this.currentState == ReservationState.BOOKED) {
            this.currentState = ReservationState.EXPIRED;
        } else {
            throw new ReservationStateViolationException();
        }
    }

    @Override
    public boolean sameAs(Object other) {
        return false;//Unfinished
    }

    @Override
    public boolean is(String code) {
        return code.equalsIgnoreCase(this.code);
    }

    @Override
    public String id() {
        return this.code;
    }

    public ReservationState state() {
        return this.currentState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Reservation)) {
            return false;
        }

        final Reservation other = (Reservation) o;
        return id().equals(other.id());
    }

    /**
     * InMemory testing purposes. Is true of the object is created.
     *
     * @return boolean
     */
    public boolean isCreated() {
        return this.active;
    }

    /**
     * Return the assigned meal of this reservation
     *
     * @return
     */
    public Meal meal() {
        return this.meal;
    }

    /**
     * Return the assigned user of this reservation
     *
     * @return
     */
    public CafeteriaUser user() {
        return this.user;
    }

    @Override
    public int hashCode() {
        return this.code.hashCode();
    }

    @Override
    public String toString() {
        return String.format("Code: %s\nState: %s\n%s", code, currentState, meal.toString());
    }

    public Money acquireRefundValue() {
        int diff;
        Money value = meal.dish().currentPrice();
        Money errantPenniesMoney[];
        Calendar actual = DateTime.now();
        Calendar booked = meal.date();
        if (!DateTime.isSameDate(actual, booked)) {
            return value;
        }
        if (meal.isLunch()) {
            diff = 12 - actual.get(Calendar.HOUR_OF_DAY);
            if (diff >= 10) {
                return value;
            }
        }
        if (meal.isDinner()) {
            diff = 19 - actual.get(Calendar.HOUR_OF_DAY);
            if (diff >= 16) {
                return value;
            }
        }
        errantPenniesMoney = value.divide(2);
        return errantPenniesMoney[1];
    }

}
