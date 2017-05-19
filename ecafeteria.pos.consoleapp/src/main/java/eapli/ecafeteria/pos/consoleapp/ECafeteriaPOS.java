/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.pos.consoleapp;

import eapli.cafeteria.consoleapp.presentation.authz.LoginAction;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.pos.consoleapp.presentation.MainMenu;

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

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {

        // login and go to main menu
        // TODO should provide three attempts
        if (new LoginAction(ActionRight.SALE).execute()) {
            final MainMenu menu = new MainMenu();
            menu.mainLoop();
        }
    }
}
