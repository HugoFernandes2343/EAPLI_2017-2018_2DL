/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.booking;

import eapli.ecafeteria.application.reservations.ReserveMealController;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.MealType.MealTypes;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

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

        final Date date = Console.readDate("Choose a day for the reservation(dd/mm/yyyy)");

        eapli.ecafeteria.domain.menu.Menu menu = theController.getMenu(date);

        Meal meal;
        SelectWidget<Meal> selector = new SelectWidget<>("Meals:", menu.listMeals(), new MealPrinter());
        selector.show();
        meal = selector.selectedElement();
        if (meal == null) {
            return true;
        }

        final String confirm = Console.readLine(meal.toString() + "\nDo you confirm the information?(Type 1 for yes, 0 for no)");
        if (Boolean.valueOf(confirm)) {
            theController.reserveMeal(meal.dish(), meal.mealType(), date);
        }
        return true;

    }

    @Override
    public String headline() {
        return "Reserve Meal";
    }

}
