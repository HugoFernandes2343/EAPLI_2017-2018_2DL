/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.meal.PublishMealController;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.MealType.MealTypes;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;

/**
 *
 * @author David Santiago
 */
public class MealsBootstraper implements Action {
    
    private void register(MealType mealType, Dish dish, String date) throws DataConcurrencyException, DataIntegrityViolationException {
        final PublishMealController mealController = new PublishMealController();
        
        Meal m = mealController.buildMeal(mealType, dish, date);

           
        mealController.saveMeal(m);
                   
    }

    @Override
    public boolean execute() {
        
        
        
        final DishRepository Dishrep = PersistenceContext.repositories().dishes();
        final ArrayList<Dish> dishes = (ArrayList<Dish>) Dishrep.findAll();

        
        return false;
    }

    
    
}
