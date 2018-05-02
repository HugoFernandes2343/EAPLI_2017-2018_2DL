
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.ElaborateMealPlanController;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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
        System.out.println(listaMenu);
        long id = Console.readLong("Insere o ID do menu que Pretende!");
        theController.verificarID(id);
        return true;
    }

    @Override
    public String headline() {
        return "Create Meal Plan";
    }

}
