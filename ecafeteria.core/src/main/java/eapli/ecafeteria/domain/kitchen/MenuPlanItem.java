/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author André Santos
 */
@Entity
public class MenuPlanItem implements Serializable
{
    @Id
    @GeneratedValue
    private Long id;
        
    @OneToMany
    private Meal meal;
    
    
    private Integer DishQuantity;

    public MenuPlanItem() {
    }

    
    
    public MenuPlanItem(Meal meal, Integer DishQuantity) {
        this.meal = meal;
        this.DishQuantity = DishQuantity;
    }
       
    
}
