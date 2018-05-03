/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.Kitchen.MealPlan;

import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 *
 * @author Paulo Jorge
 */
public class InMemoryMealPlanRepository extends InMemoryRepository<MealPlan, Integer> implements MealPlanRepository {

    @Override
    protected Integer newKeyFor(MealPlan entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
