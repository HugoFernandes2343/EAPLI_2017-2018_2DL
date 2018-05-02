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
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.persistence.MovementRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.ReservationRepository;
import eapli.framework.application.Controller;
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
    
    
    public Menu getMenu(Date date){
        return listMenuService.listMenuBooking(date);
    }
    
    public boolean reserveMeal(Dish dish, MealType mealType, Date date){
        boolean state = false;
        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(AuthorizationService.session().authenticatedUser().id());
        if(dish.currentPrice().amount() <= (listMovementService.calculateBalance(user.get()).amount())){
            Meal meal = new Meal(dish, mealType, date);
            String code = Calendar.DAY_OF_MONTH+"/"+Calendar.MONTH+"/"+Calendar.YEAR
                    +"//"+meal.mealNumber();
            Reservation reservation = new Reservation(code, meal.dish().nutricionalInfo().toString(), meal);
            movementRepo.addBookingMovement(dish.currentPrice());
            reservationRepo.addReservation(reservation);
            state = true;
        }
        return state;
    }
    
    
}
