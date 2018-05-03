
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.ElaborateMealPlanController;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.ArrayList;
import java.util.Calendar;
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

        System.out.println("Lista de Menus");
        List<Menu> listaMenu = theController.fetchAvailableMenus();
        for (Menu menu : listaMenu) {
            System.out.println(menu.id() + "\n");
        }
        long id = Console.readLong("Insere o ID do menu que Pretende!");
        theController.verificarID(id);
        Iterable<Meal> listaMeals = theController.getMealsfromMenu(id);

        List<Meal> listaMeal = (List<Meal>) listaMeals;
        System.out.println("Meals do menu " + id);
        for (Meal meal : listaMeal) {
            System.out.println(meal.dish().name() + "\n");
        }

        for (Meal meal : listaMeal) {
            int quantidade = Console.readInteger("Insere a quantidade que pretende!");
            theController.insertQuantityMeal(quantidade, meal);
        }
        try {
            System.out.println(theController.createElaborateMealplan());
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(CreateMealPlanUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(CreateMealPlanUI.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    @Override
    public String headline() {
        return "Create Meal Plan";
    }

}
