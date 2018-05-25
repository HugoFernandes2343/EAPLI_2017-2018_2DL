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

    private final ReservationRepository reservationRepo = PersistenceContext.repositories().reservations();
    private final MealPlanItemRepository mealPlanItemRepo = PersistenceContext.repositories().mealPlanItemRepository();
    private final ReservationRepository rRepo = PersistenceContext.repositories().reservations();
    private final MealPlanItemRepository mpiRepo = PersistenceContext.repositories().mealPlanItemRepository();
    private final KitchenAlertsRepository kaRepo = PersistenceContext.repositories().KitchenAlertsRepository();

    public void setMeal(Meal m) {
        this.m = m;
    }

    public void checkThreshold() {

        //reservedQuantity = rRepo.findByID(m.id).count();
        reservedQuantity = 3;
        // plannedQuantity = mpiRepo.findByMeal(m).getDishQuantity();
        plannedQuantity = 5;
        this.dangerValue = this.reservedQuantity / this.plannedQuantity;

        if (this.dangerValue > kaRepo.getYellowValue() || this.dangerValue < kaRepo.getRedValue()) {
            System.out.println("*************************************************\n");
            System.out.println("***                                           ***\n");
            System.out.println("***                                           ***\n");
            System.out.println("***         Reservation Alert                 ***\n");
            System.out.println("* The reservations are above the Yellow threshold *");
            System.out.println("***                                           ***\n");
            System.out.println("***                                           ***\n");
            System.out.println("*************************************************\n");

        } else if (this.dangerValue < kaRepo.getRedValue()) {

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

    public List<Reservation> getReservationfromMeal(Meal meal) {
        List<Reservation> listMeals;
        listMeals = (List<Reservation>) reservationRepo.findReservationFromMeal(meal);
        return listMeals;
    }

    public List<MealPlanItem> getQuantityFromMeal(Meal meal) {
        List<MealPlanItem> listQuantity;
        listQuantity = (List<MealPlanItem>) mealPlanItemRepo.findMealsFromMealPlanItem(meal);
        return listQuantity;
    }

}
