package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.application.pos.RechargeCardController;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.movement.Booking;
import eapli.ecafeteria.domain.movement.Movement;
import eapli.ecafeteria.persistence.MovementRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author filip
 */
public class JpaMovementRepository extends CafeteriaJpaRepositoryBase<Movement, Long> implements MovementRepository{

    @Override
    public Iterable<Movement> allCafeteriaUserMovements(MecanographicNumber user) {

        return match("e.user = user");
    }

    @Override
    public void addBookingMovement(Booking booking) {
        final MovementRepository rcc = PersistenceContext.repositories().movements();
        
        try {
            rcc.save(booking);
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(JpaMovementRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(JpaMovementRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        
}
