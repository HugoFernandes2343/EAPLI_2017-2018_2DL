/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.reservations;

import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Class Reservation
 * 
 * @author Marco
 */
@Entity
public class Reservation implements AggregateRoot<String>,Serializable {

    @Id
    @GeneratedValue
    private Long pk;

    @Override
    public boolean sameAs(Object other) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean is(String otherId) {
        return AggregateRoot.super.is(otherId); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String id() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
