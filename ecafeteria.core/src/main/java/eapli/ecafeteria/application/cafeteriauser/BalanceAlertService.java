/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.cafeteriauser;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.cafeteriauser.AccountCard;
import eapli.ecafeteria.domain.cafeteriauser.BalanceLimit;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.domain.money.Money;
import java.util.Optional;

/**
 *
 * @author filip
 */
public class BalanceAlertService {
    
    private final ListMovementService movementService = new ListMovementService();
    private final CafeteriaUserService userService = new CafeteriaUserService();
    
    
    public boolean verifyAlert(){
        
        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(AuthorizationService.session().authenticatedUser().id());
        CafeteriaUser cafUser = user.get();
        BalanceLimit limit = cafUser.accountCard().balanceLimit();
        
        return calculate(limit, cafUser);
    }

    private boolean calculate(BalanceLimit limit, CafeteriaUser user) {
        Money balance = movementService.calculateBalance(user);
        
        return balance.lessThan(limit.limit());
    }
    
}
