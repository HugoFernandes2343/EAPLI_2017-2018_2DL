package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.POSStateViolationException;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;

public class ClosePosController implements Controller {

    private final POSRepository posRP = PersistenceContext.repositories().pos();

    

    public boolean ListAllActivePOS() throws POSStateViolationException {
        ArrayList<POS> list_pos = new ArrayList<>();
        POS temp = new POS(1);
        list_pos = (ArrayList<POS>) posRP.findOpenned(temp.state());
        
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
        ArrayList<POS> list_pos = new ArrayList<>();
        POS temp = new POS(1);
        list_pos = (ArrayList<POS>) posRP.findOpenned(temp.state());
        
        for(POS p : list_pos){
            if(p.id() == id){
                p.close();
                
                posRP.save(p);
                
                return true;
            }
        }
        
        return false;
    }

}
