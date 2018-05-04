/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriashift;

import eapli.framework.domain.CafeteriaShiftStateViolationException;
import eapli.framework.domain.ddd.AggregateRoot;
import eapli.framework.util.Strings;
import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.*;

/**
 *
 * @author hugod
 */
@Entity
@Table(name = "CafeteriaShift")
public class CafeteriaShift implements Serializable, AggregateRoot<Long> {

    @Embeddable
    public static class ShiftState implements Serializable {

        private String sstate;

        public enum STATE {
            OPENED, CLOSED
        };

        private void open() {
            sstate = STATE.CLOSED.toString();
        }

        private void close() throws CafeteriaShiftStateViolationException {
            if (sstate.equals(STATE.OPENED.toString())) {
                sstate = STATE.OPENED.toString();
            } else {
                throw new CafeteriaShiftStateViolationException();
            }
        }
    }

    @Embeddable
    public static class DayTimeState implements Serializable {

        private String dtstate;

        public enum STATE {
            LUNCH, DINNER
        };

        private void lunch() {
            dtstate = STATE.LUNCH.toString();
        }

        private void dinner() {
            dtstate = STATE.DINNER.toString();
        }

        private boolean isLunch() {
            return dtstate.equalsIgnoreCase(STATE.LUNCH.toString());
        }

        private boolean isDinner() {
            return dtstate.equalsIgnoreCase(STATE.DINNER.toString());
        }
    }

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.DATE)
    private Calendar date;

    private ShiftState shiftState;

    private DayTimeState dtState;

    public CafeteriaShift() {
    }

    public CafeteriaShift(Calendar date, String dt) {

        this.id = new Long(1);

        if (Strings.isNullOrEmpty(dt)) {
            throw new IllegalArgumentException();
        } else if (dt.equalsIgnoreCase("LUNCH")) {
            dtState.lunch();
        } else if (dt.equalsIgnoreCase("DINNER")) {
            dtState.dinner();
        }

        this.date = date;
        shiftState.open();
    }

    public boolean closeShift() throws CafeteriaShiftStateViolationException {
        shiftState.close();
        return true;
    }

    public Calendar date() {
        return this.date;
    }

    /**
     *
     * @return 1 if it is lunch 2 if it is dinner if the return is ever 0 it
     * means somethin went wrong
     * @throws CafeteriaShiftStateViolationException
     */
    public String dayTimeCheck() throws CafeteriaShiftStateViolationException {
        if (dtState.isLunch()) {
            return "LUNCH";
        } else {
            return "DINNER";
        }

    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof CafeteriaShift)) {
            return false;
        }

        final CafeteriaShift s = (CafeteriaShift) other;

        if (!this.id.equals(s.id)) {
            return false;
        }

        if (!this.date.equals(s.date)) {
            return false;
        }

        if (!this.shiftState.equals(s.shiftState)) {
            return false;
        }

        return this.dtState.equals(s.dtState);
    }

    @Override
    public Long id() {
        return id;
    }

}
