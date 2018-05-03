/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.reservations;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.application.cafeteriauser.ListMovementService;
import eapli.ecafeteria.application.menu.ListMenuService;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.domain.movement.Booking;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.persistence.MovementRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.ReservationRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.Console;
import eapli.framework.util.DateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

/**
 *
 * @author João Vieira
 */
public class ReserveMealController implements Controller {

    private final ReservationRepository reservationRepo = PersistenceContext.repositories().reservations();
    private final MovementRepository movementRepo = PersistenceContext.repositories().movements();
    private final ListMenuService listMenuService = new ListMenuService();
    private final ListMovementService listMovementService = new ListMovementService();
    private final CafeteriaUserService userService = new CafeteriaUserService();

    public Menu getMenu(Date date) {
        return listMenuService.listMenuBooking(date);
    }

    public boolean reserveMeal(Dish dish, MealType mealType, Date date, Menu menu) {
        boolean state = false;
        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(AuthorizationService.session().authenticatedUser().id());
        if (dish.currentPrice().amount() <= (listMovementService.calculateBalance(user.get()).amount())) {
            Meal meal = new Meal(dish, mealType, DateTime.dateToCalendar(date), menu);
            String code = DateTime.format(DateTime.dateToCalendar(date)) + "//" + meal.mealNumber();
            Reservation reservation = new Reservation(code, meal.dish().name().toString(), meal);
            Booking booking = new Booking(user.get(), dish.currentPrice().negate());
            try {
                addReservation(reservation);
                movementRepo.addBookingMovement(booking);
                state = true;
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                System.out.println("An transactional error has ocurred. Please check data and try again.");
            }
        } else {
            Console.readLine("\nSuccess!");
        }
        return state;
    }

    private Reservation addReservation(Reservation reservation) throws DataConcurrencyException, DataIntegrityViolationException {
        return reservationRepo.save(reservation);
    }

}
