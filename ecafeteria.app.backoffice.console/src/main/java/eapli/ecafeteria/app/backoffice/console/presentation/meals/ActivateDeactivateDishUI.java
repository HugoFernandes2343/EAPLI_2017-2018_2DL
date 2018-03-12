/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.ecafeteria.application.dishes.ActivateDeactivateDishController;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mcn
 */
class ActivateDeactivateDishUI extends AbstractUI {

    private final ActivateDeactivateDishController theController = new ActivateDeactivateDishController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {

        final Iterable<Dish> allDishes = this.theController.allDishes();
        if (!allDishes.iterator().hasNext()) {
            System.out.println("There are no registered Dishes");
        } else {
            final SelectWidget<Dish> selector = new SelectWidget<>("Dishes:", allDishes, new DishPrinter());
            selector.show();
            final Dish updtDish = selector.selectedElement();
            try {
                this.theController.changeDishState(updtDish);
            } catch (DataConcurrencyException ex) {
                System.out
                        .println("It is not possible to change the dish state because it was changed by another user");
            } catch (DataIntegrityViolationException ex) {
                // should not happen!
                Logger.getLogger(ActivateDeactivateDishTypeUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    @Override
    public String headline() {
        return "Activate / Deactivate Dishes";
    }
}
