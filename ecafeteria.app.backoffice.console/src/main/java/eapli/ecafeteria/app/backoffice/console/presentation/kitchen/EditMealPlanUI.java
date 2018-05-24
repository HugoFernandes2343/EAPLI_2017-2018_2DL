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
       
        String name=null;
          
        while (resposta == false) {
            name = Console.readLine("Insere o ID do menu que pretende conforme a lista apresentada!");
            resposta = theController.verificarIDMealPlan(name);
        }

        MealPlan mp = theController.selectMealPlan(name);
        
        if (mp.getLmpi().isEmpty()) {
            System.out.println("No exist Meal Plan Items");
            return false;
        }
        
        System.out.println("List MealPlanItem \nID--Quantity");
        for (MealPlanItem mealPlanItem : mp.getLmpi()) {
            System.out.println(mealPlanItem.toString());
        }
        
        
        
        
        
        int quantidade = Console.readInteger("Insere a nova quantidade!");
        mp.getLmpi().get(0).setDishQuantity(quantidade);
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
