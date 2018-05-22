/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.domain.reservations.ReservationState;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;

import java.util.Optional;

/**
 *
 * @author Utilizador
 */
public interface ReservationRepository extends DataRepository<Reservation,Long>{
    
    
    /**
     * Find Reservation given a code
     * @param code
     * @return 
     */
    Optional<Reservation> findByCode(String code);
    
    /**
     * Finds reservation given a meal and a state
     * @param state
     * @param m
     * @return
     */
    Iterable<Reservation> findByStateAndMeal(ReservationState state,Meal m);

    /**
     * Finds all Reservations pertaining to a specific "date" for given "dishType", "dish" and "mealType"
     * @param date
     * @param dishType
     * @param dish
     * @param mealType
     * @return 
     */
    Iterable<Reservation> checkExistingReservations (Calendar date, DishType dishType, Dish dish, MealType mealType);
    
    Iterable<Reservation> selectTypeBooked(CafeteriaUser user);
    Iterable<Reservation> selectTypeDelivered(CafeteriaUser user);
    Iterable<Reservation> selectTypeCancelled(CafeteriaUser user);
    Iterable<Reservation> selectTypeExpired(CafeteriaUser user);

    /**
     * FindNextReservation searches for the next reservation for a user based on the state and state
     * @param user user
     * @return
     */
    Iterable<Reservation> findNextReservation(CafeteriaUser user);
    
}
