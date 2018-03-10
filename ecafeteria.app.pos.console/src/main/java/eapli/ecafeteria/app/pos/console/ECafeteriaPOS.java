/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.app.pos.console;

import eapli.cafeteria.app.common.console.presentation.authz.LoginAction;
import eapli.ecafeteria.app.pos.console.presentation.MainMenu;
import eapli.ecafeteria.domain.authz.ActionRight;

/**
 *
 * @author Paulo Gandra Sousa
 */
public final class ECafeteriaPOS {

    /**
     * Empty constructor is private to avoid instantiation of this class.
     */
    private ECafeteriaPOS() {
    }

    public static void main(final String[] args) {
        System.out.println("=====================================");
        System.out.println("eCafeteria POS");
        System.out.println("(C) 2016, 2017, 2018");
        System.out.println("=====================================");

        // login and go to main menu
        if (new LoginAction(ActionRight.SALE).execute()) {
            final MainMenu menu = new MainMenu();
            menu.mainLoop();
        }
    }

}
