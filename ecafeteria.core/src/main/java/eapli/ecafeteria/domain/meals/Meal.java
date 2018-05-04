/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.framework.domain.ddd.AggregateRoot;
import eapli.framework.util.DateTime;
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * Meal class
 *
 * @author 1161569
 */
@Entity
public class Meal implements Serializable, AggregateRoot<Long>{
    
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;

    @ManyToOne()
    private Dish dish;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "menuID")
    private Menu menu;

    @Embedded
    private MealType mealType;
    @Temporal(TemporalType.DATE)
    private Calendar date;

    private int mealNumber = 0;

    protected Meal() {
        //for ORM
    }

    public Meal(Dish dish, MealType mealType, Calendar date, Menu menu) {
        this.menu = menu;
        this.dish = dish;
        this.mealType = mealType;
        this.date = date;
        this.mealNumber++;
    }

    public Long pk() {
        return pk;
    }

    public Calendar date() {
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

    public int mealNumber() {
        return this.mealNumber;
    }

    @Override
    public String toString() {
        return "\nMeal:\n- Dish: " + dish.name() + "\n- Meal Type: " +
                mealType.mealType() + "\n- Date: " + DateTime.format(date) +
                "\n- Meal Number: " + mealNumber + '\n';
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

    public boolean belongsToMenu(Menu m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean sameAs(Object other) {
         if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Meal meal = (Meal) other;

        if (!dish.equals(meal.dish)) {
            return false;
        }
        if (!mealType.equals(meal.mealType)) {
            return false;
        }
        return date.equals(meal.date);
    }

    @Override
    public Long id() {
       return pk;
    }

}
