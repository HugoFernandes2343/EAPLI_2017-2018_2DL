/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.LotRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;

/**
 *
 * @author 1161569
 */
public class RegisterUsedLotController implements Controller {
    
    private final LotRepository lotRepo = PersistenceContext.repositories().lots();
    private final MealRepository mealRepo = PersistenceContext.repositories().meals();
    
    
    public Iterable<Meal> findMeals(Calendar date){
        return mealRepo.findMealByDate(date);
    }
    
    public Iterable<Lot> findLots(){
        return lotRepo.findAll();
    }
    
    public void registerLot(Meal meal, Lot lot) throws IllegalArgumentException, DataConcurrencyException, DataIntegrityViolationException{
        meal.registerLot(lot);
        mealRepo.save(meal);
    }
}
