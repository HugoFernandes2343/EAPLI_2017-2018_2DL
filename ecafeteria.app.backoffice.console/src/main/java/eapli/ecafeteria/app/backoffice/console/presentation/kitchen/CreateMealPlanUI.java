
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
import java.util.ArrayList;
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

        System.out.println("List Menus");
        List<Menu> listaMenu = theController.fetchAvailableMenus();
        if (listaMenu.isEmpty()) {
            System.out.println("No exist menus");
            return false;
        }
        for (Menu menu : listaMenu) {
            System.out.println(menu.id());
        }

        boolean resposta = false;
        long id = 0;
        while (resposta == false) {
            id = Console.readLong("Insere o ID do menu que pretende conforme a lista apresentada!");
            resposta = theController.verificarIDMenu(id);
        }

        Menu m = theController.selectMenu(id);

        List<Meal> listaMeals = theController.getMealsfromMenu();
        if (listaMeals.isEmpty()) {
            System.out.println("No exist mseals");
            return false;
        }
        List<String> names = new ArrayList<>();
        System.out.println("\n------------------Meals do menu " + id + "---------------- ");

        for (Meal meal : listaMeals) {
            names.add(meal.dish().name().toString());
            System.out.println(meal.dish().name());
        }
        MealPlan mp = null;
        try {
            mp = theController.createElaborateMealplan();
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(CreateMealPlanUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(CreateMealPlanUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        int cont = 0;
        for (Meal meal : listaMeals) {
            int quantidade = Console.readInteger("Insere a quantidade que pretende para " + names.get(cont) + " !");
            try {
                theController.insertQuantityMeal(quantidade, meal, mp);
            } catch (DataConcurrencyException ex) {
                Logger.getLogger(CreateMealPlanUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DataIntegrityViolationException ex) {
                Logger.getLogger(CreateMealPlanUI.class.getName()).log(Level.SEVERE, null, ex);
            }
            cont++;
        }

        return true;
    }

    @Override
    public String headline() {
        return "Create Meal Plan";
    }

}
