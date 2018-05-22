/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meals.MealType;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.framework.application.Controller;
import java.util.Calendar;

/**
 *
 * @author Rodrigo
 */
public class CheckExistingReservationsController implements Controller{
    
    public Iterable<Reservation> checkExistingReservations(Calendar date, DishType dishType, Dish dish, MealType mealType){
        throw new UnsupportedOperationException ("Not supported yet.");
    }
}
