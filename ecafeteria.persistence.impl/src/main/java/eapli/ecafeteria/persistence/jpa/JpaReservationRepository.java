/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.persistence.ReservationRepository;
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
    public boolean addReservation(Reservation reservation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
