/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser;

import eapli.ecafeteria.domain.allergens.Allergen;
import java.io.Serializable;
import java.util.Set;

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
    private double mealSalt;
    private double weekSalt;
    private double mealCalories;
    private double weekCalories;
    
    private Set<Allergen> allergens;
}
