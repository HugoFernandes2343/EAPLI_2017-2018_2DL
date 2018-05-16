package eapli.ecafeteria.application.reservations;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.persistence.*;
import java.util.Date;
import java.util.Optional;

/**
 *
 * @author Andre Rodrigues <1151136@isep.ipp.pt>
 */
public class ListReservationService {
    
    private final ReservationRepository reservationRepository = PersistenceContext.repositories().reservations();
    private final CafeteriaUserService userService = new CafeteriaUserService();
    
    public Iterable<Reservation> findBooked(){  
        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(AuthorizationService.session().authenticatedUser().id());
        return reservationRepository.selectTypeBooked(user.get());
    }
    
    public Iterable<Reservation> findDelivered(){
        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(AuthorizationService.session().authenticatedUser().id());        
        return reservationRepository.selectTypeDelivered(user.get());
    }

    public Iterable<Reservation> findCancelled(){  
        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(AuthorizationService.session().authenticatedUser().id());
        return reservationRepository.selectTypeCancelled(user.get());
    }
    
    public Iterable<Reservation> findExpired(){     
        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(AuthorizationService.session().authenticatedUser().id());
        return reservationRepository.selectTypeExpired(user.get());
    }
}