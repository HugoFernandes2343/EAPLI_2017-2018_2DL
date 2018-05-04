/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 *
 * @author André Santos
 */
@Entity
public class MealPlanItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @Version
    private Long version;

    @OneToOne
    private Meal meal;

    @Embedded
    private Integer DishQuantity;

    @OneToOne
    private MealPlan mealPlan;

    protected MealPlanItem() {

    }

    public MealPlanItem(MealPlan mp, Meal meal, Integer DishQuantity) {
        this.meal = meal;
        this.mealPlan = mp;
        this.DishQuantity = DishQuantity;
    }

    public int getDishQuantity(){
        return this.DishQuantity;
    }
}
