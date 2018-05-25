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
import java.util.Iterator;
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
        buildDefaultProfile();
    }

    private void buildDefaultProfile(){
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

    public List<Allergen> getAllergens() {
        return allergens;
    }
    
    /**
     * 
     * @param field The field to be updated. Must already exist in "fields"
     * @param newValue 
     * @return "true" if it managed to find the "field" and update it, "false" otherwise
     */
    public boolean updateFieldValue (NutritionalProfileField field, double newValue){
        Iterator<NutritionalProfileField> it = fields.iterator();
        
        while (it.hasNext()){
            NutritionalProfileField next = it.next();
            if (next.equals(field)){
                next.setValue (newValue);
                return true;
            }
        }
        return false;
    }
    
    public boolean addAllergen (Allergen newAllergen){
        if (allergens.contains(newAllergen)){
            return false;
        }
        allergens.add(newAllergen);
        return true;
    }
    
    public boolean removeAllergen (Allergen allergen){
        return allergens.remove(allergen);
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
    public NutritionalProfile clone() {
        List<NutritionalProfileField> clonedFields = new ArrayList<NutritionalProfileField>(fields);
        List<Allergen> clonedAllergens = new ArrayList<Allergen>(allergens);
        return new NutritionalProfile (clonedFields, clonedAllergens);
    }

    
    @Override
    public String toString() {
        return "NutritionalProfile{" + "fields=" + fields + ", allergens=" + allergens + '}';
    }

    
    
    
}
