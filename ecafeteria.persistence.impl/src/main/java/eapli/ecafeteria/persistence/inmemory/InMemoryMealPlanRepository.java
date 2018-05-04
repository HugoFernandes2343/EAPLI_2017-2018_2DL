/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.kitchen.MealPlanItem;

import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.List;

/**
 *
 * @author Paulo Jorge
 */
public class InMemoryMealPlanRepository extends InMemoryRepository<MealPlan, Integer> implements MealPlanRepository {

    @Override
    protected Integer newKeyFor(MealPlan entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<MealPlan> findAllMealPlanInProgress() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public List<MealPlanItem> getMealPlanItemsFromMealPlan(MealPlan mealPlan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Override
    public MealPlan findByIDMealPlan(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}