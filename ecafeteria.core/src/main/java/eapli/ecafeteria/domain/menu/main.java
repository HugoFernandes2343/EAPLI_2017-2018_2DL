/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.menu;

import eapli.ecafeteria.application.dishes.ListDishController;
import eapli.ecafeteria.application.dishes.ListDishService;
import eapli.ecafeteria.application.meal.ListMealService;
import eapli.ecafeteria.application.meal.PublishMealController;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.meals.MealType.MealTypes;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.domain.Designation;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.Currency;

/**
 *
 * @author David
 */
public class main {

    
    public static void main(String[] args) throws DataConcurrencyException, DataIntegrityViolationException {
        
           ListDishService ls = new ListDishService();
           
           System.out.println(ls.allDishes().iterator().next().name().toString());
        
         

    }
}
