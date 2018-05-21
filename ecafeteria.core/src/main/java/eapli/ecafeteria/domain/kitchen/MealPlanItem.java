/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Paulo Jorge
 */
@Entity
public class MealPlanItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @OneToOne
    private Meal meal;

    private Integer DishQuantity;
    
    @ManyToOne
    private MealPlan mealPlan;

    protected MealPlanItem() {

    }

    public MealPlanItem(Meal meal, int DishQuantity) {
        this.meal = meal;
        this.DishQuantity = DishQuantity;
    }

    public int getDishQuantity() {
        return this.DishQuantity;
    }

    public Long id() {
        return id;
    }

    @Override
    public String toString() {
        return "ID= " + id + "   DishQuantity=" + DishQuantity;
    }

}
