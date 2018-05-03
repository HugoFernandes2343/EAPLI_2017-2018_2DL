/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.app.backoffice.console.presentation.dishes.DishPrinter;
import eapli.ecafeteria.application.meal.PublishMealController;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hugo
 */
public class PublishMealUI extends AbstractUI {

     private final PublishMealController theController = new PublishMealController();
    
    @Override
    protected boolean doShow() {
        long menuID=Console.readLong("MenuId for new meal");
        Menu menu = theController.requestMenu(menuID);
        final Iterable<Dish> allDishes = this.theController.allActiveDishes();
        final SelectWidget<Dish> dishSelector = new SelectWidget<>("Dishes:", allDishes, new DishPrinter());
        System.out.println("Choose the dish in which you want to add allergens.");
        dishSelector.show();
        final Dish selectedDish = dishSelector.selectedElement();
        final Calendar date=Console.readCalendar("Please choose day of meal", "yyyy-mm-dd");
        if(menu.hasThisDay(date)){
             MealType type;
             System.out.println("Press 1 for Lunch. Press 2 for Dinner. Press 0 to exit");
             int op=Console.readOption(1, 2, 0);
             if(op==0){
                 System.out.println("Ending meal publishing.");
             } else {
                 if(op==1){
                     type=new MealType(MealType.MealTypes.LUNCH);
                 } else {
                     type=new MealType(MealType.MealTypes.DINNER);
                 }
                 Meal meal = null;
                 try {
                     meal = theController.buildMeal(selectedDish, type, date, menu);
                 } catch (DataConcurrencyException ex) {
                     Logger.getLogger(PublishMealUI.class.getName()).log(Level.SEVERE, null, ex);
                 } catch (DataIntegrityViolationException ex) {
                     Logger.getLogger(PublishMealUI.class.getName()).log(Level.SEVERE, null, ex);
                 }
                 op=Console.readInteger("Press 1 to confirm");
                 if(op==1){
                     try {
                         menu.addMeal(meal);
                         theController.save(menu);
                     }  catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                         System.out.println("Error while persisting changes");
                     }
                 }
             }
        } else {
            System.out.println("This date does not belong to this menu. Ending meal publishing.");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Publish Meal";
    }
    
}
