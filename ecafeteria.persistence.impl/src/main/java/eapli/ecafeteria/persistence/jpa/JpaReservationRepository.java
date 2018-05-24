/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteriashift.CafeteriaShiftDayTimeState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.domain.reservations.ReservationState;
import eapli.ecafeteria.persistence.ReservationRepository;
import java.util.Calendar;
import java.util.Date;

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
    public Iterable<Reservation> findReservationsBy(Calendar date, Dish dish) {
        Query createQuery = entityManager().createQuery("SELECT r FROM Reservation r WHERE r.meal IN (SELECT m FROM Meal m WHERE m.date=:dt AND m.dish=:dish)");
        createQuery.setParameter("dt", date);
        createQuery.setParameter("dish", dish);
        return createQuery.getResultList();
    }
    
    @Override
    public Iterable<Reservation> findByStateAndDate(ReservationState state, Calendar date, MealType mt) {
        Query createQuery = entityManager().createQuery("SELECT r FROM Reservation r WHERE r.currentState=:st AND r.meal IN (SELECT m FROM Meal m WHERE m.date=:dt AND m.mealType=:mt)");
        createQuery.setParameter("mt", mt);
        createQuery.setParameter("dt", date);
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
                "(SELECT m.meal_pk FROM Meal where m.date = " +
                "(SELECT MIN(m2.date) FROM Meal m2))");
        createQuery.setParameter(("u"), user);
        return createQuery.getResultList();
    }
    
    @Override
    public Reservation checkIfReservationExists(CafeteriaUser user, ReservationState state, Meal meal, Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        Reservation rv = null;
        
        Query createQuery = entityManager().createQuery(("SELECT DISTINCT (r) FROM Reservation r, Meal m " 
                            + "WHERE r.user=:u"
                            + "AND m.meal=:meal"
                            + "AND m.date=:mealDate"
                            + "AND r.currentState=:state"));
        
        createQuery.setParameter("u", user);
        createQuery.setParameter("meal", meal);
        createQuery.setParameter("mealDate", cal);
        createQuery.setParameter("state", state);
        
        try {
            rv = (Reservation)createQuery.getSingleResult();
        } catch (NoResultException ex) {
            System.out.println("No meal was consumed on the specified date");
        }
                
        return rv;
    }
    
    @Override
    public Reservation findByID(Long pk) {
        Optional<Reservation> r = matchOne("e.pk=:pk", "pk", pk);
        if (r.isPresent()) {
            Reservation rv = r.get();
            return rv;
        } else {
            return null;
        }
    }

    
   

}
