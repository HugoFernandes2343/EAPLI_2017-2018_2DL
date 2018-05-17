package eapli.ecafeteria.app.user.console.booking;

import eapli.ecafeteria.application.reservations.ConsultNextReservationController;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.framework.presentation.console.AbstractUI;


/**
 * @author <1160777@isep.ipp.pt>Marco Carneiro</1160777@isep.ipp.pt>
 */
public class ConsultNextReservationUI extends AbstractUI {

    ConsultNextReservationController cnrc = new ConsultNextReservationController();

    /**
     * doShow method fetches the next Reservation available to a user and prepares it to display
     *
     * @return control boolean
     */
    @Override
    protected boolean doShow() {
        Reservation nextReservation = cnrc.acquireNextReservation();
        System.out.println("==>> Next Booked Reservation <<==");
        if (nextReservation == null) {
            System.out.println("Error in Reservation format, pls try again!");
        } else {
            System.out.println(nextReservation.toString());
            return true;
        }
        return false;
    }

    @Override
    public String headline() {
        return ("Next Reservation");
    }
}
