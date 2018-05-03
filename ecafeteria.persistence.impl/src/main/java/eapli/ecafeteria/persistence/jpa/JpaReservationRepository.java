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
        createQuery.setParameter("meal", m.pk());
        createQuery.setParameter("state", state);
        return createQuery.getResultList();
    }

    
    public Iterable<Reservation> selectTypeBooked(Date startDate, Date endDate, Reservation.ReservationState.STATE state){
        Query createQuery = entityManager().createQuery("SELECT r FROM Reservation r WHERE r.date BETWEEN (startDate, endDate) AND state=: state");
        createQuery.setParameter("startDate", startDate);
        createQuery.setParameter("endDate", endDate);
        createQuery.setParameter(("state"), state);
        return createQuery.getResultList();
    }
    
    public Iterable<Reservation> selectTypeDelivered(Date startDate, Date endDate, Reservation.ReservationState.STATE state) {
        Query createQuery = entityManager().createQuery("SELECT r FROM Reservation r WHERE r.date BETWEEN (startDate, endDate) AND state=: state");
        createQuery.setParameter("startDate", startDate);
        createQuery.setParameter("endDate", endDate);
        createQuery.setParameter(("state"), state);
        return createQuery.getResultList();
    }

    @Override
    public Iterable<Reservation> selectTypeCancelled(Date startDate, Date endDate, Reservation.ReservationState.STATE state) {
        Query createQuery = entityManager().createQuery("SELECT r FROM Reservation r WHERE r.date BETWEEN (startDate, endDate) AND state=: state");
        createQuery.setParameter("startDate", startDate);
        createQuery.setParameter("endDate", endDate);
        createQuery.setParameter(("state"), state);
        return createQuery.getResultList();
    }

    @Override
    public Iterable<Reservation> selectTypeExpired(Date startDate, Date endDate, Reservation.ReservationState.STATE state) {
        Query createQuery = entityManager().createQuery("SELECT r FROM Reservation r WHERE r.date BETWEEN (startDate, endDate) AND state=: state");
        createQuery.setParameter("startDate", startDate);
        createQuery.setParameter("endDate", endDate);
        createQuery.setParameter(("state"), state);
        return createQuery.getResultList();
    }

}
