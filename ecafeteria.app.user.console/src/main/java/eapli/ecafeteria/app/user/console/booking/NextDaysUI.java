/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.booking;

import eapli.ecafeteria.app.user.console.presentation.CafeteriaUserBaseUI;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.application.reservations.ConsultReservationsController;
import eapli.ecafeteria.domain.reservations.Reservation;
import java.util.List;

/**
 *
 * @author Andre Rodrigues <1151136@isep.ipp.pt>
 */
public class NextDaysUI extends CafeteriaUserBaseUI {
        
    private final ConsultReservationsController crController = new ConsultReservationsController();

    @Override
    protected boolean doShow() {
        System.out.println(">> Reservations made <<");
        
        List <Reservation> bookedList = (List <Reservation>) crController.findBooked();
        if (bookedList.isEmpty()) {
            System.out.println("Without reservations");
        } else {
            for (Reservation reservation : bookedList) {
                System.out.println(reservation.toString());
            }
        }    
        return true;
    }

    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }
    
}
