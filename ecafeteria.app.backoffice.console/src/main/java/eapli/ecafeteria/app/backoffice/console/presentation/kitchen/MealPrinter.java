/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author 1161569
 */
public class MealPrinter implements Visitor<Meal> {

    public MealPrinter() {
    }

    @Override
    public void visit(Meal visiter) {
        System.out.println(visiter.mealType().mealType() + " -"
                + " " + visiter.dish().name() + " - "
                + visiter.dish().currentPrice() + " - ");

    }

}
