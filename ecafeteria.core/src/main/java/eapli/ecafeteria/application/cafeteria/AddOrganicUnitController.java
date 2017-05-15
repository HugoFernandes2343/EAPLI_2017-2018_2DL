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
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author arocha
 */
public class AddOrganicUnitController implements Controller {

    private final OrganicUnitRepository organicUnitRepository = PersistenceContext.repositories().organicUnits();

    public OrganicUnit addOrganicUnit(String acronym, String name, String description)
            throws DataIntegrityViolationException, DataConcurrencyException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);

        return this.organicUnitRepository.save(new OrganicUnit(acronym, name, description));
    }
}
