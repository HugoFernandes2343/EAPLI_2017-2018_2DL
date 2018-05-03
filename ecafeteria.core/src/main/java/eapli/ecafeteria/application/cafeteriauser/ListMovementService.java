/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.cafeteriauser;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.Movement;
import eapli.ecafeteria.persistence.MovementRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.domain.money.Money;

/**
 *
 * @author João Vieira
 */
public class ListMovementService {
    private final MovementRepository movementRepository = PersistenceContext.repositories().movements();

    public Money calculateBalance(CafeteriaUser user) {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS.SELECT_MEAL);

        Iterable<Movement> list = this.movementRepository.allCafeteriaUserMovements(user);
        
        double value = 0;
        
        for(Movement m : list){
            value += m.value();
        }
        return Money.euros(value);
    }

}
