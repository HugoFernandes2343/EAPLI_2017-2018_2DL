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

    //POSState Open or Closed
    @Enumerated(EnumType.STRING)
    public POSState state;
    @Id
    private Long posID;

    @OneToOne
    public POSShift shift;
    
    public POS() {
        this.state = POSState.CLOSED;
        this.posID = new Long(1);
        this.shift = null;
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
    
    /**
     * Closes the POS and changes all of the Reservations that are in a BOOKED state and changes them to an expired state
     */
    public void closeShift(){
        this.state = POSState.CLOSED;
       ReservationRepository reservationRepo = PersistenceContext.repositories().reservations();
       
       for(Meal m : shift.meals()){
           ArrayList<Reservation> reservations = (ArrayList<Reservation>) reservationRepo.findByStateAndMeal(Reservation.ReservationState.STATE.EXPIRED, m);
           for(Reservation r : reservations){
               r.expire();
           }
       }
        
    }
    
}
