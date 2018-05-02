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

/**
 *
 * @author Andre Rodrigues <1151136@isep.ipp.pt>
 */
public class ConsultReservationsUI extends AbstractUI {
    
    // CONSULT TYPES
    private static final int BOOKED_OPTION = 1;
    private static final int DELIVERED_OPTION = 2;
    private static final int CANCELLED_OPTION = 3;
    private static final int EXPIRED_OPTION = 4;
    
    private final ConsultReservationsController crController = new ConsultReservationsController();

    @Override
    protected boolean doShow() {
        final Date startDate = Console.readDate("Choose start date (dd/mm/yyyy)");
        final Date endDate = Console.readDate("Choose end date (dd/mm/yyyy)");
        final int state = Console.readInteger("Choose type of consult: Booked (1), Delivered (2), Cancelled (3), Expired (4)");
        switch (state) {
            case 1: crController.findBooked(startDate, endDate, Reservation.ReservationState.STATE.BOOKED);
                break;
            case 2: crController.findDelivered(startDate, endDate, Reservation.ReservationState.STATE.DELIVERED);
                break;
            case 3: crController.findCancelled(startDate, endDate, Reservation.ReservationState.STATE.CANCELLED);
                break;
            case 4: crController.findExpired(startDate, endDate, Reservation.ReservationState.STATE.EXPIRED);
                break;
        }
        return true;
    }

    @Override
    public String headline() {
        return "Consult Reservations";
    }
    
}
