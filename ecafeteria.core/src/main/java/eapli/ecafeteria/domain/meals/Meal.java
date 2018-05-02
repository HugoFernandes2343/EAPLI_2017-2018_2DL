/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.dishes.Dish;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * PlaceHolder class
 *
 * @author Utilizador
 */
@Entity
public class Meal {

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;

    @ManyToOne
    private Dish dish;

    @Embedded
    private MealType mealType;
    @Temporal(TemporalType.DATE)
    private Date date;
    
    private int mealNumber = 0;

    protected Meal() {
        //for ORM
    }

    public Meal(Dish dish, MealType mealType, Date date) {
        if (dish == null || mealType == null || date == null) {
            throw new IllegalStateException();
        }

        this.dish = dish;
        this.mealType = mealType;
        this.date = date;
        this.mealNumber++;
    }

    public Long pk() {
        return pk;
    }

    public Date getDate() {
        return date;
    }

    public Dish dish() {
        return this.dish;
    }

    public MealType mealType() {
        return this.mealType;
    }

    public boolean isOfMealType(MealType type) {
        return this.mealType.equals(type);
    }
    
    public int mealNumber(){
        return this.mealNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Meal meal = (Meal) o;

        if (!pk.equals(meal.pk)) {
            return false;
        }
        if (!version.equals(meal.version)) {
            return false;
        }
        if (!dish.equals(meal.dish)) {
            return false;
        }
        if (!mealType.equals(meal.mealType)) {
            return false;
        }
        return date.equals(meal.date);
    }

    @Override
    public int hashCode() {
        int result = dish.hashCode();
        result = 31 * result + mealType.hashCode();
        result = 31 * result + date.hashCode();
        return result;
    }

}
