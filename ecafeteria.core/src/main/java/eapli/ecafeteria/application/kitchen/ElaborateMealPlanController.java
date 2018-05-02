/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.application.menu.ListMenuService;
import eapli.ecafeteria.domain.Kitchen.MealPlan;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.kitchen.MealPlanItem;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Paulo Jorge
 */
public class ElaborateMealPlanController implements Controller{
    
    private final MenuRepository repository = PersistenceContext.repositories().menus();
    
    private final ListMenuService listMenuService = new ListMenuService();
   
    
    public List<Menu> fetchAvailableMenus() 
    {
        return new ArrayList<>((Collection<? extends Menu>) listMenuService.allMenus());       
    }
    
    
    public List<Meal> getMealsfromMenu (Menu m) 
    {
        Iterable<Meal> meals = PersistenceContext.repositories().meals().findAll();
        List<Meal> belongingMeals = new ArrayList<>();
        for (Meal meal : meals){
            if (meal.belongsToMenu(m)) belongingMeals.add(meal);         
        }
        
        return belongingMeals;
    }
    
    
    private List<Dish> getDishesFromMeals (List<Meal> ml) 
    {
        List<Dish> dl = new ArrayList<>();
        for (Meal m : ml) 
        {
            dl.add(m.getDish());
        }
        
        return dl;
    }
    
    
    
    public MealPlan createNewMenuPlan (List<Integer> qts, List<Meal> ml) 
    {
        List<MealPlanItem> items = new ArrayList<>();
        
        for (Meal m : ml) 
        {
            items.add(new MealPlanItem(m, qts.get(0)));
            qts.remove(0);
        }
          
        MealPlan m = new MealPlan(items);    
        
        //validate m
        
        return m;
    }
}
