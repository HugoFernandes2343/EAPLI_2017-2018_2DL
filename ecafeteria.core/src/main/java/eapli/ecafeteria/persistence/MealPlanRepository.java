/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.kitchen.MealPlanItem;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.List;

/**
 *
 * @author Paulo Jorge
 */
public interface MealPlanRepository extends DataRepository<MealPlan, Integer> {

    public Iterable<MealPlan> findAllMealPlanInProgress();
            
    public List<MealPlanItem> getMealPlanItemsFromMealPlan(MealPlan mealPlan);
    
    public MealPlan findByIDMealPlan(Designation name);
}