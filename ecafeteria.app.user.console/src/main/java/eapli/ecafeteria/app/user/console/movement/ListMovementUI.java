/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.movement;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.application.cafeteriauser.ListMovementService;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.movement.Movement;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Optional;

/**
 *
 * @author João Vieira
 */
public class ListMovementUI extends AbstractUI
{
    private final ListMovementService service = new ListMovementService();
    private final CafeteriaUserService userService = new CafeteriaUserService();

    @Override
    protected boolean doShow() {
        System.out.println("+= Your Movements =+");
        
        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(AuthorizationService.session().authenticatedUser().id());
        Iterable<Movement> movementList =  service.listMovements(user.get());
        
        for(Movement m : movementList){
            System.out.println(m);
        }
        
        Console.readLine("");
        
        return true;
    }

    @Override
    public String headline() {
        return "Consult Movements";
    }
    
}
