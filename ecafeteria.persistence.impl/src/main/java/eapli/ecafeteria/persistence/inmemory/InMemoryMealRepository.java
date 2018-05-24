package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.meals.Meal;

import eapli.ecafeteria.domain.meals.MealType;

import eapli.ecafeteria.domain.menu.Menu;

import eapli.ecafeteria.persistence.MealRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;
import java.util.ArrayList;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Norberto Sousa - 1120608
 */
public class InMemoryMealRepository extends InMemoryRepositoryWithLongPK<Meal> implements MealRepository {

    @Override
    public Iterable<Meal> findMealByMenu(Menu id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<Meal> findMealsByDateAndMealType(Calendar d, MealType type) {

        ArrayList<Meal> list_meals = new ArrayList<>();
        list_meals = (ArrayList<Meal>) match(m -> m.isOfMealType(type));

        ArrayList<Meal> ret = new ArrayList<>();

        for (Meal m : list_meals) {
            if (m.date().equals(d)) {
                ret.add(m);
            }
        }
        return ret;
    }

    @Override
    public Iterable<Meal> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<Meal> findOne(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<Meal> findMealByDate(Calendar d) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Meal> getMealsUsed(Lot lot) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
