/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Embeddable;

/**
 *
 * @author Rodrigo Soares <1140420@isep.ipp.pt>
 */
@Embeddable
public class NutritionalProfileField implements ValueObject, Serializable{
    private String description;
    private double value;

    protected NutritionalProfileField() {
        //JPA
    }    
    
    public NutritionalProfileField(String description, double value) {
        this.description = description;
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.description);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.value) ^ (Double.doubleToLongBits(this.value) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NutritionalProfileField other = (NutritionalProfileField) obj;
        if (Double.doubleToLongBits(this.value) != Double.doubleToLongBits(other.value)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        return true;
    }

    public String description() {
        return description;
    }

    public double value() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    
    @Override
    public String toString() {
        return "NutritionalProfileField{" + "description=" + description + ", value=" + value + '}';
    }
    
    
}
