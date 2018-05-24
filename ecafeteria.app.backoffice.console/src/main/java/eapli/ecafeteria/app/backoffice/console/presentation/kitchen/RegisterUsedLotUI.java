/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.RegisterUsedLotController;
import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;
import java.util.Calendar;

/**
 *
 * @author 1161569
 */
public class RegisterUsedLotUI extends AbstractUI {

    private final RegisterUsedLotController theController = new RegisterUsedLotController();
    
    @Override
    protected boolean doShow() {
       Calendar cal=Console.readCalendar("Please enter date for meal search(yyyy/mm/dd)", "yyyy/MM/dd");
       SelectWidget<Meal> mealSelector = new SelectWidget<>("Meals:", theController.findMeals(cal), new MealPrinter());
       mealSelector.show();
       final Meal meal=mealSelector.selectedElement();
       SelectWidget<Lot> lotSelector = new SelectWidget<>("Lots:", theController.findLots(), new LotPrinter());
       lotSelector.show();
       final Lot lot=lotSelector.selectedElement();
       System.out.println(meal.toString());
       System.out.println(lot.toString());
       final int op=Console.readInteger("Please press 1 to confirm or any integer to exit");
       if(op!=1){
           System.out.println("Registration of used lot cancelled");
           return false;
       }
       try{
            theController.registerLot(meal, lot);
       }catch(IllegalArgumentException | DataConcurrencyException | DataIntegrityViolationException ex){
           System.out.printf("\nAn error has ocurred. Please check one of the following:"
                    + "\n1. Check if the choosen lot has not already been registered for choosen meal"
                    + "\n2. An transactional error may have ocurred");
       }
       
       return false;
    }

    @Override
    public String headline() {
        return "Register Used Lot";
    }
    
}
