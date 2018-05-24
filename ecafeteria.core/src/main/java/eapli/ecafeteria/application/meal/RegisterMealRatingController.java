/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meal;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.ratings.MealRating;
import eapli.ecafeteria.domain.ratings.Score;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.domain.reservations.ReservationState;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.MealRatingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.ReservationRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Andre Rodrigues <1151136@isep.ipp.pt>
 */
public class RegisterMealRatingController implements Controller {

    private final MealRatingRepository mealRatingRepo = PersistenceContext.repositories().ratings();
    private final CafeteriaUserService userService = new CafeteriaUserService();
    private final ReservationRepository reservationRepo = PersistenceContext.repositories().reservations();

    private Reservation r;

    Optional<CafeteriaUser> u = userService.findCafeteriaUserByUsername(AuthorizationService.session().authenticatedUser().id());

    public List<Reservation> lstReservations(CafeteriaUser user) {
        return (List<Reservation>) reservationRepo.selectTypeDelivered(user);
    }

    public Reservation selectReservation(Long id) {
        r = reservationRepo.findByID(id);
        return r;
    }

    public MealRating registerMealRating(Meal meal, int rating) {
        return null;
    }

    private MealRating registerMealRatingWithoutComment(Reservation reservation, int rating)
            throws DataConcurrencyException, DataIntegrityViolationException {
        MealRating mr = new MealRating(reservation, rating);
        return mealRatingRepo.save(mr);
    }

    public boolean checkReservation(Date mealDate, Meal meal) {
        Reservation r = reservationRepo.checkIfReservationExists(u.get(), ReservationState.DELIVERED, meal, mealDate);
        if (r == null) {
            return false;
        } else {
            return true;
        }
    }
}
