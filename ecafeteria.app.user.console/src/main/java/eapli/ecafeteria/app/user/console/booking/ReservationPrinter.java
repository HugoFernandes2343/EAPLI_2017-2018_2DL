/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.booking;

import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Hugo
 */
public class ReservationPrinter implements Visitor<Reservation> {

    public ReservationPrinter() {
    }

    @Override
    public void visit(Reservation visitee) {
        System.out.println(visitee.toString());
    }
    
}
