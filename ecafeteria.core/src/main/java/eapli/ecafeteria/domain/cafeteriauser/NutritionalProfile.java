/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import eapli.ecafeteria.domain.allergens.Allergen;
import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * @author Rodrigo Soares <1140420@isep.ipp.pt>
 */
@Embeddable
public class NutritionalProfile implements ValueObject, Serializable {
    @ElementCollection
    private List<NutritionalProfileField> fields;
    @ElementCollection
    private List<Allergen> allergens;

    private static final double DEFAULT_SALT_PER_MEAL = Double.MAX_VALUE;
    private static final double DEFAULT_SALT_PER_WEEK = Double.MAX_VALUE;
    private static final double DEFAULT_CALORIES_PER_MEAL = Double.MAX_VALUE;
    private static final double DEFAULT_CALORIES_PER_WEEK = Double.MAX_VALUE;
    
    public NutritionalProfile(List<NutritionalProfileField> fields, List<Allergen> allergens) {
        this.fields = fields;
        this.allergens = allergens;
    }

    protected NutritionalProfile() {
        buildDefaultFields();
    }

    private void buildDefaultFields(){
        fields = new ArrayList<NutritionalProfileField>();
        allergens = new ArrayList<Allergen>(); 
        
        fields.add (new NutritionalProfileField ("Salt per Meal (g)", DEFAULT_SALT_PER_MEAL) );
        fields.add (new NutritionalProfileField ("Salt per Week (g)", DEFAULT_SALT_PER_WEEK) );
        fields.add (new NutritionalProfileField ("Calories per Meal (cal)", DEFAULT_CALORIES_PER_MEAL) );
        fields.add (new NutritionalProfileField ("Calories per Week (cal)", DEFAULT_CALORIES_PER_WEEK) );
    }
    
    public List<NutritionalProfileField> getFields() {
        return fields;
    }    
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.fields);
        hash = 11 * hash + Objects.hashCode(this.allergens);
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
        final NutritionalProfile other = (NutritionalProfile) obj;
        if (!Objects.equals(this.fields, other.fields)) {
            return false;
        }
        if (!Objects.equals(this.allergens, other.allergens)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "NutritionalProfile{" + "fields=" + fields + ", allergens=" + allergens + '}';
    }

    
    
    
}
