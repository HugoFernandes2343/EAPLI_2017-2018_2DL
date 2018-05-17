/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.meal.PublishMealController;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.Iterator;

/**
 *
 * @author João Vieira
 */
public class MealBootstrapper implements Action {

    @Override
    public boolean execute() {
        final DishRepository dishRepo = PersistenceContext.repositories().dishes();
        final MenuRepository menuRepo = PersistenceContext.repositories().menus();

        Dish dish = dishRepo.findAll().iterator().next();
        Iterable<Menu> menus = menuRepo.findAll();

        Iterator<Menu> menusI = menus.iterator();

        while (menusI.hasNext()) {
            
            Menu m = menusI.next();
            
            try {
                register(dish, new MealType(MealType.MealTypes.LUNCH), m.finishDate(),m);
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                System.out.println("Error while adding the meals");
            } 
        }
        
        return true;
    }

    /**
     *
     */
    private void register(Dish dish, MealType mealType, Calendar date, Menu menu) throws DataConcurrencyException, DataIntegrityViolationException {
        final PublishMealController controller = new PublishMealController();
        final Meal meal=controller.buildMeal(dish, mealType, date, menu);
        controller.save(meal);
    }

}
