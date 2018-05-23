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

    @Override
    public int getYellowValue() {
        final Query q;
        q = entityManager().createQuery("SELECT YellowAlert FROM KitchenAlerts");
        return q.getFirstResult();
    }

    @Override
    public int getRedValue() {
        final Query q;
        q = entityManager().createQuery("SELECT RedAlert FROM KitchenAlerts");
        return q.getFirstResult();
    }
}
