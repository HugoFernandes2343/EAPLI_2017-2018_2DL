/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.CafeteriaShiftClosingController;
import eapli.ecafeteria.application.kitchen.CheckExistingReservationsController;
import eapli.framework.application.Controller;

import eapli.framework.domain.CafeteriaShiftStateViolationException;
import eapli.framework.domain.POSStateViolationException;
import eapli.framework.domain.ReservationStateViolationException;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import javax.persistence.NoResultException;

/**
 *
 * @author Rodrigo
 */
public class CheckExistingReservationsUI extends AbstractUI {

    private final CheckExistingReservationsController controller = new CheckExistingReservationsController();

    protected Controller controller() {
        return this.controller;
    }
    
    @Override
    protected boolean doShow() {
        
        /*String val = Console.readLine("Close cafeteria shift? (y | n)");

        if (val.equalsIgnoreCase("y") || val.equalsIgnoreCase("yes")) {
            try {
                controller.closeAllActivePOS();
            } catch (DataConcurrencyException | DataIntegrityViolationException | ReservationStateViolationException | POSStateViolationException | CafeteriaShiftStateViolationException ex) {
                System.out.println("An error has ocurred");
            } catch(NoResultException ex){
                 System.out.println("The cafeteria shift is not opened therefore can't be closed");
            }
            System.out.println("Shift Closed!");
        } else {
            System.out.println("Operation Canceled!");
        }
        return false;*/
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    @Override
    public String headline() {
        return "Check Existing Reservations";
    }
}
