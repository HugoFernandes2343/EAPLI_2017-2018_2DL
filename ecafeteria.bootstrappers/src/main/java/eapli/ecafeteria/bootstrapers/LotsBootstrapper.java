/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.kitchen.RegisterLotController;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.persistence.MaterialRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Optional;
import java.util.logging.Logger;

/**
 *
 * @author Hugo
 */
public class LotsBootstrapper implements Action {

    @Override
    public boolean execute() {
        final MaterialRepository matRepo = PersistenceContext.repositories().materials();
        Optional<Material> material=matRepo.findByAcronym("oil");
        register("0x01", material.get());
        return true;
    }
    
    
    private void register(String code, Material material) {
     final RegisterLotController controller = new RegisterLotController();
        try {
            controller.registerLot(code, material);
        } catch (final DataIntegrityViolationException | DataConcurrencyException | IllegalArgumentException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstrapper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }
}
