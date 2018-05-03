/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.movement.Movement;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author filip
 */
public interface MovementRepository extends DataRepository<Movement, Long>{
    
    public Iterable<Movement> allCafeteriaUserMovements(CafeteriaUser user);
    
    public void addBookingMovement(Money price);
    
}