/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.booking;

import eapli.ecafeteria.application.reservations.CancelReservationController;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.framework.domain.ReservationStateViolationException;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;
import eapli.framework.util.Console;

/**
 *
 * @author 1161569
 */
public class CancelReservationUI extends AbstractUI{

    private final CancelReservationController theController = new CancelReservationController();
    
    @Override
    protected boolean doShow() {
        SelectWidget<Reservation> selector = new SelectWidget<>("Reservations:", theController.findBooked(), new ReservationPrinter());
        selector.show();
        final Reservation reserv = selector.selectedElement();
        Money value=reserv.acquireRefundValue();
        System.out.printf("The refund value will be %.2f\n",value.amount());
        final int op = Console.readInteger("Press 1 to confirm or any integer to exit");
        if(op!=1){
            System.out.println("Operation cancelled");
        }
        try{
            theController.cancelReservation(value, reserv);
            System.out.println("Reservation Cancelled.");
        } catch(ReservationStateViolationException | DataConcurrencyException | DataIntegrityViolationException ex){
            System.out.println("An error has ocurred. Please try again");
        }
        return false;
    }

    @Override
    public String headline() {
        return ("Cancel Reservation"); 
    }
    
}
