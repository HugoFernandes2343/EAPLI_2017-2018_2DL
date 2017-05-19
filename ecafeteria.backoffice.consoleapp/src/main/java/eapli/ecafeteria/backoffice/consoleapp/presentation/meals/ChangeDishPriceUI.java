/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation.meals;

import eapli.ecafeteria.application.meals.ChangeDishController;
import eapli.ecafeteria.domain.meals.Dish;
import eapli.framework.application.Controller;
import eapli.framework.domain.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.io.Console;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PRP 29.mar.2017
 */
class ChangeDishPriceUI extends AbstractUI {

    private final ChangeDishController theController = new ChangeDishController();

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
            final Dish selectedDish = selector.selectedElement();
            System.out.println("Current  price: " + selectedDish.currentPrice().toString());
            try {
                final double newPrice = Console.readDouble(" New price");
                this.theController.changeDishPrice(selectedDish, Money.euros(newPrice));
            } catch (DataConcurrencyException ex) {
                System.out
                        .println("It is not possible to change the dish state because it was changed by another user");
            } catch (DataIntegrityViolationException ex) {
                // should not happen!
                Logger.getLogger(ChangeDishPriceUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    @Override
    public String headline() {
        return "Change Dish price";
    }
}
