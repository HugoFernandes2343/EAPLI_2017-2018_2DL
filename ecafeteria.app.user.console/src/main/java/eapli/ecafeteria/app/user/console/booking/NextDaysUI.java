/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.booking;

import eapli.ecafeteria.application.reservations.ConsultReservationsController;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.util.Console;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Andre Rodrigues <1151136@isep.ipp.pt>
 */
public class NextDaysUI extends AbstractUI {
        
    private final ConsultReservationsController crController = new ConsultReservationsController();

    @Override
    protected boolean doShow() {
        System.out.println(">> Reservations made <<");
        
        List <Reservation> bookedList = (List <Reservation>) crController.findBooked();
        if (bookedList.isEmpty()) {
            System.out.println("Without reservations");
        } else {
            for (Reservation reservation : bookedList) {
                System.out.println("ID: " + reservation.id() + "\tDescription: " + reservation.description());
            }
        }    
        return true;
    }

    @Override
    public String headline() {
        return "Consult Reservations";
    }
    
}
