package eapli.ecafeteria.application.reservations;

import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.ReservationRepository;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Norberto Sousa - 1120608
 */
public class RegisterReservationController implements Controller {

    public Reservation registerReservation(Reservation rs) throws DataConcurrencyException, DataIntegrityViolationException {
        ReservationRepository rRP = PersistenceContext.repositories().reservations();
        
        return rRP.save(rs);
    }

}
