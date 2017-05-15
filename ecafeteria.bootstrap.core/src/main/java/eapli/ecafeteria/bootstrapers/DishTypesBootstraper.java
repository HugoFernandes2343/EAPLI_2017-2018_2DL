/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.meals.RegisterDishTypeController;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Logger;

/**
 *
 * @author mcn
 */
public class DishTypesBootstraper implements Action {

    @Override
    public boolean execute() {
        register("vegie", "vegetarian dish");
        register("fish", "fish dish");
        register("meat", "meat dish");
        return false;
    }

    /**
     *
     */
    private void register(String acronym, String description) {
        final RegisterDishTypeController controller = new RegisterDishTypeController();
        try {
            controller.registerDishType(acronym, description);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstraper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }
}
