/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application.meals;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meals.DishType;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author mcn
 */
public class ActivateDeactivateDishTypeController implements Controller {

    private final ListDishTypeService svc = new ListDishTypeService();
    private final DishTypeRepository dishTypeRepository = PersistenceContext.repositories().dishTypes();

    public Iterable<DishType> allDishTypes() {
        return this.svc.allDishTypes();
    }

    public void changeDishTypeState(DishType dType) throws DataConcurrencyException, DataIntegrityViolationException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        if (dType == null) {
            throw new IllegalStateException();
        }
        dType.toogleState();
        this.dishTypeRepository.save(dType);
    }
}
