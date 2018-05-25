/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.KitchenAlerts;
import eapli.ecafeteria.persistence.KitchenAlertsRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author 1150425
 */
public class KitchenAlertsManagementController {

    private final KitchenAlertsRepository repository = PersistenceContext.repositories().KitchenAlertsRepository();
    private final String name = "Kitchen Alert";
    private final KitchenAlerts am;

    public KitchenAlertsManagementController() {
        am = this.repository.findByName(name).get();
        am.changeYellowAlert(this.repository.findByName(name).get().yellowAlertValue());
        am.changeRedAlert(this.repository.findByName(name).get().redAlertValue());
    }

    public boolean changeYellowAlert(int value) throws DataConcurrencyException, DataIntegrityViolationException {

        am.changeYellowAlert(value);
        this.repository.save(am);
        return true;

    }

    public boolean changeRedAlert(int value) throws DataConcurrencyException, DataIntegrityViolationException {

        am.changeRedAlert(value);
        this.repository.save(am);

        return true;
    }

    public boolean changeBothAlert(int yellowValue, int redValue) throws DataConcurrencyException, DataIntegrityViolationException {

        am.changeRedAlert(redValue);
        am.changeYellowAlert(yellowValue);
        this.repository.save(am);
        return true;

    }

    public String alertValuesMessage() {
        return this.am.alertValues();
    }
}
