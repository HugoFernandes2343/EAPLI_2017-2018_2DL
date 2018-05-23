/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.menu;

import eapli.ecafeteria.application.meal.ConsultMealRatingController;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.util.List;

/**
 *
 * @author João Santiago <1160696@isep.ipp.pt>
 */
public class ConsultMealRatingUI extends AbstractUI { // POR ACABAR
    
    private ConsultMealRatingController cmrc = new ConsultMealRatingController();

    @Override
    protected boolean doShow() {
//        final Iterable<Meal> meals = cmrc.allMeals();
//        Iterable<MealRating> ratings;
        int ratingsCount = 0;
        String reply;
//        Comment com;
        System.out.println(headline() + "\n\n");
        List<Meal> meals = (List<Meal>) cmrc.allMeals();
        if (meals.isEmpty()) {
            System.out.println("No meals available at the moment.");
            return false;
        } else {
            SelectWidget<Meal> mealChooser = new SelectWidget<>("MEALS", meals);
            mealChooser.show();
            Meal choosenMeal = mealChooser.selectedElement();
            
            
            if(choosenMeal == null){
                return false;
            }
            
        } 
        return true;
    }


    @Override
    public String headline() {
        return "CONSULT MEAL RATING";
    }
    
}
