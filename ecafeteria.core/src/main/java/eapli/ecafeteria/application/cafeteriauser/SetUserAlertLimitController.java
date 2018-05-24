/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.cafeteriauser;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.AccountCardRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.Console;
import java.util.Optional;

/**
 *
 * @author João Vieira
 */
public class SetUserAlertLimitController implements Controller {

    private final CafeteriaUserService userService = new CafeteriaUserService();
    private final CafeteriaUserRepository userRepo = PersistenceContext.repositories().cafeteriaUsers();

    public void changeUserAlertLimit(double value) throws DataConcurrencyException, DataIntegrityViolationException {
        try {
            Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(AuthorizationService.session().authenticatedUser().id());
            if (user.get().accountCard().changeBalanceLimit(value)) {
                userRepo.save(user.get());
            }
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Console.readLine("\nErro!");
        }
    }

    public double getUserAlertLimit() {
        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(AuthorizationService.session().authenticatedUser().id());
        return user.get().accountCard().balanceLimit().limit().amount();
    }
}
