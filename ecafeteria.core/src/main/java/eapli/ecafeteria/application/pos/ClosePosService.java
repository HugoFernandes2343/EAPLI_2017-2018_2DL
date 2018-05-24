/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.domain.POSStateViolationException;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.ArrayList;

/**
 *
 * @author Norberto Sousa - 1120608 && Hugo Fernandes 1161155
 */
public class ClosePosService {

    private final POSRepository posRP = PersistenceContext.repositories().pos();

    public boolean CloseAndSavePOS(Long id, ArrayList<POS> list_pos) throws POSStateViolationException, DataConcurrencyException, DataIntegrityViolationException {

        for (POS p : list_pos) {
            if (p.id() == id) {
                p.close();

                SavePOS(p);

                return true;
            }
        }

        return false;
    }
    
    public POS SavePOS(POS p) throws DataConcurrencyException, DataIntegrityViolationException{
        return posRP.save(p);
    }
}
