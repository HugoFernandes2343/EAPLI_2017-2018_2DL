/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import eapli.ecafeteria.domain.allergens.Allergen;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 *
 * @author Rodrigo
 */

public class NutritionalProfile implements Serializable {
    private List<NutritionalProfileField> fields;    
    private List<Allergen> allergens;

    public NutritionalProfile(List<NutritionalProfileField> fields, List<Allergen> allergens) {
        this.fields = fields;
        this.allergens = allergens;
    }

    public NutritionalProfile() {
        fields = new ArrayList<NutritionalProfileField>();
        allergens = new ArrayList<Allergen>();        
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
