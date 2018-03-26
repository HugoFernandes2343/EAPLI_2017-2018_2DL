/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.reporting.dishes;

import eapli.framework.dto.DTO;

/**
 * A pure DTO for reporting. we don't even bother in having the fields private
 * as the only purpose of this "class" is to carry data as a data bag.
 *
 * we need the constructor just to simplify the JPA query methods
 *
 * @author pgsou_000
 */
public class DishesPerDishType implements DTO {

    public String dishType;
    public long quantityOfDishes;

    public DishesPerDishType(String dishType, long quantityOfDishes) {
        this.dishType = dishType;
        this.quantityOfDishes = quantityOfDishes;
    }
}
