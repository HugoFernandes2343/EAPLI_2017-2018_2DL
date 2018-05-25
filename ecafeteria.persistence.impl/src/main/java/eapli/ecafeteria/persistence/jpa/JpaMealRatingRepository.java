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

import javax.persistence.Query;
import java.util.Optional;

/**
 *
 * @author Andre Rodrigues <1151136@isep.ipp.pt>
 */
public class JpaMealRatingRepository extends CafeteriaJpaRepositoryBase<MealRating, Long> implements MealRatingRepository{

    @Override
    public Iterable<MealRating> findRatingsByMeal(Reservation re) {
        Query q = entityManager().createQuery("SELECT r FROM MealRating r WHERE r.reservation=:reservation");
        q.setParameter("reservation", re);
        return q.getResultList();
    }
    

}
