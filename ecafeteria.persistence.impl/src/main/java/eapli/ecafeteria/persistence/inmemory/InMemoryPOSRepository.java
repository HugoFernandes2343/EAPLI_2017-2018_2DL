/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.domain.pos.POSState;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;
import java.util.ArrayList;
import java.util.Optional;

/**
 *
 * @author hugod
 */
public class InMemoryPOSRepository extends InMemoryRepositoryWithLongPK<POS> implements POSRepository {

    @Override
    public Iterable<POS> findByState(POSState s) {
        ArrayList<POS> list_pos = new ArrayList<>();
        list_pos = (ArrayList<POS>) match(pos -> pos.state().equalsIgnoreCase(s.toString()));
        return list_pos;
    }

    @Override
    public Iterable<POS> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<POS> findOne(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
