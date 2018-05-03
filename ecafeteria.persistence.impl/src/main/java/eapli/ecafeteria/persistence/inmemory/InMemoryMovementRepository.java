/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.movement.Booking;
import eapli.ecafeteria.persistence.MovementRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import eapli.ecafeteria.domain.movement.Movement;
import eapli.ecafeteria.domain.movement.Recharge;
import eapli.framework.domain.money.Money;
/**
 *
 * @author filip
 */
public class InMemoryMovementRepository extends InMemoryRepository<Movement, Long> implements MovementRepository{

    @Override
    public Iterable<Movement> allCafeteriaUserMovements(MecanographicNumber user) {
        return match(e -> e.madeBy().sameAs(user));
    }

    @Override
    protected Long newKeyFor(Movement entity) {
         return entity.id();
    }

    @Override
    public void addBookingMovement(Booking booking) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
