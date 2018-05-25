/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.authz;

import eapli.ecafeteria.application.kitchen.KitchenAlertsManagementController;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1150425
 */
class KitchenAlertManagementUI extends AbstractUI {

    private final KitchenAlertsManagementController theController = new KitchenAlertsManagementController();

    protected KitchenAlertsManagementController controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {

        System.out.println(theController.alertValuesMessage());

        System.out.println("Do you wish to:\n1. Change the value for the Yellow Alert.\n"
                + "2. Change the value for the Red Alert.\n"
                + "3. Change the value for Both Alerts\n0. Exit");
        
        int option;
        do {
            option = Console.readInteger("Choose an option:");
            if (option == 0) {
                return false;
            }
        } while (option != 0 && option != 1 && option != 2 && option != 3);

        boolean success = false;
        switch (option) {
            case 1: {
                int value = Console.readInteger("Value for the Yellow Alert:");
            try {
                this.theController.changeYellowAlert(value);
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                Logger.getLogger(KitchenAlertManagementUI.class.getName()).log(Level.SEVERE, null, ex);
            }
                success = true;
                break;
            }
            case 2: {
                int value = Console.readInteger("Value for the Red Alert:");
            try {
                this.theController.changeRedAlert(value);
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                Logger.getLogger(KitchenAlertManagementUI.class.getName()).log(Level.SEVERE, null, ex);
            }
                success = true;
                break;

            }
            case 3: {
                int valueYellow = Console.readInteger("Value for the Yellow Alert:");
                int valueRed = Console.readInteger("Value for the Red Alert:");
            try {
                this.theController.changeBothAlert(valueYellow, valueRed);
            } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
                Logger.getLogger(KitchenAlertManagementUI.class.getName()).log(Level.SEVERE, null, ex);
            }
                success = true;
                break;

            }
            default:
                break;
        }
        System.out.println("Updated Values: " + theController.alertValuesMessage());
        return success;
    }

    @Override
    public String headline() {
        return "Set the Values of the Alerts";
    }

}
