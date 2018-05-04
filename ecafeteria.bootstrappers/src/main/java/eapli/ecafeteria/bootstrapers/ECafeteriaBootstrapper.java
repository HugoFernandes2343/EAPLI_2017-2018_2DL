package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.UserSession;
import eapli.framework.actions.Action;
import eapli.framework.util.Strings;
import java.util.HashSet;
import java.util.Set;

/**
 * eCafeteria Bootstrapping data app
 *
 */
public class ECafeteriaBootstrapper implements Action {

    @Override
    public boolean execute() {
        // declare bootstrap actions
        final Action[] actions = {new MasterUsersBootstrapper(), new BackofficeUsersBootstrapper(), new DishTypesBootstrapper(),
            new CafeteriaUserBootstrapper(), new DishBootstrapper(), new MaterialsBootstrapper(), new MenuBootstrapper(), new MealBootstrapper()};

        authenticateSuperUser();

        // execute all bootstrapping
        boolean ret = true;
        for (final Action boot : actions) {
            System.out.println("Bootstrapping " + nameOfEntity(boot) + "...");
            ret &= boot.execute();
        }
        return ret;
    }

    private void authenticateSuperUser() {
        // authenticate a super user to be able to register new users, ...
        // in this case we will inject the session but we shouldn't do this
        final Set<RoleType> roles = new HashSet<>();
        roles.add(RoleType.ADMIN);
        roles.add(RoleType.MENU_MANAGER);
        roles.add(RoleType.KITCHEN_MANAGER);
        final UserSession adminSession = new UserSession(
                new SystemUser("poweruser", "poweruserA1", "joe", "doe", "joe@email.org", roles));
        AuthorizationService.setSession(adminSession);
    }

    private String nameOfEntity(final Action boot) {
        final String name = boot.getClass().getSimpleName();
        return Strings.left(name, name.length() - "Bootstrapper".length());
    }
}
