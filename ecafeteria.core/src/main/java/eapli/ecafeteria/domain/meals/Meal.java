/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.kitchen.Lot;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;

/**
 * Meal class
 *
 * @author 1161569
 */
@Entity
@Table(uniqueConstraints={
    @UniqueConstraint(columnNames = {"dish_name", "mealType", "date"})
}) 
public class Meal implements Serializable, AggregateRoot<Long>{
    
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long pk;
    @Version
    private Long version;

    @ManyToOne()
    private Dish dish;

    @ManyToOne()
    private Menu menu;

    @Embedded
    private MealType mealType;
    @Temporal(TemporalType.DATE)
    private Calendar date;
    
    @ElementCollection
    @CollectionTable(name = "Used_Lot")
    @Column(name = "Lot")
    private List<Lot> lotList;
    
    private int mealNumber = 0;

    protected Meal() {
        //for ORM
    }

    public Meal(Dish dish, MealType mealType, Calendar date, Menu menu) {
        this.lotList=new ArrayList<>();
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
                mealType.mealType() + "\n- Date: " + date.getTime() +
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

    public boolean isLunch() {
        return this.mealType.isOf(MealType.MealTypes.LUNCH);
    }

    public boolean isDinner() {
        return this.mealType.isOf(MealType.MealTypes.DINNER);
    }
    
    public void registerLot(Lot lot){
        if(lotList.contains(lot)){
            throw new IllegalArgumentException();
        }
        lotList.add(lot);
    }
}
