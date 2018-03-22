/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.dishes.reporting;

import eapli.framework.dto.DTO;
import java.math.BigInteger;

/**
 * A pure DTO for reporting. we don't even bother in having the fields private
 * as the only purpose of this "class" is to carry data as a data bag.
 *
 * we need the constructor just to simplify the JPA query methods
 *
 * @author pgsou_000
 */
public class DishesPerCaloricCategory implements DTO {

    @SuppressWarnings({"squid:ClassVariableVisibilityCheck"})
    public String caloricCategory;
    // this needs to be a BigInteger because it is mapped from a native query and JPA won't do type conversion
    @SuppressWarnings({"squid:ClassVariableVisibilityCheck"})
    public BigInteger quantityOfDishes;

    public DishesPerCaloricCategory(final String caloricCategory, final BigInteger quantityOfDishes) {
        this.caloricCategory = caloricCategory;
        this.quantityOfDishes = quantityOfDishes;
    }
}
