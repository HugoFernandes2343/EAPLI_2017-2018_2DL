/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.app.backoffice.console;

import eapli.cafeteria.app.common.console.presentation.authz.LoginAction;
import eapli.ecafeteria.app.backoffice.console.presentation.MainMenu;

/**
 *
 * @author Paulo Gandra Sousa
 */
@SuppressWarnings("squid:S106")
public final class ECafeteriaBackoffice {

    /**
     * Empty constructor is private to avoid instantiation of this class.
     */
    private ECafeteriaBackoffice() {
    }
 
            
            
    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        System.out.println("=====================================");
        System.out.println("eCafeteria Back Office");
        System.out.println("(C) 2016, 2017, 2018");
        System.out.println("=====================================");

        // login and go to main menu
        if (new LoginAction().execute()) {
            final MainMenu menu = new MainMenu();
            menu.mainLoop();
        }
    }
}
