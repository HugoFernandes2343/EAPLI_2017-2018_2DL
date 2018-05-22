/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.cafeteriauser;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.movement.Movement;
import eapli.framework.application.Controller;
import java.util.Optional;

/**
 *
 * @author filip
 */
public class ConsultMovementsController implements Controller{
    
    private final ListMovementService service = new ListMovementService();
    private final CafeteriaUserService userService = new CafeteriaUserService();
    
    public ConsultMovementsController(){
        
    }
    
    public Iterable<Movement> findUserMovements(){
        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(AuthorizationService.session().authenticatedUser().id());
        Iterable<Movement> movementList =  service.listUserMovements(user.get());
        
        return movementList;
    }
    
}
