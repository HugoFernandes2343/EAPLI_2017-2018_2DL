/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menu;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author David
 */
public class RegisterMenuController {
    private Menu menu;
    
    
    private final MealRepository MealRepository = PersistenceContext.repositories().meals();
    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();
    
    
    
    
    public Menu elaborateMenu(Calendar startDate, Calendar finishDate) {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        menu = new Menu(startDate, finishDate);

        if (startDate.before(finishDate)) {
            return null;
        }

        return menu;
    }
    
    
    public boolean addMeal(Meal Meal) {
        return menu.addMeal(Meal);
    }

    public boolean removeMeal(Meal Meal) {
        return menu.removeMeal(Meal);
    }
    
    public List<Meal> menuMealList(){
        return menu.mealList();
    }
    
    public Menu saveMenu() throws DataIntegrityViolationException, DataConcurrencyException {
        return menuRepository.save(menu);
    }
}