/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author David
 */
public interface MealRepository extends DataRepository<Meal, Long> {

    Iterable<Meal> findMealOneMenu(long id);
}
