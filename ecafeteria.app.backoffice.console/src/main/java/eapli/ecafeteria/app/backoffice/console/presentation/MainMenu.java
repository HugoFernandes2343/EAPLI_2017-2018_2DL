/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation;

import eapli.cafeteria.app.common.console.presentation.MyUserMenu;
import eapli.ecafeteria.Application;
import eapli.ecafeteria.app.backoffice.console.presentation.authz.AddUserUI;
import eapli.ecafeteria.app.backoffice.console.presentation.authz.AlertManagementAction;
import eapli.ecafeteria.app.backoffice.console.presentation.authz.DeactivateUserAction;
import eapli.ecafeteria.app.backoffice.console.presentation.authz.ListUsersAction;
import eapli.ecafeteria.app.backoffice.console.presentation.cafeteriauser.AcceptRefuseSignupRequestAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ActivateDeactivateDishAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ActivateDeactivateDishTypeAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ChangeDishNutricionalInfoAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ChangeDishPriceAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ChangeDishTypeAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.DishAllergernsListAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ListDishAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.ListDishTypeAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.RegisterDishAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.RegisterDishTypeAction;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.reporting.ReportDishesPerCaloricCategoryAsTuplesUI;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.reporting.ReportDishesPerCaloricCategoryUI;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.reporting.ReportDishesPerDishTypeUI;
import eapli.ecafeteria.app.backoffice.console.presentation.dishes.reporting.ReportHighCaloriesDishesUI;
import eapli.ecafeteria.app.backoffice.console.presentation.dishesviadto.ListDishViaDTOUI;
import eapli.ecafeteria.app.backoffice.console.presentation.dishesviadto.RegisterDishViaDTOUI;
import eapli.ecafeteria.app.backoffice.console.presentation.kitchen.*;
import eapli.ecafeteria.app.backoffice.console.presentation.menu.*;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.framework.actions.ReturnAction;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.HorizontalMenuRenderer;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.ShowVerticalSubMenuAction;
import eapli.framework.presentation.console.SubMenu;
import eapli.framework.presentation.console.VerticalMenuRenderer;
import eapli.framework.presentation.console.VerticalSeparator;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends KitchenAlertUI{

    private static final int EXIT_OPTION = 0;

    // USERS
    private static final int ADD_USER_OPTION = 1;
    private static final int LIST_USERS_OPTION = 2;
    private static final int DEACTIVATE_USER_OPTION = 3;
    private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;

    // SETTINGS
    private static final int SET_KITCHEN_ALERT_LIMIT_OPTION = 1;

    // DISH TYPES
    private static final int DISH_TYPE_REGISTER_OPTION = 1;
    private static final int DISH_TYPE_LIST_OPTION = 2;
    private static final int DISH_TYPE_CHANGE_OPTION = 3;
    private static final int DISH_TYPE_ACTIVATE_DEACTIVATE_OPTION = 4;

    // DISHES
    private static final int DISH_REGISTER_OPTION = 5;
    private static final int DISH_LIST_OPTION = 6;
    private static final int DISH_REGISTER_DTO_OPTION = 7;
    private static final int DISH_LIST_DTO_OPTION = 8;
    private static final int DISH_ACTIVATE_DEACTIVATE_OPTION = 9;
    private static final int DISH_CHANGE_OPTION = 10;
    private static final int DISH_ALLERGEN = 11;

    // DISH PROPERTIES
    private static final int CHANGE_DISH_NUTRICIONAL_INFO_OPTION = 1;
    private static final int CHANGE_DISH_PRICE_OPTION = 2;

    // RESERVATIONS
    private static final int CHECK_RESERVATIONS_OPTION = 1;
    
    // MATERIALS
    private static final int MATERIAL_REGISTER_OPTION = 1;
    private static final int MATERIAL_LIST_OPTION = 2;
    private static final int ELABORATE_MEAL_PLAN_OPTION = 3;
    private static final int FIND_MEALS_BY_LOT_OPTION = 4;


    // REPORTING
    private static final int REPORTING_DISHES_PER_DISHTYPE_OPTION = 1;
    private static final int REPORTING_HIGH_CALORIES_DISHES_OPTION = 2;
    private static final int REPORTING_DISHES_PER_CALORIC_CATEGORY_OPTION = 3;

    // MEALS
    private static final int REGISTER_MENU_OPTION = 1;
    private static final int PUBLISH_MENU_OPTION = 2;
    private static final int COPY_MENU_OPTION = 3;
    private static final int PUBLISH_MEAL_OPTION = 4;
    private static final int CONSULT_MEALS_RATING = 5;
    private static final int REGISTER_USED_LOT_OPTION = 6;

    // MAIN MENU
    private static final int MY_USER_OPTION = 1;
    private static final int USERS_OPTION = 2;
    private static final int CAFETERIA_SHIFT_OPTION = 3;
    private static final int SETTINGS_OPTION = 4;
    private static final int DISH_TYPES_OPTION = 5;
    private static final int TRACEABILITY_OPTION = 6;
    private static final int REPORTING_DISHES_OPTION = 7;
    private static final int MEALS_MENU_OPTION = 8;
    private static final int RESERVATIONS_MENU_OPTION = 9;
    
    //ELABORATE MENU
    private static final int CREATE_MEAL_PLAN = 1;
    private static final int EDIT_MEAL_PLAN = 2;
    private static final int CLOSE_MEAL_PLAN = 3;
    private static final int TESTE = 4;
    private static final int TESTE2 = 5;

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
        return "eCafeteria Back Office [@" + AuthorizationService.session().authenticatedUser().id()
                + "]";
    }

    private Menu buildMainMenu() {
        final Menu mainMenu = new Menu();

        final Menu myUserMenu = new MyUserMenu();
        mainMenu.add(
                new SubMenu(MY_USER_OPTION, myUserMenu, new ShowVerticalSubMenuAction(myUserMenu)));

        if (!Application.settings().isMenuLayoutHorizontal()) {
            mainMenu.add(VerticalSeparator.separator());
        }

        if (AuthorizationService.session().authenticatedUser()
                .isAuthorizedTo(ActionRight.ADMINISTER)) {
            final Menu usersMenu = buildUsersMenu();
            mainMenu.add(
                    new SubMenu(USERS_OPTION, usersMenu, new ShowVerticalSubMenuAction(usersMenu)));
            final Menu settingsMenu = buildAdminSettingsMenu();
            mainMenu.add(new SubMenu(SETTINGS_OPTION, settingsMenu,
                    new ShowVerticalSubMenuAction(settingsMenu)));
        }
        if (AuthorizationService.session().authenticatedUser()
                .isAuthorizedTo(ActionRight.MANAGE_KITCHEN)) {
            //Reservations
            final Menu reservationsMenu = buildReservationsMenu();
            mainMenu.add(new SubMenu(RESERVATIONS_MENU_OPTION, reservationsMenu,
                    new ShowVerticalSubMenuAction(reservationsMenu)));
            
            //Traceability
            final Menu kitchenMenu = buildKitchenMenu();
            mainMenu.add(new SubMenu(TRACEABILITY_OPTION, kitchenMenu,
                    new ShowVerticalSubMenuAction(kitchenMenu)));
            mainMenu.add(new MenuItem(CAFETERIA_SHIFT_OPTION, "Close Cafeteria Shift",
                    new CafeteriaShiftClosingAction()));

        }
        if (AuthorizationService.session().authenticatedUser()
                .isAuthorizedTo(ActionRight.MANAGE_MENUS)) {
            final Menu dishTypeMenu = buildDishMenu();
            mainMenu.add(new SubMenu(DISH_TYPES_OPTION, dishTypeMenu,
                    new ShowVerticalSubMenuAction(dishTypeMenu)));

            // reporting
            final Menu reportingDishesMenu = buildReportingDishesMenu();
            mainMenu.add(new SubMenu(REPORTING_DISHES_OPTION, reportingDishesMenu,
                    new ShowVerticalSubMenuAction(reportingDishesMenu)));
        }

        if (AuthorizationService.session()
                .authenticatedUser().isAuthorizedTo(ActionRight.MANAGE_MENUS)) {
            final Menu mealsMenu = buildMealsMenu();
            mainMenu.add(new SubMenu(MEALS_MENU_OPTION, mealsMenu, new ShowVerticalSubMenuAction(mealsMenu)));
        }

        if (!Application.settings()
                .isMenuLayoutHorizontal()) {
            mainMenu.add(VerticalSeparator.separator());
        }

        mainMenu.add(
                new MenuItem(EXIT_OPTION, "Exit", new ExitWithMessageAction()));

        return mainMenu;
    }

    private Menu buildAdminSettingsMenu() {
        final Menu menu = new Menu("Settings >");

        menu.add(new MenuItem(SET_KITCHEN_ALERT_LIMIT_OPTION, "Set kitchen alert limit",
                new AlertManagementAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildUsersMenu() {
        final Menu menu = new Menu("Users >");

        menu.add(new MenuItem(ADD_USER_OPTION, "Add User", () -> new AddUserUI().show()));
        menu.add(new MenuItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction()));
        menu.add(new MenuItem(DEACTIVATE_USER_OPTION, "Deactivate User",
                new DeactivateUserAction()));
        menu.add(new MenuItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request",
                new AcceptRefuseSignupRequestAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildDishMenu() {
        final Menu menu = new Menu("Dishes >");

        // dish types
        menu.add(new MenuItem(DISH_TYPE_REGISTER_OPTION, "Register new Dish Type",
                new RegisterDishTypeAction()));
        menu.add(new MenuItem(DISH_TYPE_LIST_OPTION, "List all Dish Type",
                new ListDishTypeAction()));
        menu.add(new MenuItem(DISH_TYPE_CHANGE_OPTION, "Change Dish Type description",
                new ChangeDishTypeAction()));
        menu.add(new MenuItem(DISH_TYPE_ACTIVATE_DEACTIVATE_OPTION, "Activate/Deactivate Dish Type",
                new ActivateDeactivateDishTypeAction()));

        // dishes
        menu.add(new MenuItem(DISH_REGISTER_OPTION, "Register new Dish", new RegisterDishAction()));
        menu.add(new MenuItem(DISH_LIST_OPTION, "List all Dish", new ListDishAction()));

        menu.add(new MenuItem(DISH_REGISTER_DTO_OPTION, "Register new Dish (via DTO)",
                () -> new RegisterDishViaDTOUI().show()));
        menu.add(new MenuItem(DISH_LIST_DTO_OPTION, "List all Dish (via DTO)",
                () -> new ListDishViaDTOUI().show()));

        menu.add(new MenuItem(DISH_ACTIVATE_DEACTIVATE_OPTION, "Activate/Deactivate Dish",
                new ActivateDeactivateDishAction()));
        final Menu changeDishMenu = buildChangeDishMenu();
        menu.add(new MenuItem(DISH_CHANGE_OPTION, "Change Dish Information",
                new ShowVerticalSubMenuAction(changeDishMenu)));
        menu.add(new MenuItem(DISH_ALLERGEN, "Dish Allergen",
                new DishAllergernsListAction()));

        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildReservationsMenu() {
        final Menu menu = new Menu("Reservations >");
        
        menu.add(new MenuItem(CHECK_RESERVATIONS_OPTION, "Check Existing Reservations",
                new CheckExistingReservationsAction()));
        
        return menu;
    }
    
    private Menu buildKitchenMenu() {
        final Menu menu = new Menu("Traceability >");

        menu.add(new MenuItem(MATERIAL_REGISTER_OPTION, "Register new material",
                new RegisterMaterialAction()));
        menu.add(new MenuItem(MATERIAL_LIST_OPTION, "List all materials", new ListMaterialAction()));
        final Menu elaborateMealPlan = buildElaborateMealPlanMenu();
        menu.add(new SubMenu(ELABORATE_MEAL_PLAN_OPTION, elaborateMealPlan, new ShowVerticalSubMenuAction(buildElaborateMealPlanMenu())));
       menu.add(new MenuItem(FIND_MEALS_BY_LOT_OPTION, "Find Meals by Lot", new ListMealsByLotAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildElaborateMealPlanMenu() {

        final Menu menu = new Menu("Elaborate Meal Plan");
        menu.add(new MenuItem(CREATE_MEAL_PLAN, "Create meal plan", new CreateMealPlanAction()));
        menu.add(new MenuItem(EDIT_MEAL_PLAN, "Edit meal plan", new EditMealPlanAction()));
        menu.add(new MenuItem(CLOSE_MEAL_PLAN, "Close meal plan", new CloseMealPlanAction()));
        return menu;
    }

    private Menu buildChangeDishMenu() {
        final Menu menu = new Menu("Change Dish >");

        menu.add(new MenuItem(CHANGE_DISH_NUTRICIONAL_INFO_OPTION, "Change Nutricional Info",
                new ChangeDishNutricionalInfoAction()));
        menu.add(new MenuItem(CHANGE_DISH_PRICE_OPTION, "Change Price",
                new ChangeDishPriceAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildReportingDishesMenu() {
        final Menu menu = new Menu("Reporting Dishes >");

        menu.add(new MenuItem(REPORTING_DISHES_PER_DISHTYPE_OPTION, "Dishes per Dish Type",
                () -> new ReportDishesPerDishTypeUI().show()));
        menu.add(new MenuItem(REPORTING_HIGH_CALORIES_DISHES_OPTION, "High Calories Dishes",
                () -> new ReportHighCaloriesDishesUI().show()));
        menu.add(new MenuItem(REPORTING_DISHES_PER_CALORIC_CATEGORY_OPTION,
                "Dishes per Caloric Category",
                () -> new ReportDishesPerCaloricCategoryUI().show()));
        menu.add(new MenuItem(REPORTING_DISHES_PER_CALORIC_CATEGORY_OPTION + 1,
                "Dishes per Caloric Category (as tuples)",
                () -> new ReportDishesPerCaloricCategoryAsTuplesUI().show()));

        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    }

    private Menu buildMealsMenu() {
        final Menu menu = new Menu("Menus >");
        menu.add(new MenuItem(REGISTER_MENU_OPTION, "Register Menu", new RegisterMenuAction()));
        menu.add(new MenuItem(PUBLISH_MENU_OPTION, "Publish Menu", new PublishMenuAction()));
        menu.add(new MenuItem(COPY_MENU_OPTION, "Copy Menu", new CopyMenuAction()));
        menu.add(new MenuItem(PUBLISH_MEAL_OPTION, "Publish Meal", new PublishMealAction()));
        menu.add(new MenuItem(REGISTER_USED_LOT_OPTION, "Register Used Lot", new RegisterUsedLotAction()));
        menu.add(new MenuItem(CONSULT_MEALS_RATING, "Consult meals rating", new ConsultMealRatingAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));
        return menu;
    }
}
