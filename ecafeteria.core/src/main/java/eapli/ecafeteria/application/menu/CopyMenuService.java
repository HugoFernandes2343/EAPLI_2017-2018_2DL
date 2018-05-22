/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menu;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.meal.PublishMealController;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author David Santiago <1161109@isep.ipp.pt>
 */
public class CopyMenuService {

    RegisterMenuController rmc = new RegisterMenuController();
    PublishMealController pmc = new PublishMealController();
    MealRepository jmr = PersistenceContext.repositories().meals();

    public void copyMenu(Menu m, Calendar startDate, Designation newName) throws DataConcurrencyException, DataIntegrityViolationException {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        
        Calendar mStartDate = m.startDate(); //Starting date of the original menu

        Calendar endingDate = (Calendar) startDate.clone();
        endingDate.add(Calendar.DAY_OF_MONTH, 6);

        rmc.MenuMaker(startDate, endingDate, newName);
        Menu menu = rmc.saveMenu();
       
        Iterable<Meal> itmeal= jmr.findMealByMenu(m);
        
        addMeals(itmeal,dateDistance(mStartDate,startDate),menu);
        
        
    }

    private int dateDistance(Calendar menuStartingDate, Calendar newMenuStartingDate) {

        long distanceL = ChronoUnit.DAYS.between(menuStartingDate.toInstant(), newMenuStartingDate.toInstant());

        if (distanceL > Integer.MAX_VALUE) {
            throw new IllegalArgumentException("A Distância entre as datas é superior à suportada");
        }

        int distance = (int) distanceL;

        return distance;

    }

    private void addDays(Calendar date, int days) {

        date.add(Calendar.DAY_OF_MONTH, days);

    }

    private void addMeals(Iterable<Meal> meals, int daysDif,Menu menu) throws DataConcurrencyException, DataIntegrityViolationException {
        
        System.out.println("entrou 1");
        for (Meal e : meals) {
            
            System.out.println("entrou 2");
            
            Calendar date = e.date();
            Dish dish = e.dish();
            MealType mtp = e.mealType();
            
            Calendar newDate = (Calendar) date.clone();
            addDays(newDate,daysDif);
            System.out.println("vai gravar meal");
            Meal newMeal =pmc.buildMeal(dish, mtp, newDate, menu);
            pmc.save(newMeal);
                 
        }

    }

}
