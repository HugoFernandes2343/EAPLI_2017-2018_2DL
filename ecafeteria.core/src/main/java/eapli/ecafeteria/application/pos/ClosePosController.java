package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.domain.pos.POSState;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.POSStateViolationException;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;

/**
 *
 * @author Norberto Sousa - 1120608 && Hugo Fernandes 1161155
 */
public class ClosePosController implements Controller {

    private final POSRepository posRP = PersistenceContext.repositories().pos();
    private final ClosePosService cpS = new ClosePosService();

    private ArrayList<POS> list_pos = new ArrayList<>();

    public boolean ListAllActivePOS() throws POSStateViolationException {

        list_pos = (ArrayList<POS>) posRP.findByState(POSState.OPENED);
        
        if(list_pos.isEmpty()){
            System.out.println("No POS currently open!");
        } else {
            System.out.println("Open POS:");
            for(POS p : list_pos){
                System.out.println("POS "+p.id());
            }
        }
        
        if(list_pos.isEmpty()){
            return false;
        }
        return true;
    }
    
    
    public boolean CloseAndSavePOS(Long id) throws POSStateViolationException, DataConcurrencyException, DataIntegrityViolationException{
        
        return cpS.CloseAndSavePOS(id, list_pos);
    }

}
