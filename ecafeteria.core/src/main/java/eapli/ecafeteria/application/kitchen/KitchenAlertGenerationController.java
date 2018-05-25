/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.MealPlanItem;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.persistence.*;
import java.util.List;

/**
 *
 * @author 1150425
 */
public class KitchenAlertGenerationController {

    private int reservedQuantity;
    private int plannedQuantity;
    private int dangerValue;
    private Meal m;
    private String name = "Kitchen Alerts";

    private final ReservationRepository reservationRepo = PersistenceContext.repositories().reservations();
    private final MealPlanItemRepository mealPlanItemRepo = PersistenceContext.repositories().mealPlanItemRepository();
    private final KitchenAlertsRepository kaRepo = PersistenceContext.repositories().KitchenAlertsRepository();

    public void setMeal(Meal m) {
        this.m = m;
    }

    public void checkThreshold() {

        reservedQuantity = this.getReservationsByMeal();
        plannedQuantity = this.getPlannedQuantityFromMeal();
        this.dangerValue = this.reservedQuantity / this.plannedQuantity;
        
        int redAlert = kaRepo.findByName(name).get().redAlertValue();
        int yellowAlert = kaRepo.findByName(name).get().yellowAlertValue();

        if (this.dangerValue > yellowAlert && this.dangerValue < redAlert) {
            System.out.println("*************************************************\n");
            System.out.println("***                                           ***\n");
            System.out.println("***                                           ***\n");
            System.out.println("***         Reservation Alert                 ***\n");
            System.out.println("* The reservations are above the Yellow threshold *");
            System.out.println("***                                           ***\n");
            System.out.println("***                                           ***\n");
            System.out.println("*************************************************\n");

        } else if (this.dangerValue < redAlert) {

            System.out.println("*************************************************\n");
            System.out.println("***                                           ***\n");
            System.out.println("***                                           ***\n");
            System.out.println("***         Reservation Alert                 ***\n");
            System.out.println("The reservations are above the Red threshold");
            System.out.println("***                                           ***\n");
            System.out.println("***                                           ***\n");
            System.out.println("*************************************************\n");
        }

    }

    private int getReservationsByMeal() {
        List<Reservation> listMeals;
        listMeals = (List<Reservation>) reservationRepo.findReservationFromMeal(this.m);
        return listMeals.size();
    }

    private int getPlannedQuantityFromMeal() {
        MealPlanItem mealPlanItem;
        mealPlanItem = (MealPlanItem) mealPlanItemRepo.findMealsFromMealPlanItem(this.m);
        return mealPlanItem.getDishQuantity();
    }

}
