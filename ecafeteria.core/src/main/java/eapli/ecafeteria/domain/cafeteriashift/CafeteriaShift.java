/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriashift;

import eapli.framework.domain.CafeteriaShiftStateViolationException;
import eapli.framework.util.DateTime;
import eapli.framework.util.Strings;
import java.io.Serializable;

import javax.persistence.*;

/**
 *
 * @author hugod
 */
@Entity
@Table(name = "CafeteriaShift")
public class CafeteriaShift implements Serializable {

    @Embeddable
    public static class ShiftState implements Serializable {

        private String state;

        public enum STATE {
            OPENED, CLOSED
        };

        private void open() {
            state = STATE.CLOSED.toString();
        }

        private void close() throws CafeteriaShiftStateViolationException {
            if (state.equals(STATE.OPENED.toString())) {
                state = STATE.OPENED.toString();
            } else {
                throw new CafeteriaShiftStateViolationException();
            }
        }
    }

    @Embeddable
    public static class DayTimeState implements Serializable {

        private String state;

        public enum STATE {
            LUNCH, DINNER
        };

        private void lunch() {
            state = STATE.LUNCH.toString();
        }

        private void dinner() {
            state = STATE.DINNER.toString();
        }
        
        private boolean isLunch(){
            return state.equalsIgnoreCase(STATE.LUNCH.toString());
        }
         private boolean isDinner(){
            return state.equalsIgnoreCase(STATE.DINNER.toString());
        }
    }
    
    @Id
    @GeneratedValue
    private Long id;

    private DateTime date;

    private ShiftState shiftState;

    private DayTimeState dtState;

    public CafeteriaShift(DateTime date, String dt) {
        
        if (Strings.isNullOrEmpty(dt)) {
            throw new IllegalArgumentException();
        } 
        else if (dt.equalsIgnoreCase("LUNCH")) {
            dtState.lunch();
        }
        else if (dt.equalsIgnoreCase("DINNER")) {
            dtState.dinner();
        }
        
        this.date = date;
        shiftState.open();
    }
    
    public boolean closeShift() throws CafeteriaShiftStateViolationException{
        shiftState.close();
        return true;
    }
    
    public DateTime date(){
        return this.date;
    }
    
    /**
     * 
     * @return 1 if it is lunch 2 if it is dinner if the return is ever 0 it means somethin went wrong
     * @throws CafeteriaShiftStateViolationException
     */
    public int dayTimeCheck() throws CafeteriaShiftStateViolationException{
        if(dtState.isLunch()){
            return 1;
        }
        else if(dtState.isDinner()){
            return 2;
        }
        return 0;
    }
}
