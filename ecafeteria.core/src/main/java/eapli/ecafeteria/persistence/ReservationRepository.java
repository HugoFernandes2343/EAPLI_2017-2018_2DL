/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.framework.persistence.repositories.DataRepository;

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
    Iterable<Reservation> findByStateAndMeal(Reservation.ReservationState.STATE state,Meal m);

    public boolean addReservation(Reservation reservation);

    
}