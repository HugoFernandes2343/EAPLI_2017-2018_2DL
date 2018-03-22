/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.reporting.DishesPerCaloricCategory;
import eapli.ecafeteria.domain.dishes.reporting.DishesPerDishType;
import eapli.ecafeteria.persistence.DishReportingRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.io.Serializable;

/**
 *
 * @author pgsou_000
 */
@SuppressWarnings("rawtypes")
class InMemoryDishReportingRepository extends InMemoryRepository
        implements DishReportingRepository {

    @Override
    public Iterable<DishesPerDishType> dishesPerDishType() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    /**
     * just to abide by the contract of InMemoryRepository but this is really
     * not needed as this repository is only for reporting
     *
     * @param entity
     * @return
     */
    @Override
    protected Serializable newKeyFor(Object entity) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Iterable<Dish> reportHighCaloriesDishes() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<DishesPerCaloricCategory> reportDishesPerCaloricCategory() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<Object[]> reportDishesPerCaloricCategoryAsTuples() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
