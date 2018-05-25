/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meal;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.ratings.Comment;
import eapli.ecafeteria.domain.ratings.MealRating;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.persistence.MealRatingRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.ReservationRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;

/**
 *
 * @author João Santiago <1160696@isep.ipp.pt>
 */
public class ConsultMealRatingController implements Controller {

    private final MealRepository mr = PersistenceContext.repositories().meals();
    private final MealRatingRepository rr = PersistenceContext.repositories().ratings();
    private final ReservationRepository rer = PersistenceContext.repositories().reservations();

    public Iterable<Meal> allMeals() {
        return mr.findAll();
    }

//    public Iterable<Reservation> allMealsOfReservations() {
//
//        ArrayList<Meal> oi = new ArrayList<>();
//
//        ArrayList<Reservation> list = (ArrayList<Reservation>) rer.findAll();
//
//        for (Reservation r : list) {
//            
//            oi.add(r.meal());
//
//        }
//
//        return oi;
//    }
    
    public Iterable<Reservation> allMealsOfReservations() {
        return rer.findAll();
    }

    public Iterable<MealRating> allRatings(Reservation r) {
        return rr.findRatingsByMeal(r);

    }

    public void answerToComment(MealRating r, String reply) throws DataConcurrencyException, DataIntegrityViolationException {
        Comment c = r.comment(reply);
        c.answerReply(reply);
        rr.save(r);
    }
}
