    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.reservations;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.ddd.AggregateRoot;
import eapli.framework.util.Strings;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Class Reservation
 *
 * @author Marco
 */
@Entity
public class Reservation implements AggregateRoot<String>, Serializable {
    
    /**
     * Inner class to allow the use of a enum
     */
    public static class ReservationState implements Serializable{
         /**
         * Different states for a determined Reservation
         */
        public enum STATE {DELIVERED,BOOKED,CANCELLED,EXPIRED};
        
        public STATE changeToDelivered(){
            return STATE.DELIVERED;
        }
        
        public STATE changeToExpired(){
            return STATE.EXPIRED;
        }
    }
    
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;
    
    // business ID
    @Column(unique = true)
    private String code;
    
    private String description;

    @ManyToOne
    private Meal meal;
    
    private ReservationState currentState;

    protected Reservation() {
        //ORM
    }

    /**
     * Reservation Class
     * 
     * @param code
     * @param description
     * @param meal 
     */
    public Reservation(String code, String description, Meal meal) {
        if (Strings.isNullOrEmpty(code)) {
            throw new IllegalArgumentException();
        }
        this.code = code;
        this.description = description;
        this.meal = meal;
    }

    public String description() {
        return this.description;
    }

    /**
     * NEEDS TESTING
     * @return 
     */
    public boolean deliver() {
        this.currentState.changeToDelivered();
        return true;
    }

    public boolean expire(){
        this.currentState.changeToExpired();
        return true;
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

    @Override
    public int hashCode() {
        return this.code.hashCode();
    }

}
