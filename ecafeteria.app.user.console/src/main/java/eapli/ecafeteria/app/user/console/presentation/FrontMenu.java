/*
 * 1 * To change this license header, choose License Headers in Project
 * Properties. To change this template file, choose Tools | Templates and open
 * the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation;

import eapli.cafeteria.app.common.console.presentation.ExitWithMessageAction;
import eapli.cafeteria.app.common.console.presentation.authz.LoginAction;
import eapli.ecafeteria.app.user.console.presentation.authz.SignupRequestAction;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.framework.actions.IfThenAction;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.ShowUiAction;
import eapli.framework.presentation.console.VerticalMenuRenderer;

/**
 * @author Paulo Gandra Sousa
 */
public class FrontMenu extends AbstractUI {

    private static final int EXIT_OPTION = 0;

    private static final int LOGIN_OPTION = 1;
    private static final int SIGNUP_OPTION = 2;

    public FrontMenu() {
    }

    @Override
    public boolean show() {
        drawFormTitle();
        return doShow();
    }

    /**
     * @return true if the user selected the exit option
     */
    @Override
    public boolean doShow() {
        final Menu menu = new Menu();
        menu.add(new MenuItem(LOGIN_OPTION, "Login",
                new IfThenAction(new LoginAction(ActionRight.SELECT_MEAL), new ShowUiAction(new MainMenu()))));
        menu.add(new MenuItem(SIGNUP_OPTION, "Sign up", new SignupRequestAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Exit", new ExitWithMessageAction()));

        final MenuRenderer renderer = new VerticalMenuRenderer(menu);
        return renderer.show();
    }

    @Override
    public String headline() {
        return "eCAFETERIA";
    }
}
