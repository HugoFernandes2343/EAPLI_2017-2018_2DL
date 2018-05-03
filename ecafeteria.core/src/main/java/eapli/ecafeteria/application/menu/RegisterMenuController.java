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
import eapli.framework.application.Controller;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author David Santiago
 */
public class RegisterMenuController implements Controller{
    
    
    private Menu menu;
    
    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();
    private final MealRepository MealRepository = PersistenceContext.repositories().meals();
    
    
    
    
    
    public Menu MenuMaker(Calendar startDate, Calendar finishDate, Designation name) throws DataConcurrencyException, DataIntegrityViolationException {
        
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        
         
        menu = new Menu(startDate, finishDate, name);

        return menu;
        
    }
    
    
    public boolean addMeal(Meal meal) {
                
        return menu.addMeal(meal);
    }

    public boolean removeMeal(Meal Meal) {
        return menu.removeMeal(Meal);
    }
    
    public List<Meal> menuMealList(){
        return menu.listMeals();
    }
    
    public Menu saveMenu() throws DataIntegrityViolationException, DataConcurrencyException {
        return menuRepository.save(menu);
    }
}
