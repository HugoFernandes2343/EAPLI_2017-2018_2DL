/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.meal;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.sql.Date;
import java.util.Calendar;
import java.util.Optional;

/**
 *
 * @author David
 */
public class PublishMealController implements Controller {
    
    private final DishRepository dishRepository = PersistenceContext.repositories().dishes();
    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();

    public PublishMealController() {
    }

    public Meal buildMeal(Dish dish, MealType mealType, Calendar date, Menu menu) {
        Meal meal = new Meal(dish, mealType, date, menu);
        return meal;
    }

    public Iterable<Dish> allActiveDishes() {
        return dishRepository.findAll();
    }

    public Menu requestMenu(long menuID) {
        return menuRepository.findOne(menuID).get();
    }

    public void save(Menu menu) throws DataConcurrencyException, DataIntegrityViolationException {
        menuRepository.save(menu);
    }


    
}
