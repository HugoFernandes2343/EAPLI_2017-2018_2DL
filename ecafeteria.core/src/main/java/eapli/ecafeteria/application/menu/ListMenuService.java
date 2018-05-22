/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menu;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.Date;

/**
 *
 * @author David Santiago
 */
public class ListMenuService {

    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();
    private final MealRepository mealRepository = PersistenceContext.repositories().meals();

    public Iterable<Menu> allMenus() {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        return this.menuRepository.findPublishedMenu();
    }

    public Menu findByID(long id) {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        return this.menuRepository.findByID(id);
    }

    public Menu listMenu(Date date) {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        return this.menuRepository.findByDate(date);

    }

    public Menu listMenuBooking(Date date) {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.SELECT_MEAL);

        return this.menuRepository.findByDate(date);
    }

    public Iterable<Meal> findMealByMenu(Menu menu) {
        return this.mealRepository.findMealByMenu(menu);
    }

}
