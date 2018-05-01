/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealPlanRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.Spliterator;
import java.util.function.Consumer;

/**
 *
 * @author Paulo Jorge
 */
public class InMemoryMealPlanRepository extends InMemoryRepository<Meal,Integer> implements MealPlanRepository{

    @Override
    protected Integer newKeyFor(Meal entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void forEach(Consumer<? super Meal> cnsmr) {
        super.forEach(cnsmr); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Spliterator<Meal> spliterator() {
        return super.spliterator(); //To change body of generated methods, choose Tools | Templates.
    }
    
}
