/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application.cafeteria;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.cafeteria.OrganicUnit;
import eapli.ecafeteria.persistence.OrganicUnitRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

/**
 *
 * @author losa
 */
public class ListOrganicUnitsController implements Controller {

    private final OrganicUnitRepository repo = PersistenceContext.repositories().organicUnits();

    public Iterable<OrganicUnit> listOrganicUnits() {
        Application.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);

        return this.repo.findAll();
    }
}
