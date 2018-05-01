/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.pos;

import java.io.Serializable;

/**
 *
 * @author hugod
 */
public enum POSState implements Serializable{
    
     OPEN {
        private final String id = "Open";

        @Override
        public POSState state() {
            return OPEN;
        }

        @Override
        public String toString() {
            return id;
        }

    },
    CLOSED {
        private final String id = "Closed";

        @Override
        public POSState state() {
            return CLOSED;
        }

        @Override
        public String toString() {
            return id;
        }
    };

    public abstract POSState state();
    
}
