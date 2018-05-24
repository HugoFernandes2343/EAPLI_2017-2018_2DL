/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.menu.RegisterMenuController;
import eapli.framework.actions.Action;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.Calendar;

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
        Designation teste7 = Designation.valueOf("PUBLISHED_MENU");

        try {
            register(DateTime.newCalendar(2019, 4, 1), DateTime.newCalendar(2019, 4, 7),teste1);
            register(DateTime.newCalendar(2019, 5, 1), DateTime.newCalendar(2019, 5, 7),teste2);
            register(DateTime.newCalendar(2019, 6, 1), DateTime.newCalendar(2019, 6, 7),teste3);
            register(DateTime.newCalendar(2019, 7, 1), DateTime.newCalendar(2019, 7, 7),teste4);
            register(DateTime.newCalendar(2019, 8, 1), DateTime.newCalendar(2019, 8, 7),teste5);
            register(DateTime.newCalendar(2019, 9, 1), DateTime.newCalendar(2019, 9, 7),teste6);
            Calendar tomorrow = DateTime.now();
            Calendar inAWeek = DateTime.now();
            tomorrow.add(Calendar.DATE, 1);
            inAWeek.add(Calendar.DATE, 7);
            register(tomorrow,inAWeek,teste7);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            System.out.println("Erro a registar menu");
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
