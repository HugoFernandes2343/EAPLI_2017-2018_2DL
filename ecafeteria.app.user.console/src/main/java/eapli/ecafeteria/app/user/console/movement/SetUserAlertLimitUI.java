/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.movement;

import eapli.ecafeteria.app.user.console.presentation.CafeteriaUserBaseUI;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.application.cafeteriauser.SetUserAlertLimitController;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.Console;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Vieira
 */
public class SetUserAlertLimitUI extends CafeteriaUserBaseUI {

    private final SetUserAlertLimitController theController = new SetUserAlertLimitController();


    @Override
    protected boolean doShow() {

            double value = Console.readDouble("Qual o limite que deseja impor na sua conta?");
        try {
            theController.changeUserAlertLimit(value);
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(SetUserAlertLimitUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(SetUserAlertLimitUI.class.getName()).log(Level.SEVERE, null, ex);
        }
            Console.readLine("Sucesso! O seu novo limite de saldo é: " + theController.getUserAlertLimit() + "€");

        return true;
    }

    @Override
    protected CafeteriaUserBaseController controller() {
        return new CafeteriaUserBaseController();
    }


}
