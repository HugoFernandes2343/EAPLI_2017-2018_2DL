/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import eapli.framework.domain.ddd.ValueObject;
import eapli.framework.domain.money.Money;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author filip
 */
@Embeddable
public class BalanceLimit implements ValueObject, Serializable {
    private static final long serialVersionUID = 1L;

    private Money balanceLimit;

    protected BalanceLimit() {
        this.balanceLimit = Money.euros(-1);
    }

    public boolean defineLimit(double value) {
        boolean operationConcluded = false;
        if (value < 0.0) {
            System.out.println("Não é possível definir um limite inferior a 0€.");
        } else {
            this.balanceLimit = Money.euros(value);
            operationConcluded = true;
        }
        return operationConcluded;
    }
    
    public Money limit(){
        return balanceLimit;
    }
    
}
