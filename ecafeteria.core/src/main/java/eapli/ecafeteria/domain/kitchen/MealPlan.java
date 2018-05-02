/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.Kitchen;

import eapli.ecafeteria.domain.kitchen.MealPlanItem;
import static eapli.ecafeteria.domain.Kitchen.MealPlanState.IN_PROGRESS;
import static eapli.ecafeteria.domain.Kitchen.MealPlanState.PUBLISHED;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author André Santos
 */
@Entity
public class MealPlan <M,Q> implements Serializable 
{
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private MealPlanState mealPlanState;
    
    @OneToMany
    private List<MealPlanItem> menuItems;

    
    
    
    public MealPlan(List<MealPlanItem> menuItems) 
    {
        this.mealPlanState = IN_PROGRESS;
        this.menuItems = menuItems;
    }

    
    public MealPlan() 
    {
        this.mealPlanState = IN_PROGRESS;
        this.menuItems = new ArrayList<MealPlanItem>();      
    }
    
    
    
    
    
    public void updateMalPlanState (MealPlan p) 
    {
        if (p.menuItems.isEmpty()) {
            this.mealPlanState = IN_PROGRESS;
        }
        else this.mealPlanState = PUBLISHED;            
    }
     
    public void closeMealPlan(){
        this.mealPlanState=PUBLISHED;
    }
    
    
    
    
    

    
    
 
    
    
}
