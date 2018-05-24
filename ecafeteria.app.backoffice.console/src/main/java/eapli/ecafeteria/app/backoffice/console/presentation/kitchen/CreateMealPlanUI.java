
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.ElaborateMealPlanController;
import eapli.ecafeteria.domain.kitchen.MealPlan;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Paulo Jorge
 */
public class CreateMealPlanUI extends AbstractUI {

    private final ElaborateMealPlanController theController = new ElaborateMealPlanController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {

        List<Menu> listaMenu = theController.fetchAvailableMenus();
        if (listaMenu.isEmpty()) {
            System.out.println("No exist menus");
            return false;
        }


        System.out.println("\n\nList Menus");
        for (Menu menu : listaMenu) {
            System.out.println("ID:" + menu.id()
                    + "\nStart Date:" + menu.startDate().getTime()
                    + "\nEnd Date:" + menu.finishDate().getTime()
                    + "\nDesignation:" + menu.designation()+"\n\n");
        }

        boolean resposta = false;
        long id = -1;
        while (resposta == false) {
            id = Console.readLong("Enter the ID of the menu you want according to the displayed list! (0 the end)");
            resposta = theController.verificarIDMenu(id);
            if (id == 0) {
                return false;
            }
        }

        Menu m = theController.selectMenu(id);

        List<Meal> listaMeals = theController.getMealsfromMenu();
        if (listaMeals.isEmpty()) {
            System.out.println("No exist mseals");
            return false;
        }

        MealPlan mp = null;
        String nameMenu = Console.readLine("\n\nEnter the name for the meal plan");
        mp = theController.createElaborateMealplan(nameMenu);


        int cont = 0;
        for (Meal meal : listaMeals) {
            System.out.println(meal.toString());
            int quantidade = Console.readInteger("Enter the quantity you want for " + listaMeals.get(cont).dish().name() + " !");
            try {
                theController.insertQuantityMeal(quantidade, meal, mp);
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                Logger.getLogger(CreateMealPlanUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            cont++;
        }

        try {
            theController.saveMealPlan(mp);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(CreateMealPlanUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public String headline() {
        return "Create Meal Plan";
    }

}
