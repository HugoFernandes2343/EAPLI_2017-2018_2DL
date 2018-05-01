/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.Movement;
import eapli.ecafeteria.persistence.MovementRepository;
import eapli.framework.domain.money.Money;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author filip
 */
public class JpaMovementRepository extends CafeteriaJpaRepositoryBase<Movement, Long> implements MovementRepository{

    @Override
    public Iterable<Movement> allCafeteriaUserMovements(CafeteriaUser user) {
        final TypedQuery query = entityManager().createQuery("Select m from Movement m WHERE m.user =: number", Movement.class);
        query.setParameter("number", user.id());
        return query.getResultList();
    }

    @Override
    public boolean addBookingMovement(Money price) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
