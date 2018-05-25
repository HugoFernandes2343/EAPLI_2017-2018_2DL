/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.KitchenAlerts;
import eapli.ecafeteria.persistence.KitchenAlertsRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.Optional;

/**
 *
 * @author 1150425
 */
public class InMemoryKitchenAlertRepository extends InMemoryRepository<KitchenAlerts, String>
        implements KitchenAlertsRepository {

  
    @Override
    public Optional<KitchenAlerts> findByName(String name) {
      return matchOne(e -> e.alertName().equals(name));
    }

    @Override
    protected String newKeyFor(KitchenAlerts entity) {
       return entity.alertName();
    }
}
