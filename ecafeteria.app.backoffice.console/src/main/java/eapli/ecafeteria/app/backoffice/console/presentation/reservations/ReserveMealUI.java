/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.reservations;

import eapli.ecafeteria.app.backoffice.console.presentation.dishes.DishPrinter;
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

        final String d = Console.readLine("Choose a day for the reservation(dd/mm/yyyy)");
        Calendar date = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
        try {
            date.setTime(sdf.parse(d));
        } catch (ParseException ex) {
            Logger.getLogger(ReserveMealUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        final String mealTy = Console.readLine("Is it LUNCH or DINNER?");

        eapli.ecafeteria.domain.menu.Menu menu = theController.getMenu(date);

        MealTypes mealTypes = MealType.MealTypes.valueOf(mealTy);

        final String sDish = Console.readLine(menu.mealList() + "\nWhat dish you want?");
        Dish dish = null;
        for (Dish di : menu.mealList()) {
            if (di.name().toString().equals(sDish)) {
                dish = di;
            }
        }
        Meal m = new Meal(dish, new MealType(mealTypes), date);
        final String confirm = Console.readLine(m.toString() + "\nDo you confirm the information?");
        if (Boolean.valueOf(confirm)) {
            theController.reserveMeal(dish, new MealType(mealTypes), date);

        }
        return true;
         
    }

    @Override
    public String headline() {
        return "Reserve Meal";
    }

}
