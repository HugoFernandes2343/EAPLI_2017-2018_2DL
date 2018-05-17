package eapli.ecafeteria.application.reservations;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.reservations.Reservation;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.ReservationRepository;
import eapli.framework.application.Controller;

import java.util.Optional;

/**
 * @author <1160777@isep.ipp.pt>Marco Carneiro</1160777@isep.ipp.pt>
 */
public class ConsultNextReservationController implements Controller {

    private final ReservationRepository rrp = PersistenceContext.repositories().reservations();
    private final CafeteriaUserService userService = new CafeteriaUserService();

    /**
     * Acquires the next available/booked reservation for a user (user, date)
     *
     * @return next reservation
     */
    public Reservation acquireNextReservation() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(AuthorizationService.session().authenticatedUser().id());
        return this.rrp.findNextReservation(user.get()).iterator().next();//Gets the next and only element in the iterable
    }

}
