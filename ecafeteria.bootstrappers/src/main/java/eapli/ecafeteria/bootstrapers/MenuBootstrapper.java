/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.menu.PublishMenuController;
import eapli.ecafeteria.application.menu.RegisterMenuController;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.actions.Action;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author João Vieira
 */
public class MenuBootstrapper implements Action {

    @Override
    public boolean execute() {
        
        
        Designation teste1 = Designation.valueOf("Menu NATAL");
        Designation teste2 = Designation.valueOf("Menu CARNAVAL");
        Designation teste3 = Designation.valueOf("Menu PASCOA");
        Designation teste4 = Designation.valueOf("Menu QUEIMA");
        Designation teste5 = Designation.valueOf("Menu EAPLI");
        Designation teste6 = Designation.valueOf("Menu GERINGONÇA");

        try {
            register(DateTime.newCalendar(2018, 4, 1), DateTime.newCalendar(2018, 4, 7),teste1);
            register(DateTime.newCalendar(2018, 5, 1), DateTime.newCalendar(2018, 5, 7),teste2);
            register(DateTime.newCalendar(2018, 6, 1), DateTime.newCalendar(2018, 6, 7),teste3);
            register(DateTime.newCalendar(2018, 7, 1), DateTime.newCalendar(2018, 7, 7),teste4);
            register(DateTime.newCalendar(2018, 8, 1), DateTime.newCalendar(2018, 8, 7),teste5);
            register(DateTime.newCalendar(2018, 9, 1), DateTime.newCalendar(2018, 9, 7),teste6);
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(MenuBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(MenuBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return true;
    }

    /**
     *
     */
    private void register(Calendar startDate, Calendar endDate, Designation name) throws DataConcurrencyException, DataIntegrityViolationException {
        final RegisterMenuController controller = new RegisterMenuController();
        controller.MenuMaker(startDate, endDate, name);
        controller.saveMenu();
    }
    
}
