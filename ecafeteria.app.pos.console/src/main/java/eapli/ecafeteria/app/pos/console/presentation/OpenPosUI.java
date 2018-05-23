/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation;

import eapli.ecafeteria.application.pos.OpenPosController;
import eapli.framework.application.Controller;
import eapli.framework.domain.POSStateViolationException;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 *
 * @author Norberto Sousa - 1120608 && Hugo Fernandes 1161155
 */
public class OpenPosUI extends AbstractUI {

    private final OpenPosController cont = new OpenPosController();

    protected Controller controller() {
        return this.cont;
    }

    @Override
    protected boolean doShow() {

        boolean closedShift = false;

        closedShift = cont.IsTheCafeteriaShiftClosed();

        if (closedShift) {
            System.out.println("The cafeteria shift is not opened");
            
            boolean success = false;
            do {
                String dt = Console.readLine("Indicate the meal type of this shift(LUNCH/DINNER)");
                try {
                    success = cont.OpenAndUpdateCafeteriaShift(dt);
                } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                    System.out.printf("Daytime state Error");
                }
                if (!success) {
                    System.out.println("\n---Not a valid meal type, please choose between LUNCH and DINNER!---\n");
                }
            } while (!success);
        }

        
        boolean closed = true;
        try {
            closed = cont.ListAllClosedPOS();
        } catch (POSStateViolationException ex) {
            System.out.println("POS state error");
        }

        if (!closed) {
            return false;
        }

        Long pos;
        boolean success = false;

        do {
            pos = Console.readLong("Choose a pos to open:");
            try {
                success = cont.OpenAndSavePOS(pos);
            } catch (POSStateViolationException | DataConcurrencyException | DataIntegrityViolationException ex) {
                System.out.printf("POS State Error");
            }
            if (!success) {
                System.out.println("\n---Not a valid POS!---\n");

                try {
                    cont.ListAllClosedPOS();
                } catch (POSStateViolationException ex) {
                    System.out.println("POS state error");
                }
            }
        } while (!success);

        System.out.println("POS opened successfully!");
        return false;
    }

    @Override
    public String headline() {
        return "Open POS";
    }

}
