/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.ReservationRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Rodrigo
 */
public class CheckExistingReservationsController implements Controller{
    //Only a class-variable because it needs to be shared by multiple operations during
    //the Controller's lifetime, otherwise it would stay within a method scope
    private Iterable<Meal> meals;
    
    public Iterable<DishType> findDishTypesByDateAndMealType (Calendar date, MealType mealType)
    throws DataIntegrityViolationException, DataConcurrencyException {
        
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        
        final MealRepository mealRepo = PersistenceContext.repositories().meals();
        
        meals = mealRepo.findMealsByDateAndMealType (date, mealType);
        
        Iterable<DishType> dishTypes = getAllDishTypes (meals);
        
        return dishTypes;        
    }
    
    public Iterable<Dish> findDishesByDishType (DishType dishType){
        return getAllDishes (meals, dishType);
    }
    
    public Iterable<Reservation> findReservationsBy (Calendar date, DishType dishType, Dish dish, MealType mealType){
        final ReservationRepository resRepo = PersistenceContext.repositories().reservations();
        
        Iterable<Reservation> reservations = resRepo.findReservationsBy(date, dish);
        
        return reservations;
    }
    
    
    
    
    /**-----------------IMPORTANT--------------------------------
     * The following auxiliary methods are performing operations that theoretically could
     * be part of a more complex query in the "findReservationsBy()" method in "ReservationRepository".
     * I am performing them here, in memory, under the assumption that when they are called,
     * the "meals" container will only have a few elements (i.e., an average cafeteria should only have
     * 10-20 Dishes tops, so traversing them is not a heavy-duty task).
     * Obviously if the above changes, then this will have to be reworked
    */
    private Iterable<DishType> getAllDishTypes (Iterable<Meal> meals){
        List<DishType> dishTypes = new ArrayList<DishType>();
        
        for (Meal meal : meals){
            dishTypes.add (meal.dish().dishType());
        }
        
        return dishTypes;
    }
    
    private Iterable<Dish> getAllDishes (Iterable<Meal> meals, DishType dishType){
        List<Dish> dishes = new ArrayList<Dish>();
        
        for (Meal meal : meals){
            Dish nextDish = meal.dish();
            DishType nextDishType = nextDish.dishType();
            if (nextDishType.equals(dishType)){
                dishes.add(nextDish);
            }
        }
        
        return dishes;
    }
}
