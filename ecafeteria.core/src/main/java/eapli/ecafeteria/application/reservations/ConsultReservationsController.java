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
    private final ReservationRepository reservationRepo = PersistenceContext.repositories().reservations();

    public Iterable<Reservation> findBooked(Date startDate, Date endDate, Reservation.ReservationState.STATE state){
        return listReservationService.findBooked(startDate, endDate, state);
    }
    
    public Iterable<Reservation> findDelivered(Date startDate, Date endDate, Reservation.ReservationState.STATE state){
        return listReservationService.findDelivered(startDate, endDate, state);
    }
    
    public Iterable<Reservation> findCancelled(Date startDate, Date endDate, Reservation.ReservationState.STATE state){
        return listReservationService.findCancelled(startDate, endDate, state);
    }
    
    public Iterable<Reservation> findExpired(Date startDate, Date endDate, Reservation.ReservationState.STATE state){
        return listReservationService.findExpired(startDate, endDate, state);
    }
    
    
}

