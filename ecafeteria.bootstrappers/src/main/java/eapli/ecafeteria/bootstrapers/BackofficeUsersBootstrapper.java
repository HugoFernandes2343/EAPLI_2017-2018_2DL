/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.authz.RoleType;
import eapli.framework.actions.Action;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Paulo Gandra Sousa
 */
public class BackofficeUsersBootstrapper extends UsersBootstrapperBase implements Action {

    @SuppressWarnings("squid:S2068")
    private static final String PASSWORD1 = "Password1";

    @Override
    public boolean execute() {
        registerCashier("cashier", PASSWORD1, "Johny", "Cash", "johny.doe@emai.l.com");
        registerKitchenManager("kitchen", PASSWORD1, "Oven", "Stove", "Oven.and.stove@emai.l.com");
        registerMenuManager("chef", PASSWORD1, "Master", "Chef", "master.chef@emai.l.com");
        return true;
    }

    private void registerCashier(final String username, final String password, final String firstName,
            final String lastName, final String email) {
        final Set<RoleType> roles = new HashSet<>();
        roles.add(RoleType.CASHIER);

        registerUser(username, password, firstName, lastName, email, roles);
    }

    private void registerKitchenManager(final String username, final String password, final String firstName,
            final String lastName, final String email) {
        final Set<RoleType> roles = new HashSet<>();
        roles.add(RoleType.KITCHEN_MANAGER);

        registerUser(username, password, firstName, lastName, email, roles);
    }

    private void registerMenuManager(final String username, final String password, final String firstName,
            final String lastName, final String email) {
        final Set<RoleType> roles = new HashSet<>();
        roles.add(RoleType.MENU_MANAGER);

        registerUser(username, password, firstName, lastName, email, roles);
    }
}
