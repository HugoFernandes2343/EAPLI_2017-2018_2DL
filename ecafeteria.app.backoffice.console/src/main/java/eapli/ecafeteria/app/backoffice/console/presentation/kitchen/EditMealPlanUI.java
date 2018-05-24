/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.ElaborateMealPlanController;
import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.kitchen.MealPlanItem;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo Jorge
 */
public class EditMealPlanUI extends AbstractUI {

    private final ElaborateMealPlanController theController = new ElaborateMealPlanController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        List<MealPlan> listaMenu = theController.findAllMealPlanInProgress();
        if (listaMenu.isEmpty()) {
            System.out.println("No exist Meal Plans");
            return false;
        }

        System.out.println("\n\nList Meals Plans");
        for (MealPlan mealPlan : listaMenu) {
            System.out.println(mealPlan.toString() + "\n\n");
        }

        boolean resposta = false;

        String name = null;

        while (resposta == false) {
            name = Console.readLine("Enter the ID of the menu you want according to the displayed list!");
            resposta = theController.verificarIDMealPlan(name);
        }

        MealPlan mp = theController.selectMealPlan(name);

        if (mp.getLmpi().isEmpty()) {
            System.out.println("No exist Meal Plan Items");
            return false;
        }

        System.out.println("List MealPlanItem\n");
        for (int i = 0; i < mp.getLmpi().size(); i++) {
            System.out.println("ID:" + i + mp.getLmpi().get(i)+"\n");
        }
        int id = -1;
        while (id > mp.getLmpi().size() || id < 0) {
            id = Console.readInteger("Enter the ID you want according to the displayed list!");
        }

        int quantity = Console.readInteger("Insert new quantity!");
        mp.getLmpi().get(id).setDishQuantity(quantity);
        try {
            theController.saveMealPlan(mp);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(EditMealPlanUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    @Override
    public String headline() {
        return "Edit Meal Plan";
    }

}
