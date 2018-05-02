/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.meal.PublishMealController;
import eapli.ecafeteria.application.menu.PublishMenuController;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David
 */
public class MenuBootstraper implements Action {

    private void register(String startingDate,String endingDate,List<Meal> meals) throws DataConcurrencyException, DataIntegrityViolationException {
        
        
        final PublishMenuController menuController = new PublishMenuController();
        
        Menu m = menuController.buildMenu(startingDate, endingDate, meals);

           
        menuController.publish(m);
                   
    }
    
    
    
    
    
    @Override
    public boolean execute() {
        
        final MealRepository mealrep = PersistenceContext.repositories().meals();
        final ArrayList<Meal> meals = (ArrayList<Meal>) mealrep.findAll();
        
        return false;
    }
    
    
    
}
