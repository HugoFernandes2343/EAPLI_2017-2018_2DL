/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.Movement;
import eapli.ecafeteria.persistence.MovementRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import eapli.ecafeteria.domain.cafeteriauser.Movement;
/**
 *
 * @author filip
 */
public class InMemoryMovementRepository extends InMemoryRepository<Movement, Long> implements MovementRepository{

    @Override
    public Iterable<Movement> allCafeteriaUserMovements(CafeteriaUser user) {
        return match(e -> e.sameAs(user));
    }

    @Override
    protected Long newKeyFor(Movement entity) {
         return entity.id();
    }
    
}
