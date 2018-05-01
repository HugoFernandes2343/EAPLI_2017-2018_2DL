/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.Kitchen;

import eapli.ecafeteria.domain.kitchen.MenuPlanItem;
import static eapli.ecafeteria.domain.Kitchen.MenuPlanState.IN_PROGRESS;
import static eapli.ecafeteria.domain.Kitchen.MenuPlanState.PUBLISHED;
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
public class MenuPlan <M,Q> implements Serializable 
{
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Enumerated(EnumType.STRING)
    private MenuPlanState menuPlanState;
    
    @OneToMany
    private List<MenuPlanItem> menuItems;

    
    
    
    public MenuPlan(List<MenuPlanItem> menuItems) 
    {
        this.menuPlanState = IN_PROGRESS;
        this.menuItems = menuItems;
    }

    
    public MenuPlan() 
    {
        this.menuPlanState = IN_PROGRESS;
        this.menuItems = new ArrayList<MenuPlanItem>();      
    }
    
    
    
    
    
    public void updateMenuPlanState (MenuPlan p) 
    {
        if (p.menuItems.isEmpty()) {
            this.menuPlanState = IN_PROGRESS;
        }
        else this.menuPlanState = PUBLISHED;            
    }
     
    
    
    
    
    
    

    
    
 
    
    
}
