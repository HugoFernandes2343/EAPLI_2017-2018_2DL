/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.ratings.MealRating;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.persistence.MealRatingRepository;

import javax.persistence.Query;
import java.util.Optional;

/**
 *
 * @author Andre Rodrigues <1151136@isep.ipp.pt>
 */
public class JpaMealRatingRepository extends CafeteriaJpaRepositoryBase<MealRating, Long> implements MealRatingRepository{

    @Override
    public MealRating findRatingByReservation(Reservation res) {
        Query createQuery = entityManager().createQuery("SELECT r FROM MealRating r WHERE r.Meal_pk=:me_pk");
        createQuery.setParameter("me_pk", res.meal().pk());

        return (MealRating) createQuery.getResultList().get(0);
    }
}
