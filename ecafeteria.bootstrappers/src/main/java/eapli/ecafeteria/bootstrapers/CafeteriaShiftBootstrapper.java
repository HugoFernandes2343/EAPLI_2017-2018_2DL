/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.kitchen.CafeteriaShiftClosingController;
import eapli.ecafeteria.domain.cafeteriashift.CafeteriaShift;
import eapli.framework.actions.Action;
import eapli.framework.domain.CafeteriaShiftStateViolationException;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.Calendar;

/**
 *
 * @author Norberto Sousa - 1120608
 */
public class CafeteriaShiftBootstrapper implements Action {

    @Override
    public boolean execute() {
        CafeteriaShiftClosingController csCont = new CafeteriaShiftClosingController();

        Calendar tomorrow = DateTime.now();
        tomorrow.add(Calendar.DATE, 1);
        CafeteriaShift cs = new CafeteriaShift(tomorrow, "LUNCH");
        try {
            cs.closeShift();
            csCont.SaveCafeteriaShift(cs);
        } catch (DataConcurrencyException | DataIntegrityViolationException | CafeteriaShiftStateViolationException ex) {
            System.out.println("Erro a gravar cafeteria shift");
        }
        return true;
    }

}
