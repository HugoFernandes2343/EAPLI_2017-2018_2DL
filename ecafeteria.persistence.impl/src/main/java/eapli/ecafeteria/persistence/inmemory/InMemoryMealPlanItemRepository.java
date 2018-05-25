/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.kitchen.MealPlanItem;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealPlanItemRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author hugod
 */
public class InMemoryMealPlanItemRepository extends InMemoryRepositoryWithLongPK<MealPlanItem> implements MealPlanItemRepository{

    @Override
    public Iterable<MealPlanItem> getMealPlanItemList(MealPlan mp) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MealPlanItem findByIDMealPlanItem(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateTable(Long id, int quantidades) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MealPlanItem findByMeal(Meal m) {
        
        Optional<MealPlanItem> mpitem = matchOne(mpi -> mpi.checkMeal(m));

        if (mpitem.isPresent()) {
            MealPlanItem ret = mpitem.get();
            return ret;
        } else {
            return null;
        }
     //   ArrayList<MealPlanItem> ret = new ArrayList<>(1);
       // ret = (ArrayList<MealPlanItem>) match(mpi -> mpi.checkMeal(m)); 
        //return ret;
    }

    @Override
    public Iterable<MealPlanItem> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<MealPlanItem> findOne(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<MealPlanItem> findMealsFromMealPlanItem(Meal m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
