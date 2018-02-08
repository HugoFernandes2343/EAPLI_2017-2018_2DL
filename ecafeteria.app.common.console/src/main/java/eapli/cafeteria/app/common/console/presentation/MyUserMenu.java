package eapli.cafeteria.app.common.console.presentation;

import eapli.cafeteria.app.common.console.presentation.authz.LoginAction;
import eapli.cafeteria.app.common.console.presentation.authz.LogoutAction;
import eapli.framework.actions.ReturnAction;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.ShowMessageAction;

public class MyUserMenu extends Menu {

    private static final int EXIT_OPTION = 0;

    // MY USER
    private static final int CHANGE_PASSWORD_OPTION = 1;
    private static final int LOGIN_OPTION = 2;
    private static final int LOGOUT_OPTION = 3;

    public MyUserMenu() {
        super("My account >");
        buildMyUserMenu();
    }

    private void buildMyUserMenu() {
        add(new MenuItem(CHANGE_PASSWORD_OPTION, "Change password", new ShowMessageAction("Not implemented yet")));
        //TODO need to know if there are some specific action rights to check, e.g., for the POS or USER app
        add(new MenuItem(LOGIN_OPTION, "Change user (Login)", new LoginAction()));
        add(new MenuItem(LOGOUT_OPTION, "Logout", new LogoutAction()));
        add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
    }
}
