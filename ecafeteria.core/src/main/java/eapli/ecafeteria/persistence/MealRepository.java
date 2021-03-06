/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author David
 */
public interface MealRepository extends DataRepository<Meal, Long> {


    Iterable<Meal> findMealsByDateAndMealType(Calendar d, MealType type);

    Iterable<Meal> findMealByMenu(Menu menu);
    
//    Iterable<Meal> findRatingsByMeal(Meal meal);

    Iterable<Meal> findMealByDate(Calendar d);

    public List<Meal> getMealsUsed(Lot lot);

}
