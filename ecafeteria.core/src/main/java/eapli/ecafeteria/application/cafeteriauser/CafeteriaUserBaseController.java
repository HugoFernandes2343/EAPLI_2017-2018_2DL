/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application.cafeteriauser;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.application.Controller;
import eapli.framework.domain.money.Money;
import java.util.Optional;

/**
 *
 * @author mcn
 */
public class CafeteriaUserBaseController implements Controller {
    
    private final CafeteriaUserService userService = new CafeteriaUserService();
    private final ListMovementService listMovementService = new ListMovementService();

    public Money balance() {
        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(AuthorizationService.session().authenticatedUser().id());
        
        // TODO get the actual balance of the user
        return Money.euros(listMovementService.calculateBalance(user.get()).amount());
    }
}
