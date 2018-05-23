/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.KitchenAlerts;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Optional;

/**
 *
 * @author 1150425
 */
public interface KitchenAlertsRepository extends DataRepository<KitchenAlerts, String> {
    
    public Optional<KitchenAlerts> findByName(String name);

    public int getYellowValue();
    
    public int getRedValue();
    
}
