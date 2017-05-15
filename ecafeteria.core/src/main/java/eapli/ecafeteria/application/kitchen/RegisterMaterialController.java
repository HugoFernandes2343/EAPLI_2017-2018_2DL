/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.persistence.MaterialRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author mcn
 */
public class RegisterMaterialController implements Controller {

    private final MaterialRepository repository = PersistenceContext.repositories().materials();

    public Material registerMaterial(String acronym, String description)
            throws DataIntegrityViolationException, DataConcurrencyException {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        final Material mat = new Material(acronym, description);
        return this.repository.save(mat);
    }
}
