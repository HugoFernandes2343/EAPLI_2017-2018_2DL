    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.reservations;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.domain.ReservationStateViolationException;
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
    @Embeddable
    public static class ReservationState implements Serializable{
         /**
         * Different states for a determined Reservation
         */
        private String state;
        public enum STATE {DELIVERED,BOOKED,CANCELLED,EXPIRED};
        
        private void cancel() throws ReservationStateViolationException{
            if(state.equals(STATE.BOOKED.toString())){
                state=STATE.CANCELLED.toString();
            } else {
                throw new ReservationStateViolationException();
            }
        }
        
        private void book(){
            this.state=STATE.BOOKED.toString();
        }
        
        private void deliver() throws ReservationStateViolationException{
            if(state.equals(ReservationState.STATE.BOOKED.toString())){
                state=STATE.DELIVERED.toString();
            } else {
                throw new ReservationStateViolationException();
            }
        }
        
        private void expire() throws ReservationStateViolationException{
            if(state.equals(STATE.BOOKED.toString())){
                state=STATE.EXPIRED.toString();
            } else {
                throw new ReservationStateViolationException();
            }
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
        this.currentState = new ReservationState();
        currentState.book();
    }

    public String description() {
        return this.description;
    }

    /**
     * NEEDS TESTING
     * @return 
     * @throws eapli.framework.domain.ReservationStateViolationException 
     */
    public boolean deliver() throws ReservationStateViolationException {
        this.currentState.deliver();
        return true;
    }

    public boolean expire() throws ReservationStateViolationException{
        this.currentState.expire();
        return true;
    }
     
    public boolean cancel() throws ReservationStateViolationException{
        this.currentState.cancel();
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

    @Override
    public String toString(){
        return String.format("Code: %s\nDescription: %s\nState: %s\n", code,description,currentState.toString());
    }
}
