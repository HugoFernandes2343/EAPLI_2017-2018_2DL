/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.*;

/**
 *
 * @author 1150425
 */
public class KitchenAlertGenerationController {

    private int reservedQuantity;
    private int plannedQuantity;
    private int dangerValue;
    private Meal m;

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

}
