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
    
    public Iterable<Reservation> findBooked(){        
        return reservationRepository.selectTypeBooked();
    }
    
    public Iterable<Reservation> findDelivered(){        
        return reservationRepository.selectTypeDelivered();
    }

    public Iterable<Reservation> findCancelled(){        
        return reservationRepository.selectTypeCancelled();
    }
    
    public Iterable<Reservation> findExpired(){        
        return reservationRepository.selectTypeExpired();
    }
}