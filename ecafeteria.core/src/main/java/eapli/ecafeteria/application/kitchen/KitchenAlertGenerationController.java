/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.persistence.*;

/**
 *
 * @author 1150425
 */
public class KitchenAlertGenerationController {

    private int reservedQuantity;
    private int plannedQuantity;
    private int dangerValue;

    private final MealRepository mRepo = PersistenceContext.repositories().meals();
    private final MealPlanItemRepository dRepo = PersistenceContext.repositories().mealPlanItemRepository();
    private final KitchenAlertsRepository kaRepo = PersistenceContext.repositories().KitchenAlertsRepository();

    private void getReservedQuantity(Long id) {

        reservedQuantity = 4;

    }

    private void getPlannedQuantity(Long id) {

        plannedQuantity = 5;
    }

    private void checkThreshold() {

        this.dangerValue = this.reservedQuantity / this.plannedQuantity;

        if (this.dangerValue > kaRepo.getYellowValue() || this.dangerValue < kaRepo.getRedValue()) {
            System.out.println("The reservations are above the Yellow threshold");
        } else if (this.dangerValue < kaRepo.getRedValue()) {
            System.out.println("The reservations are above the Red threshold");
        }

    }

}
