/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meals.Meal;
import eapli.ecafeteria.persistence.MealRepository;

/**
 *
 * @author Hugo
 */
public class JpaMealRepository extends CafeteriaJpaRepositoryBase<Meal, Long> implements MealRepository {
    
}
