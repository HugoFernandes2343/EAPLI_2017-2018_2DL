package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.persistence.MaterialRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;

/**
 * Created on 29/03/2016.
 */
public class ListMaterialController implements Controller {

    private final MaterialRepository repository = PersistenceContext.repositories().materials();

    public Iterable<Material> all() {
        Application.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);
        return this.repository.findAll();
    }
}
