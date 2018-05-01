/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.domain.pos.POSState;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author hugod
 */
public interface POSRepository extends DataRepository<POS, Long>{
    
     public Iterable<POS> findOpenned(POSState ps);
}
