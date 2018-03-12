package eapli.ecafeteria.app.backoffice.console.presentation.meals;

import eapli.ecafeteria.application.dishes.ActivateDeactivateDishTypeController;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by MCN on 29/03/2016.
 */
public class ActivateDeactivateDishTypeUI extends AbstractUI {

    private final ActivateDeactivateDishTypeController theController = new ActivateDeactivateDishTypeController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {

        final Iterable<DishType> allDishTypes = this.theController.allDishTypes();
        if (!allDishTypes.iterator().hasNext()) {
            System.out.println("There are no registered Dish Types");
        } else {
            final SelectWidget<DishType> selector = new SelectWidget<>("Dish types:", allDishTypes,
                    new DishTypePrinter());
            selector.show();
            final DishType updtDishType = selector.selectedElement();
            try {
                this.theController.changeDishTypeState(updtDishType);
            } catch (DataConcurrencyException ex) {
                System.out.println(
                        "It is not possible to change the dish type state because it was changed by another user");
            } catch (DataIntegrityViolationException ex) {
                // should not happen!
                Logger.getLogger(ActivateDeactivateDishTypeUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    @Override
    public String headline() {
        return "Activate / Deactivate Dish Types";
    }
}
