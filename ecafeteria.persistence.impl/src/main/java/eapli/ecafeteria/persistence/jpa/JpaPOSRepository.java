/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.domain.pos.POSState;
import eapli.ecafeteria.persistence.POSRepository;
import javax.persistence.TypedQuery;

/**
 *
 * @author hugod
 */
public class JpaPOSRepository extends CafeteriaJpaRepositoryBase<POS, Long> implements POSRepository{
    
    @Override
    public Iterable<POS> findOpenned(POSState ps){
        final TypedQuery query = entityManager().createQuery("Select p from POS p WHERE p.shift =: state",POS.class);
        query.setParameter("state",ps);
        return query.getResultList();
    }
}
