/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.booking;

import eapli.ecafeteria.application.reservations.ReserveMealController;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import eapli.framework.util.DateTime;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author João Vieira
 */
public class ReserveMealUI extends AbstractUI {

    private final ReserveMealController theController = new ReserveMealController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        Date date;
        Menu menu;

        do {
            date = Console.readDate("Choose a day for the reservation(yyyy/mm/dd)");
        } while (!validate(date));

        try {
            menu = theController.getMenu(date);
        } catch (NullPointerException ex){
            System.out.println("There are no menus available for the given date!");
                return true;
        }

            Meal meal;
            SelectWidget<Meal> selector = new SelectWidget<>("Meals:", menu.listMeals(), new MealPrinter());
            selector.show();
            meal = selector.selectedElement();
            if (meal == null) {
                return true;
            }

            final String confirm = Console.readLine(meal.toString() + "\nDo you confirm the information?(Type 1 for yes, 0 for no)");
            if (confirm.equals("1")) {
                theController.reserveMeal(meal.dish(), meal.mealType(), date, menu);
                Console.readLine(meal.toString() + "\nSuccess!");
            }
            return true;

        }

        @Override
        public String headline
        
            () {
        return "Reserve Meal";
        }

    

    private boolean validate(Date date) {
        Calendar cal = DateTime.dateToCalendar(date);
        if (DateTime.isBeforeToday(cal)) {
            System.out.println("Insert Another Date!");
            return false;
        }
        return true;
    }

}