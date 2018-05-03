/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.persistence.ReservationRepository;
import java.util.*;
import java.util.Optional;
import javax.persistence.*;

/**
 *
 * @author Hugo
 */
public class JpaReservationRepository extends CafeteriaJpaRepositoryBase<Reservation, Long> implements ReservationRepository {

    @Override
    public Optional<Reservation> findByCode(String code) {
        return matchOne("e.code=:code", "code", code);
    }

    @Override
    public Iterable<Reservation> findByStateAndMeal(Reservation.ReservationState.STATE state, Meal m) {
        Query createQuery = entityManager().createQuery("SELECT r FROM Reservation r WHERE r.meal=:meal r.state=:state");
        createQuery.setParameter("meal", m);
        createQuery.setParameter("state", state);
        return createQuery.getResultList();
    }

    
    @Override
    public Iterable<Reservation> selectTypeBooked(){
        Query createQuery = entityManager().createQuery("SELECT r FROM Reservation r WHERE state=:state");
        createQuery.setParameter(("state"), Reservation.ReservationState.STATE.BOOKED.toString());
        return createQuery.getResultList();
    }
    
    @Override
    public Iterable<Reservation> selectTypeDelivered() {
        Query createQuery = entityManager().createQuery("SELECT r FROM Reservation r WHERE state=:state");
        createQuery.setParameter(("state"), Reservation.ReservationState.STATE.DELIVERED.toString());
        return createQuery.getResultList();
    }

    @Override
    public Iterable<Reservation> selectTypeCancelled() {
        Query createQuery = entityManager().createQuery("SELECT r FROM Reservation r WHERE state=:state");
        createQuery.setParameter(("state"), Reservation.ReservationState.STATE.CANCELLED.toString());
        return createQuery.getResultList();
    }

    @Override
    public Iterable<Reservation> selectTypeExpired() {
        Query createQuery = entityManager().createQuery("SELECT r FROM Reservation r WHERE state=:state");
        createQuery.setParameter(("state"), Reservation.ReservationState.STATE.EXPIRED.toString());
        return createQuery.getResultList();
    }

}
