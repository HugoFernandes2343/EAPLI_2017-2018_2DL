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
 * @author Norberto Sousa - 1120608 && Hugo Fernandes 1161155
 */
@Entity
@Table(name = "CafeteriaShift")
public class CafeteriaShift implements Serializable, AggregateRoot<Long> {

    @Id
    @GeneratedValue
    private Long id;

    @Temporal(TemporalType.DATE)
    private Calendar date;

    @Enumerated(EnumType.STRING)
    private CafeteriaShiftState shiftState;

    @Enumerated(EnumType.STRING)
    private CafeteriaShiftDayTimeState dtState;

    public CafeteriaShift() {
    }

    public CafeteriaShift(Calendar date, String dt) {

        this.id = new Long(1);

        if (Strings.isNullOrEmpty(dt)) {
            throw new IllegalArgumentException();
        } else if (dt.equalsIgnoreCase("LUNCH")) {
            dtState = CafeteriaShiftDayTimeState.LUNCH;
        } else if (dt.equalsIgnoreCase("DINNER")) {
            dtState = CafeteriaShiftDayTimeState.DINNER;
        }

        this.date = date;
        shiftState = CafeteriaShiftState.OPENED;
    }

    public void openShift() {
        shiftState = CafeteriaShiftState.OPENED;
    }

    public void closeShift() throws CafeteriaShiftStateViolationException {
        if (shiftState.equals(CafeteriaShiftState.OPENED)) {
            shiftState = CafeteriaShiftState.CLOSED;
        } else {
            throw new CafeteriaShiftStateViolationException();
        }
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
        if (dtState.equals(CafeteriaShiftDayTimeState.LUNCH)) {
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
