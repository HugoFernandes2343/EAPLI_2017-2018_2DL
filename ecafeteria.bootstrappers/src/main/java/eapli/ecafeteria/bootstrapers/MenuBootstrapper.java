/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.menu.PublishMenuController;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.actions.Action;
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

        try {
            register(DateTime.newCalendar(2018, 4, 30), DateTime.newCalendar(2018, 5, 6));
            register(DateTime.newCalendar(2018, 5, 7), DateTime.newCalendar(2018, 5, 13));
            register(DateTime.newCalendar(2018, 5, 14), DateTime.newCalendar(2018, 5, 20));
            register(DateTime.newCalendar(2018, 5, 21), DateTime.newCalendar(2018, 5, 27));
            register(DateTime.newCalendar(2018, 5, 28), DateTime.newCalendar(2018, 6, 3));
            register(DateTime.newCalendar(2018, 6, 4), DateTime.newCalendar(2018, 6, 10));
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
    private void register(Calendar startDate, Calendar endDate) throws DataConcurrencyException, DataIntegrityViolationException {
        final PublishMenuController controller = new PublishMenuController();
        controller.publish(new Menu(startDate, endDate));
    }
    
}
