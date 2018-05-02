package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.domain.pos.POSState;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.ReservationStateViolationException;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;

/**
 *
 * @author Norberto Sousa - 1120608 && Hugo Fernandes 1161155
 */
public class CafeteriaShiftClosingController implements Controller {

    private final POSRepository posRP = PersistenceContext.repositories().pos();

    
    /**
     * Closes all active POS
     * 
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException 
     */
    public void closeAllActivePOS() throws DataConcurrencyException, DataIntegrityViolationException, ReservationStateViolationException {
        
        ArrayList<POS> list = new ArrayList<>();
        list = (ArrayList<POS>) posRP.findOpenned(POSState.OPEN);
        
        for(POS p : list){
            p.closeShift();
            posRP.save(p);
        }
        
    }
}
