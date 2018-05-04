/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.pos;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.ReservationRepository;
import eapli.framework.domain.POSStateViolationException;
import eapli.framework.domain.ReservationStateViolationException;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.ArrayList;
import javax.persistence.*;

/**
 *
 * @author hugod
 */
@Entity
public class POS implements AggregateRoot<Long>, Serializable {

    @Embeddable
    public static class POSState implements Serializable {

        private String state;

        public enum STATE {
            OPENED, CLOSED
        };

        private void open() {
            state = STATE.CLOSED.toString();
        }

        private void close() throws POSStateViolationException {
            if (state.equals(STATE.OPENED.toString())) {
                state = STATE.OPENED.toString();
            } else {
                throw new POSStateViolationException();
            }
        }

        public String state() {
            return state;
        }
    }

   
  
    
    @Id
    @GeneratedValue
    private Long posID;
    
    private int code;
    
    private POSState state;
    
    public POS(){
    }
    
    public POS(int code) throws POSStateViolationException {
        state.open();
        this.code = code;
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof POS)) {
            return false;
        }
        final POS p = (POS) other;

        if (this == p) {
            return true;
        }

        if (!this.posID.equals(p.posID)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean is(Long otherId) {
        return id().equals(otherId);
    }

    @Override
    public Long id() {
        return posID;
    }
    
    public POSState state(){
        return state;
    }
    
    /**
     *
     * @throws eapli.framework.domain.POSStateViolationException
     */
    public boolean close() throws POSStateViolationException {
        state.close();
        return true;
    }

}
