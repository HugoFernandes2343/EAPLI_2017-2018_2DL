/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.KitchenAlerts;
import eapli.ecafeteria.persistence.KitchenAlertsRepository;
import java.util.Optional;
import javax.persistence.Query;

/**
 *
 * @author 1150425
 */
public class JpaKitchenAlertRepository extends CafeteriaJpaRepositoryBase<KitchenAlerts, String> implements KitchenAlertsRepository {

    @Override
    public Optional<KitchenAlerts> findByName(String name) {
        return matchOne("e.name=:name", "name", name);
    }    
    
}
