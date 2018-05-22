/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.domain.reservations.ReservationState;
import eapli.ecafeteria.persistence.ReservationRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

import java.util.Calendar;
import java.util.Optional;

import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

import java.util.*;

/**
 * @author Utilizador
 */
public class InMemoryReservationRepository extends InMemoryRepositoryWithLongPK<Reservation> implements ReservationRepository {

    @Override
    public Optional<Reservation> findByCode(String code) {
        return matchOne(r -> r.id().equals(code));
    }

    @Override
    public Iterable<Reservation> findAll() {
        return match(r -> r.isCreated());
    }

    @Override
    public Optional<Reservation> findOne(Long id) {
        throw new UnsupportedOperationException("Deprecated method! Do not use!");
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<Reservation> findByStateAndMeal(ReservationState state, Meal m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }

    @Override
    public Iterable<Reservation> checkExistingReservations(Calendar date, DishType dishType, Dish dish, MealType mealType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public Iterable<Reservation> selectTypeBooked(CafeteriaUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<Reservation> selectTypeDelivered(CafeteriaUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<Reservation> selectTypeCancelled(CafeteriaUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<Reservation> selectTypeExpired(CafeteriaUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<Reservation> findNextReservation(CafeteriaUser user) {


        ArrayList<Reservation> nextRes = new ArrayList<>(1);
        Iterable<Reservation> allReserv = match(r -> r.isCreated());
        Calendar lowest = allReserv.iterator().next().meal().date();/*Creates a temp lowest date*/

        for(Reservation res : allReserv) {
            if(res.user().equals(user)){
                if(res.meal().date().before(lowest) && res.state() == ReservationState.BOOKED){
                    lowest = res.meal().date();
                    nextRes.add(res);
                }
            }
        }
        return nextRes;
    }

    @Override
    public Iterable<Reservation> findByStateAndDate(ReservationState state, Calendar date, MealType mt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
