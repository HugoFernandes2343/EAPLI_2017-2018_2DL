/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.CheckExistingReservationsController;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.framework.application.Controller;

import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Calendar;
import java.util.Iterator;

/**
 *
 * @author Rodrigo
 */
public class CheckExistingReservationsUI extends AbstractUI {

    private final CheckExistingReservationsController controller = new CheckExistingReservationsController();

    protected Controller controller() {
        return this.controller;
    }
    
    @Override
    protected boolean doShow() {        
        Calendar date = askForDate ("Please type in a date:");
        
        MealType mealType = askForMealType ("Please choose a Meal time, respecting the casing of the available options:");
        
        Iterable<DishType> dishTypes = null;
        try{
            dishTypes = controller.findDishTypesByDateAndMealType (date, mealType);            
        } catch (DataIntegrityViolationException ex){
            System.out.println ("DataIntegrityViolationException has ocurred");
        } catch (DataConcurrencyException ex) {
            System.out.println ("DataConcurrencyException has ocurred");
        }
        
        if (dishTypes.iterator().hasNext()){
            DishType dishType = askForDishType ("Please select a Dish Type from the available ones:", dishTypes);
        
            Iterable<Dish> dishes = controller.findDishesByDishType(dishType);

            Dish dish = askForDish ("Please select a Dish from the available ones:", dishes);

            Iterable<Reservation> reservations = controller.findReservationsBy(date, dishType, dish, mealType);
            
            if (reservations.iterator().hasNext()){
                showReservations ("Here are the existing Reservations:", reservations);
            }
            else{
                System.out.println("--------There are no Reservations for the selected date (Booked or otherwise)--------------");
            }
        }
        else{
            System.out.println("--------There are no Meals planned for the selected date--------------");
        }
        
        return true;
    }
    
    @Override
    public String headline() {
        return "Check Existing Reservations";
    }
    
    private Calendar askForDate (String prompt){
        return Console.readCalendar(prompt);
    }
    
    /**
     * I REALLY don't lie how the UI knows about and is dependent on the "MealTypes",
     * but I will create an issue for it.
     * @param prompt
     * @return 
     */
    private MealType askForMealType (String prompt){
        MealType mealType = null;
        
        while (mealType == null){
            System.out.println("Available Meal Types: ");
            for (int i = 0; i < MealType.MealTypes.values().length; i++) {
                System.out.println ("\""+MealType.MealTypes.values()[i]+"\"");
            }
            String mealTypeString = Console.readLine(prompt);

            try{
                MealType.MealTypes.valueOf (mealTypeString);
                mealType = new MealType(mealTypeString);
            }
            catch (IllegalArgumentException IAE){
                System.out.println ("Please respect letter casing and try again.");
            }
        }
        return mealType;
    }
    
    private DishType askForDishType (String prompt, Iterable<DishType> dishTypes){
        DishType dishType = null;
        
        while (dishType == null){
            System.out.println("Available Dish Types: ");
            int i=0;
            for (DishType next : dishTypes){
                System.out.println ( i + " - " + next.description() );
                i++;
            }
            int dishTypeInt = Console.readInteger(prompt);

            boolean foundIt = false;
            i=0;
            Iterator<DishType> it = dishTypes.iterator();
            while (it.hasNext() && !foundIt){
                DishType next = it.next();
                if (dishTypeInt == i){
                    dishType = next;
                    foundIt = true;
                }
                else{
                    i++;
                }                
            }
        }
        return dishType;
    }
    
    private Dish askForDish (String prompt, Iterable<Dish> dishes){
        Dish dish = null;
        
        while (dish == null){
            System.out.println("Available Dishes: ");
            int i=0;
            for (Dish next : dishes){
               // System.out.println ( i + " - " + next.toDTO().toString() );
               System.out.println ( i + " - " + next.id().toString() );
                i++;
            }
            int dishInt = Console.readInteger(prompt);

            boolean foundIt = false;
            i=0;
            Iterator<Dish> it = dishes.iterator();
            while (it.hasNext() && !foundIt){
                Dish next = it.next();
                if (dishInt == i){
                    dish = next;
                    foundIt = true;
                }
                else{
                    i++;
                }                
            }
        }
        return dish;
    }
    
    private void showReservations (String message, Iterable<Reservation> reservations){
        System.out.println (message);
        
        for (Reservation reservation: reservations) {
            System.out.println ("\"" + "USER: " + reservation.user().toString() + ".");
            System.out.println ("RESEVATION:" + reservation.toString() + "\"");
        }
    }
}
