/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meals;

import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author João Vieira
 */
@Embeddable
public class MealType implements ValueObject, Serializable {

    private MealTypes mealType;

    protected MealType() {
    } // For ORM

    public MealType(MealTypes mealType) {
        if (mealType == null) {
            throw new IllegalStateException();
        }
        this.mealType = mealType;
    }

    public String mealType() {
        return this.mealType.name();
    }

    public enum MealTypes {
        LUNCH, DINNER
    }

    /**
     * It checks if the meal type (enum) is the same as this meal type.
     *
     * @param type The meal type (enum) to check.
     * @return It returns "true" if this meal type has the same meal type (enum)
     * or "false" otherwise.
     */
    public boolean isOf(MealTypes type) {
        return type != null && mealType.equals(type);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MealType meal = (MealType) o;

        return mealType.equals(meal.mealType);
    }
}
