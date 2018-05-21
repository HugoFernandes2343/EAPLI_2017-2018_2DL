/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.domain.reservations.ReservationState;
import eapli.ecafeteria.persistence.ReservationRepository;

import java.util.Optional;
import javax.persistence.*;

/**
 * @author  <1160777@isep.ipp.pt && 1161569@isep.ipp.pt>Marco && Hugo Carvalho</1160777@isep.ipp.pt && 1161569@isep.ipp.pt>
 */
public class JpaReservationRepository extends CafeteriaJpaRepositoryBase<Reservation, Long> implements ReservationRepository {

    @Override
    public Optional<Reservation> findByCode(String code) {
        return matchOne("e.code=:code", "code", code);
    }

    @Override
    public Iterable<Reservation> findByStateAndMeal(ReservationState state, Meal m) {
        Query createQuery = entityManager().createQuery("SELECT r FROM Reservation r WHERE r.meal=:me_pk AND r.currentState=:st");
        createQuery.setParameter("me_pk", m);
        createQuery.setParameter("st", state);
        return createQuery.getResultList();
    }


    @Override
    public Iterable<Reservation> selectTypeBooked(CafeteriaUser user) {
        Query createQuery = entityManager().createQuery("SELECT r FROM Reservation r WHERE r.currentState=:state AND r.user=:u");
        createQuery.setParameter(("state"), ReservationState.BOOKED);
        createQuery.setParameter(("u"), user);
        return createQuery.getResultList();
    }

    @Override
    public Iterable<Reservation> selectTypeDelivered(CafeteriaUser user) {
        Query createQuery = entityManager().createQuery("SELECT r FROM Reservation r WHERE r.currentState=:state AND r.user=:u");
        createQuery.setParameter(("state"), ReservationState.DELIVERED);
        createQuery.setParameter(("u"), user);
        return createQuery.getResultList();
    }

    @Override
    public Iterable<Reservation> selectTypeCancelled(CafeteriaUser user) {
        Query createQuery = entityManager().createQuery("SELECT r FROM Reservation r WHERE r.currentState=:state AND r.user=:u");
        createQuery.setParameter(("state"), ReservationState.CANCELLED);
        createQuery.setParameter(("u"), user);
        return createQuery.getResultList();
    }

    @Override
    public Iterable<Reservation> selectTypeExpired(CafeteriaUser user) {
        Query createQuery = entityManager().createQuery("SELECT r FROM Reservation r WHERE r.currentState=:state AND r.user=:u");
        createQuery.setParameter(("state"), ReservationState.EXPIRED);
        createQuery.setParameter(("u"), user);
        return createQuery.getResultList();
    }


    /**
     * Searches for the next Reservation in the database
     * Selects the booked reservation in which the meal has the lowest date
     *
     * @param user user reference to the user who needs to see it's next reservation
     * @return the next reservation
     */
    @Override
    public Iterable<Reservation> findNextReservation(CafeteriaUser user) {
        Query createQuery = entityManager().createQuery("SELECT r FROM Reservation WHERE r.currentState=:state AND r.user=:u AND r.meal_pk = " +
                "(select m.meal_pk FROM Meal where m.date = " +
                "(SELECT MIN(m2.date) FROM Meal m2))");
        createQuery.setParameter(("u"), user);
        return createQuery.getResultList();
    }

}
