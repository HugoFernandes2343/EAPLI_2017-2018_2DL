/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.pos;

import eapli.framework.domain.POSStateViolationException;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author Norberto Sousa - 1120608 && Hugo Fernandes 1161155
 */
@Entity
public class POS implements AggregateRoot<Long>, Serializable {
    
    @Id
    @GeneratedValue
    private Long posID;
    
    private int code;
    
    @Enumerated(EnumType.STRING)
    private POSState currentState;
    
    public POS(){
    }
    
    public POS(int code) throws POSStateViolationException {
        this.currentState = POSState.OPENED;
        this.code = code;
    }

    
    public void open() {
            currentState = POSState.OPENED;
    }
    
    public void close() throws POSStateViolationException {
            if (currentState.equals(POSState.OPENED)) {
                currentState = POSState.CLOSED;
            } else {
                throw new POSStateViolationException();
            }
    }
    
    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof POS)) {
            return false;
        }
        final POS p = (POS) other;

        if (this == p) {
            return true;
        }

        if (!this.posID.equals(p.posID)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean is(Long otherId) {
        return id().equals(otherId);
    }

    @Override
    public Long id() {
        return posID;
    }
       


}
