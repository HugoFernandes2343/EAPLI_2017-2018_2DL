/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.ElaborateMealPlanController;
import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.kitchen.MealPlanItem;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.List;

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
        System.out.println("List Meals Plans");
        List<MealPlan> listaMenu = theController.findAllMealPlanInProgress();
        if (listaMenu.isEmpty()) {
            System.out.println("No exist Meal Plans");
            return false;
        }
        for (MealPlan mealPlan : listaMenu) {
            System.out.println(mealPlan.id());
        }
        boolean resposta = false;
        long id = 0;
        while (resposta == false) {
            id = Console.readLong("Insere o ID do menu que pretende conforme a lista apresentada!");
            resposta = theController.verificarIDMealPlan(id);
        }

        MealPlan mp = theController.selectMealPlan(id);

        List<MealPlanItem> listMPI = theController.getMealPlanItemList();
        if (listMPI.isEmpty()) {
            System.out.println("No exist Meal Plan Items");
            return false;
        }
        System.out.println("List MealPlanItem ID--Quantity");
        for (MealPlanItem mealPlanItem : listMPI) {
            System.out.println(mealPlanItem.toString());
        }
        resposta = false;
        long id1 = 0;
        while (resposta == false) {
            id1 = Console.readLong("Insere o ID do menu que pretende conforme a lista apresentada!");
            resposta = theController.verificarIDMealPlanItem(id1);
        }
        int quantidade = -1;

        while (quantidade < 0) {
            quantidade = Console.readInteger("Insere a nova quantidade!");
        }

        MealPlanItem MPI = theController.selectMealPlanItem(id1);

        theController.changeQuantity(id1, quantidade);

        return true;
    }

    @Override
    public String headline() {
        return "Edit Meal Plan";
    }

}
