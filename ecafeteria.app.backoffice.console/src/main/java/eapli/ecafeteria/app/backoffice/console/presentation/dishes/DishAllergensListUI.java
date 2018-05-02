/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.dishes;

import eapli.ecafeteria.application.allergens.DishAllergensListController;
import eapli.ecafeteria.domain.allergens.Allergen;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.*;
import eapli.framework.util.Console;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1150425
 */
class DishAllergensListUI extends AbstractUI {

    private final DishAllergensListController theController = new DishAllergensListController();

    protected DishAllergensListController controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {

        final Iterable<Dish> allDishes = this.theController.allDishes();

        do {
            if (!allDishes.iterator().hasNext()) {
                System.out.println("There are no registered Dishes");
                return false;
            }
            final SelectWidget<Dish> dishSelector = new SelectWidget<>("Dishes:", allDishes, new DishPrinter());

            System.out.println("Choose the dish in which you want to add allergens.");
            dishSelector.show();
            final Dish selectedDish = dishSelector.selectedElement();

            System.out.println("Allergens contained in the chosen dish:");
            List<Allergen> allergenList = theController.dishAllergens(selectedDish);
            if (allergenList.isEmpty()) {
                System.out.println("There are no registered Dishes");
            } else {
                for (Allergen a : allergenList) {
                    System.out.println(a.toString());
                }
            }
            boolean success = false;
            if (allergenList.isEmpty()) {
                if (allergenList.isEmpty()) {
                    System.out.println("Adding Allergens to the list:");
                    SelectWidget<Allergen> allergenSelector = new SelectWidget<>("Allergen List:", theController.allAllergens());
                    allergenSelector.show();

                    Allergen selectedAllergen = allergenSelector.selectedElement();

                    try {
                        success = theController.addAllergenToDish(selectedDish, selectedAllergen);
                    } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
                        Logger.getLogger(DishAllergensListUI.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (!success) {
                        System.out.println("Dish's allergen list already contains that allergen.");
                    }
                }
            } else {
                System.out.println("Do you wish to:\n1. Add an allergen to the list.\n2. Remove an allergen to the list.\n0. Exit");
                int option;
                do {
                    option = Console.readInteger("Choose an option:");
                    if (option == 0) {
                        return false;
                    }
                } while (option != 0 && option != 1 && option != 2);

                if (option == 1) {
                    SelectWidget<Allergen> allergenSelector = new SelectWidget<>("Allergen List:", theController.allAllergens());
                    allergenSelector.show();

                    Allergen selectedAllergen = allergenSelector.selectedElement();

                    try {
                        success = theController.addAllergenToDish(selectedDish, selectedAllergen);
                    } catch (DataIntegrityViolationException | DataConcurrencyException ex) {
                        Logger.getLogger(DishAllergensListUI.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (!success) {
                        System.out.println("Dish's allergen list already contains that allergen.");
                    }

                } else {
                    SelectWidget<Allergen> allergenSelector = new SelectWidget<>("Allergen List:", theController.dishAllergens(selectedDish));
                    allergenSelector.show();

                    Allergen selectedAllergen = allergenSelector.selectedElement();

                    success = theController.removeAllergen(selectedAllergen, selectedDish);
                    if (!success) {
                        System.out.println("Dish's allergen list doesn't contain the chosen allergen.");
                    }
                }
            }
            if (success) {
                try {
                    theController.save(selectedDish);
                    System.out.println("Dish's allergen list was successefully updated.");
                } catch (DataConcurrencyException ex) {
                    System.out.println("It wasn't possible to make any alteration, since this dish has changed since it was read.");
                } catch (DataIntegrityViolationException ex) {
                    System.out.println("The inserted data is not valid.");
                }

                return false;
            }

        } while (true);
    }

    @Override
    public String headline() {
        return "Add Allergen to List";
    }

}
