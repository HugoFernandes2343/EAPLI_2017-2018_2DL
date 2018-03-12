/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.ecafeteria.application.dishes.ChangeDishTypeController;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;

/**
 *
 * @author Nuno
 */
public class ChangeDishTypeUI extends AbstractUI {

    private final ChangeDishTypeController theController = new ChangeDishTypeController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final Iterable<DishType> dishTypes = this.theController.listDishTypes();
        final SelectWidget<DishType> selector = new SelectWidget<>("Dish types:", dishTypes, new DishTypePrinter());
        selector.show();
        final DishType theDishType = selector.selectedElement();
        if (theDishType != null) {
            final String newDescription = Console
                    .readLine("Enter new description for " + theDishType.description() + ": ");
            try {
                this.theController.changeDishType(theDishType, newDescription);
            } catch (DataConcurrencyException ex) {
                System.out.println("That entity has already been changed or deleted since you last read it");
                //Logger.getLogger(ChangeDishTypeUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (DataIntegrityViolationException ex) {
                System.out.println("That entity ID is already in use");
                //Logger.getLogger(ChangeDishTypeUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public String headline() {
        return "Change Dish Type description";
    }
}
