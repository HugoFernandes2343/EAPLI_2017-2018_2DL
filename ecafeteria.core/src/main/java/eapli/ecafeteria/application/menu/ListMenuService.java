/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.menu;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.Date;

/**
 *
 * @author João Vieira
 */
public class ListMenuService {

    private final MenuRepository menuRepository = PersistenceContext.repositories().menus();

    public Iterable<Menu> allMenus() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.menuRepository.findAll();
    }

    public Menu listMenu(Date date) {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.menuRepository.findByDate(date);
    }

}
