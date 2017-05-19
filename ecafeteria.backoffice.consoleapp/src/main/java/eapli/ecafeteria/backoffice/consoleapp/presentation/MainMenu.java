/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.backoffice.consoleapp.presentation;

import eapli.cafeteria.consoleapp.presentation.ExitWithMessageAction;
import eapli.cafeteria.consoleapp.presentation.MyUserMenu;
import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.cafeteria.ListOrganicUnitsController;
import eapli.ecafeteria.backoffice.consoleapp.presentation.authz.AcceptRefuseSignupRequestAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.authz.AddUserUI;
import eapli.ecafeteria.backoffice.consoleapp.presentation.authz.DeactivateUserAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.authz.ListUsersAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria.AddOrganicUnitUI;
import eapli.ecafeteria.backoffice.consoleapp.presentation.cafeteria.OrganicUnitPrinter;
import eapli.ecafeteria.backoffice.consoleapp.presentation.kitchen.ListMaterialAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.kitchen.RegisterMaterialAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.ActivateDeactivateDishAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.ActivateDeactivateDishTypeAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.ChangeDishNutricionalInfoAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.ChangeDishPriceAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.ChangeDishTypeAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.ListDishAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.ListDishTypeAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.RegisterDishAction;
import eapli.ecafeteria.backoffice.consoleapp.presentation.meals.RegisterDishTypeAction;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.framework.actions.ReturnAction;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.HorizontalMenuRenderer;
import eapli.framework.presentation.console.ListUI;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.ShowMessageAction;
import eapli.framework.presentation.console.ShowVerticalSubMenuAction;
import eapli.framework.presentation.console.SubMenu;
import eapli.framework.presentation.console.VerticalMenuRenderer;
import eapli.framework.presentation.console.VerticalSeparator;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

    private static final int EXIT_OPTION = 0;

    // USERS
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DEACTIVATE_USER_OPTION = 3;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;

    // ORGANIC UNITS
    private static final int ADD_ORGANIC_UNIT_OPTION = 1;
    private static final int LIST_ORGANIC_UNIT_OPTION = 2;

    // SETTINGS
    private static final int SET_KITCHEN_ALERT_LIMIT_OPTION = 1;
    private static final int SET_USER_ALERT_LIMIT_OPTION = 2;

    // DISH TYPES
    private static final int DISH_TYPE_REGISTER_OPTION = 1;
    private static final int DISH_TYPE_LIST_OPTION = 2;
    private static final int DISH_TYPE_CHANGE_OPTION = 3;
    private static final int DISH_TYPE_ACTIVATE_DEACTIVATE_OPTION = 4;

    // DISHES
    private static final int DISH_REGISTER_OPTION = 5;
    private static final int DISH_LIST_OPTION = 6;
    private static final int DISH_ACTIVATE_DEACTIVATE_OPTION = 7;
    private static final int DISH_CHANGE_OPTION = 8;

    // DISH PROPERTIES
    private static final int CHANGE_DISH_NUTRICIONAL_INFO_OPTION = 1;
    private static final int CHANGE_DISH_PRICE_OPTION = 2;

    // MATERIALS
    private static final int MATERIAL_REGISTER_OPTION = 1;
    private static final int MATERIAL_LIST_OPTION = 2;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int USERS_OPTION = 2;
    private static final int ORGANIC_UNITS_OPTION = 3;
    private static final int SETTINGS_OPTION = 4;
    private static final int DISH_TYPES_OPTION = 5;
    private static final int TRACEABILITY_OPTION = 6;

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
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu);
        } else {
            renderer = new VerticalMenuRenderer(menu);
        }
        return renderer.show();
    }

    @Override
    public String headline() {
        return "eCafeteria Back Office [@" + Application.session().session().authenticatedUser().id() + "]";
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.add(new SubMenu(MY_USER_OPTION, myUserMenu, new ShowVerticalSubMenuAction(myUserMenu)));

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.add(VerticalSeparator.separator());
        }

        if (Application.session().session().authenticatedUser().isAuthorizedTo(ActionRight.ADMINISTER)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.add(new SubMenu(USERS_OPTION, usersMenu, new ShowVerticalSubMenuAction(usersMenu)));
            final Menu organicUnitsMenu = buildOrganicUnitsMenu();
            mainMenu.add(new SubMenu(ORGANIC_UNITS_OPTION, organicUnitsMenu,
                    new ShowVerticalSubMenuAction(organicUnitsMenu)));
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.add(new SubMenu(SETTINGS_OPTION, settingsMenu, new ShowVerticalSubMenuAction(settingsMenu)));
        }
        if (Application.session().session().authenticatedUser().isAuthorizedTo(ActionRight.MANAGE_KITCHEN)) {
            final Menu kitchenMenu = buildKitchenMenu();
            mainMenu.add(new SubMenu(TRACEABILITY_OPTION, kitchenMenu, new ShowVerticalSubMenuAction(kitchenMenu)));
        }
        if (Application.session().session().authenticatedUser().isAuthorizedTo(ActionRight.MANAGE_MENUS)) {
            final Menu myDishTypeMenu = buildDishMenu();
            mainMenu.add(new SubMenu(DISH_TYPES_OPTION, myDishTypeMenu, new ShowVerticalSubMenuAction(myDishTypeMenu)));
        }
        if (Application.session().session().authenticatedUser().isAuthorizedTo(ActionRight.SALE)) {
            // TODO
        }

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.add(VerticalSeparator.separator());
        }

        mainMenu.add(new MenuItem(EXIT_OPTION, "Exit", new ExitWithMessageAction()));

        return mainMenu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.add(new MenuItem(SET_KITCHEN_ALERT_LIMIT_OPTION, "Set kitchen alert limit",
                new ShowMessageAction("Not implemented yet")));
        menu.add(new MenuItem(SET_USER_ALERT_LIMIT_OPTION, "Set users' alert limit",
                new ShowMessageAction("Not implemented yet")));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildOrganicUnitsMenu() {
        final Menu menu = new Menu("Organic units >");

        menu.add(new MenuItem(ADD_ORGANIC_UNIT_OPTION, "Add Organic Unit", () -> {
            return new AddOrganicUnitUI().show();
        }));
        menu.add(new MenuItem(LIST_ORGANIC_UNIT_OPTION, "List Organic Unit", () -> {
            // example of using the generic list ui from the framework
            new ListUI<>(new ListOrganicUnitsController().listOrganicUnits(), new OrganicUnitPrinter(), "Organic Unit")
                    .show();
            return false;
        }));
        // TODO add other options for Organic Unit management
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users >");

        menu.add(new MenuItem(ADD_USER_OPTION, "Add User", () -> {
            return new AddUserUI().show();
        }));
        menu.add(new MenuItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction()));
        menu.add(new MenuItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction()));
        menu.add(new MenuItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request",
                new AcceptRefuseSignupRequestAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildDishMenu() {
        final Menu menu = new Menu("Dishes >");

        menu.add(new MenuItem(DISH_TYPE_REGISTER_OPTION, "Register new Dish Type", new RegisterDishTypeAction()));
        menu.add(new MenuItem(DISH_TYPE_LIST_OPTION, "List all Dish Type", new ListDishTypeAction()));
        menu.add(new MenuItem(DISH_TYPE_CHANGE_OPTION, "Change Dish Type description", new ChangeDishTypeAction()));
        menu.add(new MenuItem(DISH_TYPE_ACTIVATE_DEACTIVATE_OPTION, "Activate/Deactivate Dish Type",
                new ActivateDeactivateDishTypeAction()));

        menu.add(new MenuItem(DISH_REGISTER_OPTION, "Register new Dish", new RegisterDishAction()));
        menu.add(new MenuItem(DISH_LIST_OPTION, "List all Dish", new ListDishAction()));
        menu.add(new MenuItem(DISH_ACTIVATE_DEACTIVATE_OPTION, "Activate/Deactivate Dish",
                new ActivateDeactivateDishAction()));
        final Menu changeDishMenu = buildChangeDishMenu();
        menu.add(new MenuItem(DISH_CHANGE_OPTION, "Change Dish Information",
                new ShowVerticalSubMenuAction(changeDishMenu)));

        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildKitchenMenu() {
        final Menu menu = new Menu("Traceability >");

        menu.add(new MenuItem(MATERIAL_REGISTER_OPTION, "Register new material", new RegisterMaterialAction()));
        menu.add(new MenuItem(MATERIAL_LIST_OPTION, "List all materials", new ListMaterialAction()));

        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildChangeDishMenu() {
        final Menu menu = new Menu("Change Dish >");

        menu.add(new MenuItem(CHANGE_DISH_NUTRICIONAL_INFO_OPTION, "Change Nutricional Info",
                new ChangeDishNutricionalInfoAction()));
        menu.add(new MenuItem(CHANGE_DISH_PRICE_OPTION, "Change Price", new ChangeDishPriceAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }
}
