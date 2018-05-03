package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.cafeteriauser.Movement;
import eapli.ecafeteria.domain.cafeteriauser.MovementDescription;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.MovementRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1161110 & 1161213
 */
public class RechargeCardController implements Controller{
    
    private final CafeteriaUserRepository repo = PersistenceContext.repositories().cafeteriaUsers();
    
    private final MovementRepository repo2 = PersistenceContext.repositories().movements();
    
    public RechargeCardController() {
    }
    
    public CafeteriaUser findCafeteriaUserByMecNumber(String mecNumber) {
        return this.repo.findByMecanographicNumber(new MecanographicNumber(mecNumber)).get();
    }
    
    public boolean rechargeCard(CafeteriaUser user, double value) {
        Movement mov = new Movement(user,Money.euros(value), MovementDescription.RECHARGE);
        try {
            repo2.save(mov);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(RechargeCardController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
         
}
