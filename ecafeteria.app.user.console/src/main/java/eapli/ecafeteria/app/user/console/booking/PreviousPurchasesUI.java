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
import eapli.framework.util.Console;
import java.util.List;

/**
 *
 * @author Andre Rodrigues <1151136@isep.ipp.pt>
 */
public class PreviousPurchasesUI extends CafeteriaUserBaseUI{
    
    ConsultReservationsController crController = new ConsultReservationsController();

    @Override
    protected boolean doShow() {
        final int state = Console.readInteger("Types:\n1. Consumed\n2. Cancelled\n3. Expired\n\nPlease choose an option");
        switch (state) {
            case 1: 
                List <Reservation> deliveredList = (List <Reservation>) crController.findDelivered();
                System.out.println("\n>> List of delivered reservations <<");
                if (deliveredList.isEmpty()) {
                    System.out.println("No reservation has been consumed");
                } else {
                    for (Reservation reservation : deliveredList) {
                        System.out.println(reservation.toString());
                    }
                }
                break;
            case 2:
                List <Reservation> cancelledList = (List <Reservation>) crController.findCancelled();
                System.out.println("\n>> List of cancelled reservations <<");
                if (cancelledList.isEmpty()) {
                    System.out.println("No reservation has been cancelled");
                } else {
                    for (Reservation reservation : cancelledList) {
                        System.out.println(reservation.toString());
                    }
                }                
                break;
            case 3: 
                List <Reservation> expiredList = (List <Reservation>) crController.findExpired();
                System.out.println("\n>> List of expired reservations <<");
                if (expiredList.isEmpty()) {
                    System.out.println("No reservation has expired");
                } else {
                    for (Reservation reservation : expiredList) {
                        System.out.println(reservation.toString());
                    }
                }
                break;
        }
        return true;
    }

    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }
}
