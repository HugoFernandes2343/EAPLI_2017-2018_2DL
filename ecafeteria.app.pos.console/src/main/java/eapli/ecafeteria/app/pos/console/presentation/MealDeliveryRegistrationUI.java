/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.pos.console.presentation;

import eapli.ecafeteria.application.reservations.MealDeliveryRegistrationController;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.framework.application.Controller;
import eapli.framework.domain.ReservationStateViolationException;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;

/**
 *
 * @author Hugo
 */
public class MealDeliveryRegistrationUI extends AbstractUI {
    
    private final MealDeliveryRegistrationController theController = new MealDeliveryRegistrationController();

    protected Controller controller() {
        return this.theController;
    }
    
    @Override
    protected boolean doShow() {
        final String code = Console.readLine("Reservation code");
        Reservation reservation;
        try {
            reservation = theController.acquireReservation(code);
            System.out.println(reservation.toString());
            final int option = Console.readInteger("Press any number to regect or 1 to confirm Delivery");
            if(option == 1){
                reservation.deliver();
                this.theController.confirmDelivery(reservation); 
                System.out.println("Delivery has been successfull.");
            }
        } catch (DataConcurrencyException | DataIntegrityViolationException | NullPointerException | ReservationStateViolationException ex) {
            System.out.printf("\nAn error has ocurred. Please check one of the following:"
                    + "\n1. The code you entered might not exist. Please check"
                    + "\n2. Check reservation state is booked for possible delivery"
                    + "\n3. An transactional error may have ocurred");
        } 
        return false;
    }

    @Override
    public String headline() {
         return "Register meal delivery";
    }
    
}
