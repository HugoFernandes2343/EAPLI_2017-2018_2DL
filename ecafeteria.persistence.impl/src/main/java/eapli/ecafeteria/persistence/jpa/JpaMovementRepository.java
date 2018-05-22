package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.movement.Booking;
import eapli.ecafeteria.domain.movement.Movement;
import eapli.ecafeteria.domain.movement.MovementBuilder;
import eapli.ecafeteria.domain.movement.MovementDescription;
import eapli.ecafeteria.persistence.MovementRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author filip
 */
public class JpaMovementRepository extends CafeteriaJpaRepositoryBase<Movement, Long> implements MovementRepository {

    private final CafeteriaUserService userService = new CafeteriaUserService();

    @Override
    public Iterable<Movement> allCafeteriaUserMovements(MecanographicNumber user) {

        return match("e.user = user");
    }

    @Override
    public void addBookingMovement(Booking booking) {
        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(AuthorizationService.session().authenticatedUser().id());
        final MovementBuilder movementBuilder = new MovementBuilder();
        movementBuilder.withCafeteriaUser(user.get()).withDescriptionAndMoney(MovementDescription.BOOKING, Money.euros(booking.value()));
        Booking mov = (Booking) movementBuilder.build();
        final MovementRepository rcc = PersistenceContext.repositories().movements();

        try {
            rcc.save(mov);
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(JpaMovementRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(JpaMovementRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
