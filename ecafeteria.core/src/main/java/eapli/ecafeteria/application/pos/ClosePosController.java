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
            
                 
        //Bubble sort
        
        boolean is_sorted;
        POS sortingTemp;
        
        for (int i = 0; i < list_pos.size(); i++) {

            is_sorted = true;

            for (int j = 1; j < (list_pos.size() - i); j++) {

                if (list_pos.get(j - 1).code() > list_pos.get(j).code()) {
                    sortingTemp = list_pos.get(j - 1);
                    list_pos.set(j-1, list_pos.get(j));
                    list_pos.set(j, sortingTemp);
                    is_sorted = false;
                }

            }

            // is sorted? then break it, avoid useless loop.
            if (is_sorted) {
                break;
            }

        }
        
            for(POS p : list_pos){
                System.out.println("POS "+p.code());
            }
        }
                
        if(list_pos.isEmpty()){
            return false;
        }
        return true;
    }
    
    
    public boolean CloseAndSavePOS(int code) throws POSStateViolationException, DataConcurrencyException, DataIntegrityViolationException{
        
        return cpS.CloseAndSavePOS(code, list_pos);
    }

}
