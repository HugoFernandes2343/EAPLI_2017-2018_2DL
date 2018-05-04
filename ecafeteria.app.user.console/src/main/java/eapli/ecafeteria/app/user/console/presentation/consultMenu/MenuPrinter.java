/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package eapli.ecafeteria.app.user.console.presentation.consultMenu;


import eapli.ecafetaria.domain.meals.Meal;
import eapli.ecafetaria.domain.menu.Menu;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Joel Costa <1130270@isep.ipp.pt>
 */
public class MenuPrinter implements Visitor<Menu>{

    @Override
    public void visit(Menu visitee) {
        System.out.printf("Menu title: %s \n", visitee.getTitle());
        for (Meal meal : visitee.getMeals()) {
            System.out.println("Meal:\n");
            System.out.println("\nDish Name: " + meal.dishName().toString());
            System.out.println("\nMeal Type" + meal.mealType());
            System.out.println("\nDate " + meal.date().getCalendarType());
        }
    }

    @Override
    public void beforeVisiting(Menu visitee) {
        // nothing to do
    }

    @Override
    public void afterVisiting(Menu visitee) {
        // nothing to do
    }

}
