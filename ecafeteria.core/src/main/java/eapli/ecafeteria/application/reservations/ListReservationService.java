package eapli.ecafeteria.application.reservations;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.persistence.*;
import java.util.Date;

/**
 *
 * @author Andre Rodrigues <1151136@isep.ipp.pt>
 */
public class ListReservationService {
    
    private final ReservationRepository reservationRepository = PersistenceContext.repositories().reservations();
    
    public Iterable<Reservation> findBooked(Date startDate, Date endDate, Reservation.ReservationState.STATE state){        
        return reservationRepository.selectTypeBooked(startDate, endDate, state);
    }
    
    public Iterable<Reservation> findDelivered(Date startDate, Date endDate, Reservation.ReservationState.STATE state){        
        return reservationRepository.selectTypeDelivered(startDate, endDate, state);
    }

    public Iterable<Reservation> findCancelled(Date startDate, Date endDate, Reservation.ReservationState.STATE state){        
        return reservationRepository.selectTypeCancelled(startDate, endDate, state);
    }
    
    public Iterable<Reservation> findExpired(Date startDate, Date endDate, Reservation.ReservationState.STATE state){        
        return reservationRepository.selectTypeExpired(startDate, endDate, state);
    }
}