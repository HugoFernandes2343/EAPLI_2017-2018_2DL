/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.domain.cafeteriashift.CafeteriaShift;
import eapli.ecafeteria.domain.cafeteriashift.CafeteriaShiftDayTimeState;
import eapli.ecafeteria.domain.cafeteriashift.CafeteriaShiftState;
import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.domain.pos.POSState;
import eapli.ecafeteria.persistence.CafeteriaShiftRepository;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.POSStateViolationException;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.DateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

/**
 *
 * @author Norberto Sousa - 1120608 && Hugo Fernandes 1161155
 */
public class OpenPosController implements Controller {

    private final POSRepository posRP = PersistenceContext.repositories().pos();
    private final CafeteriaShiftRepository cfRP = PersistenceContext.repositories().cafeteriaShift();
    private ArrayList<POS> list_pos = new ArrayList<>();

    public boolean IsTheCafeteriaShiftClosed() {
        CafeteriaShift cs = cfRP.findCafeteriaShift();

        if (cs.isClosed()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean OpenAndUpdateCafeteriaShift(String dt) throws DataConcurrencyException, DataIntegrityViolationException {
        CafeteriaShift cs = cfRP.findCafeteriaShift();
        Calendar date = DateTime.now();
        if (dt.equalsIgnoreCase("LUNCH")) {
            cs.update(CafeteriaShiftState.OPENED, CafeteriaShiftDayTimeState.LUNCH, date);
            cfRP.save(cs);
            return true;
        } else if (dt.equalsIgnoreCase("DINNER")) {
            cs.update(CafeteriaShiftState.OPENED, CafeteriaShiftDayTimeState.DINNER, date);
            cfRP.save(cs);
            return true;
        } else {
            return false;
        }
    }

    public boolean ListAllClosedPOS() throws POSStateViolationException {

        list_pos = (ArrayList<POS>) posRP.findByState(POSState.CLOSED);

        if (list_pos.isEmpty()) {
            System.out.println("No POS can be opened now!");
        } else {
            System.out.println("Closed POS's:");

            //Bubble sort
            boolean is_sorted;
            POS sortingTemp;

            for (int i = 0; i < list_pos.size(); i++) {

                is_sorted = true;

                for (int j = 1; j < (list_pos.size() - i); j++) {

                    if (list_pos.get(j - 1).code() > list_pos.get(j).code()) {
                        sortingTemp = list_pos.get(j - 1);
                        list_pos.set(j - 1, list_pos.get(j));
                        list_pos.set(j, sortingTemp);
                        is_sorted = false;
                    }

                }

                // is sorted? then break it, avoid useless loop.
                if (is_sorted) {
                    break;
                }

            }
        }
        for (POS p : list_pos) {
            System.out.println("POS " + p.code());
        }

        if (list_pos.isEmpty()) {
            return false;
        }
        return true;
    }

    public boolean OpenAndSavePOS(int code) throws POSStateViolationException, DataConcurrencyException, DataIntegrityViolationException {

        for (POS p : list_pos) {
            if (p.code() == code) {
                p.open();

                posRP.saveWithDelete(p);

                return true;
            }
        }

        return false;
    }
}
