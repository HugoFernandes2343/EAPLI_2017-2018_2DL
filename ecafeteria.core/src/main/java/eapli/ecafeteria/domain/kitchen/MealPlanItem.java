/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.kitchen;

import eapli.ecafeteria.domain.meals.Meal;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
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

    private int NonDeliveredMeals;

    private int DeliveredMeals;

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
        return  "\n DishQuantity = " + DishQuantity + meal + "\n Non Delivered Meals = " + NonDeliveredMeals;
    }

    public String toStringValuesOnly(){
        return  "\n DishQuantity = " + DishQuantity + "\n Non Delivered Meals = " + NonDeliveredMeals;
    }
    
    public void setDishQuantity(Integer DishQuantity) {
        this.DishQuantity = DishQuantity;
    }

    public void calculateWastedMeals(int n) {
        this.NonDeliveredMeals = n;
        this.DeliveredMeals = this.DishQuantity - n;
    }

    public boolean checkMeal(Meal m) {
        if (m.equals(this.meal)) {
            return true;
        } else {
            return false;
        }

    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.meal);
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
        final MealPlanItem other = (MealPlanItem) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.meal, other.meal)) {
            return false;
        }

        return true;
    }

}
