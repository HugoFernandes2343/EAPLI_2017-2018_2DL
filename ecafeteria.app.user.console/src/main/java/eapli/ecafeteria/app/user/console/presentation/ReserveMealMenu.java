/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation;

import eapli.ecafeteria.app.user.console.booking.ReserveMealAction;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.framework.actions.ReturnAction;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.ShowMessageAction;
import eapli.framework.presentation.console.VerticalMenuRenderer;

/**
 *
 * @author João Vieira
 */
public class ReserveMealMenu extends CafeteriaUserBaseUI {
    
    private static final int EXIT_OPTION = 0;
    
    // BOOKINGS MENU
    private static final int LIST_MENUS_OPTION = 1;
    private static final int BOOK_A_MEAL_OPTION = 2;

    public ReserveMealMenu() {
    }

    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }

    @Override
    protected boolean doShow() {
        boolean wantsToExit = false;
        do {
            drawFormTitle();
            final Menu menu = buildMenu();
            final MenuRenderer renderer = new VerticalMenuRenderer(menu);
            System.out.println("\n>> " + menu.title());
            wantsToExit = renderer.show();
        } while (!wantsToExit);
        return wantsToExit;
    }

    private Menu buildMenu() {
        final Menu menu = new Menu("Book a Meal");
        menu.add(new MenuItem(LIST_MENUS_OPTION, "List Menus", new ShowMessageAction("Not implemented yet")));
        menu.add(new MenuItem(BOOK_A_MEAL_OPTION, "Book a Meal", new ReserveMealAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Exit", new ReturnAction()));
        return menu;
    }
}
