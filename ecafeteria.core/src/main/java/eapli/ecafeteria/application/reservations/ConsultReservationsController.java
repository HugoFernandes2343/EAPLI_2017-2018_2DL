/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.reservations;

import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.persistence.*;
import eapli.framework.application.Controller;
import java.util.*;

/**
 *
 * @author Andre Rodrigues <1151136@isep.ipp.pt>
 */
public class ConsultReservationsController implements Controller{
    
    private final ListReservationService listReservationService = new ListReservationService();
    
    public Iterable<Reservation> findBooked(){
        return listReservationService.findBooked();
    }
    
    public Iterable<Reservation> findDelivered(){
        return listReservationService.findDelivered();
    }
    
    public Iterable<Reservation> findCancelled(){
        return listReservationService.findCancelled();
    }
    
    public Iterable<Reservation> findExpired(){
        return listReservationService.findExpired();
    }
    
}

