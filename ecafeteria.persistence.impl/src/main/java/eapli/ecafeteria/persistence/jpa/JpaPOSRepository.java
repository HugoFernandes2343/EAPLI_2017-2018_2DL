/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.persistence.POSRepository;
import javax.persistence.*;

/**
 *
 * @author hugod
 */
public class JpaPOSRepository extends CafeteriaJpaRepositoryBase<POS, Long> implements POSRepository {

    @Override
    public Iterable<POS> findOpenned(POS.POSState s) {
        final TypedQuery query = entityManager().createQuery("SELECT p FROM POS p WHERE p.state = :st", this.entityClass);
        query.setParameter("st", s);
        return query.getResultList();
    }
}
