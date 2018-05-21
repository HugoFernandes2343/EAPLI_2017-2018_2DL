/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.kitchen.KitchenAlerts;
import eapli.ecafeteria.persistence.KitchenAlertsRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1150425
 */
public class KitchenBootstrapper implements Action{

    @Override
    public boolean execute() {
        final KitchenAlertsRepository repo = PersistenceContext.repositories().KitchenAlertsRepository();
        final KitchenAlerts ka = new KitchenAlerts("Kitchen Alert", 75, 90);

        try {
            repo.save(ka);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(ECafeteriaBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;

    }
}
