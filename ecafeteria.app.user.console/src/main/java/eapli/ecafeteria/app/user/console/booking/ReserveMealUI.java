/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.booking;

import eapli.ecafeteria.app.user.console.presentation.CafeteriaUserBaseUI;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.application.reservations.ReserveMealController;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.domain.movement.MovementBuilder;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import eapli.framework.util.DateTime;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author João Vieira
 */
public class ReserveMealUI extends CafeteriaUserBaseUI {

    private final ReserveMealController theController = new ReserveMealController();

    @Override
    protected boolean doShow() {
        Date date;
        Menu menu;

        do {
            date = Console.readDate("Choose a day for the reservation(yyyy/mm/dd)");
        } while (!validate(date));

        try {
            menu = theController.getMenu(date);
        } catch (NullPointerException ex) {
            System.out.println("There are no menus available for the given date!");
            return true;
        }

        Meal meal;
        SelectWidget<Meal> selector = new SelectWidget<>("Meals:", theController.menuMealsList(menu), new MealPrinter());
        selector.show();
        meal = selector.selectedElement();
        if (meal == null) {
            return true;
        }

        final String confirm = Console.readLine(meal.toString() + "\nDo you confirm the information?(Type 1 for yes, 0 for no)");
        if (confirm.equals("1")) {
            /*Observer and Observable use*/
            MovementBuilder temp = theController.getMVB();
            super.defineObservable(temp);
            super.defineObserver(temp);
            /*-----*/
            Reservation reservation = theController.reserveMeal(meal);
            if (reservation != null) {
                Console.readLine("\nSuccess! Your Reservation code is " + reservation.id());
            }
        }
        return true;

    }

    private boolean validate(Date date) {
        Calendar cal = DateTime.dateToCalendar(date);
        if (DateTime.isBeforeToday(cal)) {
            System.out.println("Insert Another Date!");
            return false;
        }
        return true;
    }

    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }

}
