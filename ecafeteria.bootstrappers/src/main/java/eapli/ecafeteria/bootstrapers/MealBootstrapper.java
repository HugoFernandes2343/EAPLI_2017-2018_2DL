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
import eapli.framework.util.DateTime;
import java.util.Calendar;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Vieira
 */
public class MealBootstrapper implements Action {

    @Override
    public boolean execute() {
        final DishRepository dishRepo = PersistenceContext.repositories().dishes();
        final MenuRepository menuRepo = PersistenceContext.repositories().menus();

        Iterable<Dish> dishes = dishRepo.findAll();
        Iterable<Menu> menus = menuRepo.findAll();

        Iterator<Dish> dishesI = dishes.iterator();
        Iterator<Menu> menusI = menus.iterator();

        while (dishesI.hasNext() && menusI.hasNext()) {
            
            Dish d = dishesI.next();
            Menu m = menusI.next();
            
            try {
                register(d, new MealType(MealType.MealTypes.LUNCH), DateTime.newCalendar(2018, 05, 4), m);
            } catch (DataConcurrencyException ex) {
                Logger.getLogger(MealBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DataIntegrityViolationException ex) {
                Logger.getLogger(MealBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        return true;
    }

    /**
     *
     */
    private void register(Dish dish, MealType mealType, Calendar date, Menu menu) throws DataConcurrencyException, DataIntegrityViolationException {
        final PublishMealController controller = new PublishMealController();
        controller.buildMeal(dish, mealType, date, menu);
    }

}
