/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.ratings.MealRating;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.persistence.MealRatingRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 *
 * @author Andre Rodrigues <1151136@isep.ipp.pt>
 */
public class InMemoryMealRatingRepository extends InMemoryRepository<MealRating, Long> implements MealRatingRepository{

    @Override
    protected Long newKeyFor(MealRating entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public MealRating findRatingByReservation(Reservation res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
