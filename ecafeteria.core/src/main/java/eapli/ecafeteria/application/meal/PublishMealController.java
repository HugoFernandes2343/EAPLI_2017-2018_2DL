/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meal;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.sql.Date;
import java.util.Calendar;

/**
 *
 * @author David
 */
public class PublishMealController implements Controller {
    
    private final DishRepository dishRepository = PersistenceContext.repositories().dishes();
    private final MealRepository mealRepository = PersistenceContext.repositories().meals();

    public PublishMealController() {
    }

    public Meal buildMeal(MealType mealType, Dish dish, String dateString) {
        Date date = Date.valueOf(dateString);
        
        if (date == null) {
            return null;
        }
        
        Meal meal = new Meal(dish, mealType, date);
        
        
        return meal;
        
    }
    
    public void saveMeal(Meal meal) throws DataConcurrencyException, DataIntegrityViolationException{
        mealRepository.save(meal);
    }


    public Iterable<Dish> allActiveDishes() {
        return dishRepository.findAll();
    }


    
}
