/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.MealPlanItem;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealPlanItemRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Norberto Sousa - 1120608
 */
public class RegisterMealPlanItemController implements Controller {
    
    private final MealPlanItemRepository mpiRP = PersistenceContext.repositories().mealPlanItemRepository();
    
    
    public void registerMealPlanItem(Meal mp, int quant) throws DataConcurrencyException, DataIntegrityViolationException{
        MealPlanItem mpi = new MealPlanItem(mp, quant);
        mpiRP.save(mpi);
    }
    
    
}
