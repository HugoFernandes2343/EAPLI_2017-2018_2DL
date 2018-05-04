package eapli.ecafeteria.app.backoffice.console.presentation;

import eapli.ecafeteria.application.kitchen.CafeteriaShiftClosingController;
import eapli.framework.application.Controller;
import eapli.framework.domain.CafeteriaShiftStateViolationException;
import eapli.framework.domain.POSStateViolationException;
import eapli.framework.domain.ReservationStateViolationException;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Norberto Sousa - 1120608 && Hugo Fernandes 1161155
 */
public class CafeteriaShiftClosingUI extends AbstractUI {

    private final CafeteriaShiftClosingController controller = new CafeteriaShiftClosingController();

    protected Controller controller() {
        return this.controller;
    }

    @Override
    protected boolean doShow() {
        
        String val = Console.readLine("Close cafeteria shift? (y | n)");

        if (val.equalsIgnoreCase("y") || val.equalsIgnoreCase("yes")) {
            try {
                controller.closeAllActivePOS();
            } catch (DataConcurrencyException | DataIntegrityViolationException | ReservationStateViolationException | POSStateViolationException | CafeteriaShiftStateViolationException ex) {
                System.out.println("An error has ocurred");
            }
            System.out.println("Shift Closed!");
        } else {
            System.out.println("Operation Canceled!");
        }
        return false;
    }

    @Override
    public String headline() {
        return "Close Cafeteria Shift";
    }

}
