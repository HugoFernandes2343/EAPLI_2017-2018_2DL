/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation;

import eapli.cafeteria.app.common.console.presentation.MyUserMenu;
import eapli.ecafeteria.app.user.console.booking.*;
import eapli.ecafeteria.app.user.console.complaint.RegisterComplaintAction;
import eapli.ecafeteria.app.user.console.movement.ConsultMovementsAction;
import eapli.ecafeteria.app.user.console.movement.SetUserAlertLimitAction;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.framework.actions.ReturnAction;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.ShowVerticalSubMenuAction;
import eapli.framework.presentation.console.SubMenu;
import eapli.framework.presentation.console.VerticalMenuRenderer;
import eapli.framework.presentation.console.VerticalSeparator;
import eapli.ecafeteria.app.user.console.presentation.consultMenu.ConsultMenuAction;

/**
 * @author Paulo Gandra Sousa
 */
class MainMenu extends CafeteriaUserBaseUI {

    private static final int EXIT_OPTION = 0;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int BOOKINGS_OPTION = 2;
    private static final int ACCOUNT_OPTION = 3;
    private static final int COMPLAINT_OPTION = 4;
    private static final int RATINGS_AND_CALORIES_OPTION = 5;
    private static final int SETTINGS_OPTION = 6;

    // BOOKINGS MENU
    private static final int BOOK_A_MEAL_OPTION = 1;
    private static final int CONSULT_RESERVATIONS = 3;
    private static final int CONSULT_MENU = 4;
    private static final int CANCEL_RESERVATION_OPTION = 5;

    //RESERVATIONS SUB_MENU
    private static final int CONSULT_NEXT_RESERVATION = 1;
    private static final int LIST_NEXT_RESERVATIONS = 2;
    private static final int LIST_PREVIOUS_PURCHASES = 3;

    // ACCOUNT MENU
    private static final int LIST_MOVEMENTS_OPTION = 1;

    //COMPLAINTS MENU
    private static final int FILE_COMPLAINTS = 1;
    
    //RATINGS AND CALORIES MENU
    private static final int REGISTER_MEAL_RATING = 1;

    // SETTINGS
    private static final int SET_USER_ALERT_LIMIT_OPTION = 1;

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
        final Menu menu = buildMainMenu();
        final MenuRenderer renderer = new VerticalMenuRenderer(menu);
        return renderer.show();
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.add(new SubMenu(MY_USER_OPTION, myUserMenu, new ShowVerticalSubMenuAction(myUserMenu)));

        mainMenu.add(VerticalSeparator.separator());

        final Menu bookingsMenu = buildBookingsMenu();
        mainMenu.add(new SubMenu(BOOKINGS_OPTION, bookingsMenu, new ShowVerticalSubMenuAction(bookingsMenu)));

        mainMenu.add(VerticalSeparator.separator());

        final Menu accountMenu = buildAccountMenu();
        mainMenu.add(new SubMenu(ACCOUNT_OPTION, accountMenu, new ShowVerticalSubMenuAction(accountMenu)));

        mainMenu.add(VerticalSeparator.separator());

        final Menu complaintMenu = buildComplaintMenu();
        mainMenu.add(new SubMenu(COMPLAINT_OPTION, complaintMenu, new ShowVerticalSubMenuAction(complaintMenu)));

        mainMenu.add(VerticalSeparator.separator());

        final Menu ratingsAndCaloriesMenu = buildRatingsAndCaloriesMenu();
        mainMenu.add(new SubMenu(RATINGS_AND_CALORIES_OPTION, ratingsAndCaloriesMenu, new ShowVerticalSubMenuAction(ratingsAndCaloriesMenu)));
        
        mainMenu.add(VerticalSeparator.separator());
        
        final Menu settingsMenu = buildAdminSettingsMenu();
        mainMenu.add(new SubMenu(SETTINGS_OPTION, settingsMenu, new ShowVerticalSubMenuAction(settingsMenu)));

        mainMenu.add(VerticalSeparator.separator());

        mainMenu.add(new MenuItem(EXIT_OPTION, "Exit", new ExitWithMessageAction()));

        return mainMenu;
    }

    /**
     * Builds the user account information such as account movements
     *
     * @return
     */
    private Menu buildAccountMenu() {
        final Menu menu = new Menu("Account");
        menu.add(new MenuItem(LIST_MOVEMENTS_OPTION, "List movements", new ConsultMovementsAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
        return menu;
    }

    /**
     * Builds the user booking data regarding booking meals and consulting Reservations
     *
     * @return
     */
    private Menu buildBookingsMenu() {
        final Menu menu = new Menu("Bookings");
        menu.add(new MenuItem(BOOK_A_MEAL_OPTION, "Book a meal", new ReserveMealAction()));
        final Menu menuConsult = buildConsultReservationsMenu();
        menu.add(new SubMenu(CONSULT_RESERVATIONS, menuConsult, new ShowVerticalSubMenuAction(buildConsultReservationsMenu())));
        menu.add(new MenuItem(CONSULT_MENU, "Consult menu", new ConsultMenuAction()));
        menu.add(new MenuItem(CANCEL_RESERVATION_OPTION, "Cancel Reservation", new CancelReservationAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
        return menu;
    }

    /**
     * Builds the sub menu that displays options to show previous and following reservations
     *
     * @return
     */
    private Menu buildConsultReservationsMenu() {
        final Menu menu = new Menu("Consult Reservations");

        menu.add(new MenuItem(CONSULT_NEXT_RESERVATION, "Next Reservation", new NextReservationAction()));
        menu.add(new MenuItem(LIST_NEXT_RESERVATIONS, "Next days", new NextDaysAction()));
        menu.add(new MenuItem(LIST_PREVIOUS_PURCHASES, "Previous purchases", new PreviousPurchasesAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }
    
    private Menu buildRatingsAndCaloriesMenu() {
        final Menu menu = new Menu("Ratings and Calories >");
        menu.add(new MenuItem(REGISTER_MEAL_RATING, "Register meal rating", new RegisterMealRatingAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
        return menu;
    }

    /**
     * Admin only menu
     *
     * @return
     */
    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.add(new MenuItem(SET_USER_ALERT_LIMIT_OPTION, "Set users' alert limit",
                new SetUserAlertLimitAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildComplaintMenu(){
        final Menu menu = new Menu("File Complaints >");

        menu.add(new MenuItem(FILE_COMPLAINTS, "Create a complaint",
                new RegisterComplaintAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }
}
