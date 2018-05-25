/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.domain.pos.POSState;
import eapli.ecafeteria.persistence.POSRepository;
import javax.persistence.*;

/**
 *
 * @author Norberto Sousa - 1120608 && Hugo Fernandes 1161155
 */
public class JpaPOSRepository extends CafeteriaJpaRepositoryBase<POS, Long> implements POSRepository {

    @Override
    public Iterable<POS> findByState(POSState s) {
        final TypedQuery query = entityManager().createQuery("SELECT p FROM POS p WHERE p.currentState=:st", this.entityClass);
        query.setParameter("st", s);
        return query.getResultList();
    }

    @Override
    public POS saveWithDelete(POS p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
